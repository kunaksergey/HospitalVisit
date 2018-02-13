package ua.shield.api.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.TicketEntityDoctorDtoConverter;
import ua.shield.converter.TicketEntityDtoConverter;
import ua.shield.converter.TicketPickerEntityDtoConverter;
import ua.shield.domen.Condition;
import ua.shield.domen.DateRange;
import ua.shield.domen.TicketPicker;
import ua.shield.dto.ErrorResponseDto;
import ua.shield.dto.TicketDoctorDto;
import ua.shield.dto.TicketDto;
import ua.shield.dto.TicketPickerDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.factory.ErrorResponseFactory;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.TicketPickerService;
import ua.shield.service.TicketService;
import ua.shield.service.adapter.UnitMethodServiceAdapter;
import ua.shield.service.factory.UnitMethodServiceAdapterFactory;
import ua.shield.validator.TicketDoctorDtoValidator;
import ua.shield.validator.TicketDtoValidator;
import ua.shield.validator.TicketExisting;
import ua.shield.validator.TicketNew;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/secured/v1/ticket", produces = "application/json")
public class TicketSecApiController {
    private final int range = 14;

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final TicketPickerService ticketPickerService;
    private final TicketService ticketService;
    private final TicketEntityDtoConverter ticketConverter;
    private final TicketEntityDoctorDtoConverter ticketDoctorConverter;
    private final TicketPickerEntityDtoConverter ticketPickerConverter;
    private final UnitMethodServiceAdapterFactory unitMethodServiceAdapterFactory;
    private final ErrorResponseFactory errorResponseFactory;

    @Autowired
    public TicketSecApiController(PatientService patientService,
                                  DoctorService doctorService,
                                  TicketPickerService ticketPickerService,
                                  TicketService ticketService,
                                  TicketEntityDtoConverter ticketConverter,
                                  TicketEntityDoctorDtoConverter ticketDoctorConverter,
                                  TicketPickerEntityDtoConverter ticketPickerConverter,
                                  UnitMethodServiceAdapterFactory unitMethodServiceAdapterFactory,
                                  ErrorResponseFactory errorResponseFactory) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.ticketPickerService = ticketPickerService;
        this.ticketService = ticketService;
        this.ticketConverter = ticketConverter;
        this.ticketDoctorConverter = ticketDoctorConverter;
        this.ticketPickerConverter = ticketPickerConverter;
        this.unitMethodServiceAdapterFactory = unitMethodServiceAdapterFactory;
        this.errorResponseFactory = errorResponseFactory;
    }

//    @InitBinder("ticketDto")
//    protected void initBinderTicketDto(WebDataBinder binder) {
//        binder.setValidator(new TicketDtoValidator());
//    }
//
//    @InitBinder("ticketDoctorDto")
//    protected void initBinderTicketDoctorDto(WebDataBinder binder) {
//        binder.setValidator(new TicketDoctorDtoValidator());
//    }


    @RequestMapping(value = "listdata/doctor/{doctorId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketPickerDto>> findAllDailyInfoByDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findOne(doctorId);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().plusDays(range));
        List<TicketPicker> ticketPickerList = ticketPickerService.findAllInRangeByDoctor(dateRange, doctor);
        List<TicketPickerDto> ticketPickerDtoList = ticketPickerConverter.createFromEntities(ticketPickerList);

        return ResponseEntity.ok(ticketPickerDtoList
                .stream()
                .sorted(Comparator.comparing(TicketPickerDto::getDate))
                .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<?> add(@Validated(TicketNew.class) @RequestBody TicketDto ticketDto,
                          BindingResult bindingResult,
                          Principal principal) {
        if (bindingResult.hasErrors()) {
            ErrorResponseDto errorResponse = errorResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, "bad request", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Patient patient = patientService.findByName(principal.getName());
        Doctor doctor = doctorService.findOne(ticketDto.getDoctorId());
        Ticket newTicket = ticketConverter.createFromDto(ticketDto, doctor, patient);
        newTicket.setStatus(StatusTicket.PROCESED);
        Ticket addedTicket = ticketService.add(newTicket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(addedTicket),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PUT, consumes = "application/json")
//    @PreAuthorize("!isAnonymous()")
    ResponseEntity<?> cancel(@Validated(TicketExisting.class) @RequestBody TicketDoctorDto ticketDoctorDto,
                                           BindingResult bindingResult,
                                           Principal principal
    ) {
        if(bindingResult.hasErrors()){
            ErrorResponseDto errorResponse = errorResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, "bad request", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Ticket ticket = ticketService.findOne(ticketDoctorDto.getTicketDto().getId());
        ticket.setStatus(StatusTicket.CANCELED);
        Ticket updatedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketDoctorConverter.createFromEntity(updatedTicket),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR') OR hasRole('ROLE_ADMIN')")
    ResponseEntity<TicketDoctorDto> edit(@RequestBody TicketDoctorDto ticketDoctorDto, Principal principal) {
        Ticket ticket = ticketService.findOne(ticketDoctorDto.getTicketDto().getId());
        ticket.setNote(ticketDoctorDto.getTicketDto().getNote());
        Ticket updatedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketDoctorConverter.createFromEntity(updatedTicket),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/lock", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR') OR hasRole('ROLE_ADMIN')")
    ResponseEntity<?> lock(@Validated @RequestBody TicketDoctorDto ticketDoctorDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            ErrorResponseDto errorResponse = errorResponseFactory.createErrorResponse(HttpStatus.BAD_REQUEST, "bad request", bindingResult.getFieldErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Ticket ticket = ticketService.findOne(ticketDoctorDto.getTicketDto().getId());
        ticket.setStatus(StatusTicket.DONE);
        Ticket lockedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketDoctorConverter.createFromEntity(lockedTicket),
                HttpStatus.OK);
    }


    @RequestMapping(value = "/unlock", method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR') OR hasRole('ROLE_ADMIN')")
    ResponseEntity<TicketDoctorDto> unlock(@RequestBody TicketDoctorDto ticketDoctorDto, Principal principal) {
        Ticket ticket = ticketService.findOne(ticketDoctorDto.getTicketDto().getId());
        ticket.setStatus(StatusTicket.PROCESED);
        Ticket unlockedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketDoctorConverter.createFromEntity(unlockedTicket),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/listdata/patient", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<List<TicketDto>> findAllByPatient(
            @RequestParam(name = "status", required = false) StatusTicket status,
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end,
            Principal principal) {

        Patient patient = patientService.findByName(principal.getName());
        Condition condition = new Condition(status, new DateRange(start, end));
        UnitMethodServiceAdapter patientMethodServiceAdapter = unitMethodServiceAdapterFactory.getPatientMethodServiceAdapter(patient);
        List<Ticket> ticketList = findAllByCondition(patientMethodServiceAdapter, condition);
        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/listdata/doctor", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    ResponseEntity<List<TicketDoctorDto>> findAllByDoctor(
            @RequestParam(name = "status", required = false) StatusTicket status,
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end,
            Principal principal) {
        Doctor doctor = doctorService.findByName(principal.getName());
        Condition condition = new Condition(status, new DateRange(start, end));
        UnitMethodServiceAdapter doctorMethodServiceAdapter = unitMethodServiceAdapterFactory.getDoctorMethodServiceAdapter(doctor);
        List<Ticket> ticketList = findAllByCondition(doctorMethodServiceAdapter, condition);
        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketDoctorConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/patient/{patientId}/status/{statusId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketDto>> findAllByPatientAndStatus(@PathVariable Integer patientId,
                                                              @PathVariable StatusTicket statusTicket,
                                                              Principal principal) {
        Patient patient = patientService.findByName(principal.getName());

        List<Ticket> ticketList = ticketService.findAllByPatientAndStatus(patient, statusTicket);

        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private List<Ticket> findAllByCondition(UnitMethodServiceAdapter unitMethodServiceAdapter, Condition condition) {
        List<Ticket> listTicket;
        if (condition.getDateRange().getStart() != null && condition.getDateRange().getEnd() != null) {
            listTicket = unitMethodServiceAdapter.findAllDateBetween(condition.getDateRange());
        } else if (condition.getDateRange().getStart() != null) {
            listTicket = unitMethodServiceAdapter.findAllDateStartWith(condition.getDateRange());
        } else {
            listTicket = unitMethodServiceAdapter.findAll();
        }
        if (condition.getStatus() != null) {
            listTicket = listTicket
                    .stream()
                    .filter(t -> t.getStatus().equals(condition.getStatus()))
                    .collect(Collectors.toList());
        }
        return listTicket;
    }
}
