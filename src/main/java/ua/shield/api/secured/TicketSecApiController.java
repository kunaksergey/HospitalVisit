package ua.shield.api.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.TicketEntityDtoConverter;
import ua.shield.converter.TicketPickerEntityDtoConverter;
import ua.shield.domen.Condition;
import ua.shield.domen.DateRange;
import ua.shield.domen.TicketPicker;
import ua.shield.dto.TicketDto;
import ua.shield.dto.TicketPickerDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.TicketPickerService;
import ua.shield.service.TicketService;
import ua.shield.service.adapter.UnitMethodServiceAdapter;
import ua.shield.service.factory.UnitMethodServiceAdapterFactory;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/secured/v1/ticket", produces = "application/json")
public class TicketSecApiController {
    private final int range = 14;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TicketPickerService ticketPickerService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketEntityDtoConverter ticketConverter;

    @Autowired
    private TicketPickerEntityDtoConverter ticketPickerConverter;
    @Autowired
    private UnitMethodServiceAdapterFactory unitMethodServiceAdapterFactory;


    @RequestMapping(value = "listdata/doctor/{doctorId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketPickerDto>> findAllDailyInfoByDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findOne(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().plusDays(range));
        List<TicketPicker> ticketPickerList = ticketPickerService.findAllInRangeByDoctor(dateRange, doctor);

        List<TicketPickerDto> ticketPickerDtoList = ticketPickerConverter.createFromEntities(ticketPickerList);

        return new ResponseEntity<>(ticketPickerDtoList
                .stream()
                .sorted(Comparator.comparing(TicketPickerDto::getDate))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize("!isAnonymous()")
    ResponseEntity<TicketDto> add(@RequestBody TicketDto ticketDto, Principal principal) {
        Patient patient = patientService.findByName(principal.getName());
        Doctor doctor = doctorService.findOne(ticketDto.getDoctorId());
        Ticket newTicket = ticketConverter.createFromDto(ticketDto, doctor, patient);
        newTicket.setStatus(StatusTicket.PROCESED);
        Ticket addedTicket = ticketService.add(newTicket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(addedTicket),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.PUT, consumes = "application/json")
            @PreAuthorize("!isAnonymous()")
    ResponseEntity<TicketDto> cancel(@RequestBody TicketDto ticketDto, Principal principal) {
        Ticket ticket=ticketService.findOne(ticketDto.getId());
        ticket.setStatus(StatusTicket.CANCELED);
        Ticket updatedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(updatedTicket),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/done",method = RequestMethod.PUT, consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    ResponseEntity<TicketDto> updateByDoctor(@RequestBody TicketDto ticketDto, Principal principal) {
        Ticket ticket=ticketService.findOne(ticketDto.getId());
        ticket.setStatus(StatusTicket.DONE);
        ticket.setNote(ticketDto.getNote());
        Ticket updatedTicket = ticketService.update(ticket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(updatedTicket),
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
//    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    ResponseEntity<List<TicketDto>> findAllByDoctor(
            @RequestParam(name = "status", required = false) StatusTicket status,
            @RequestParam(name = "start", required = false) LocalDate start,
            @RequestParam(name = "end", required = false) LocalDate end,
            Principal principal) {
//        Doctor doctor = doctorService.findByName(principal.getName());
        Doctor doctor = doctorService.findByName("kostyakunak");
        Condition condition = new Condition(status, new DateRange(start, end));
        UnitMethodServiceAdapter doctorMethodServiceAdapter = unitMethodServiceAdapterFactory.getDoctorMethodServiceAdapter(doctor);
        List<Ticket> ticketList = findAllByCondition(doctorMethodServiceAdapter, condition);
        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
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
