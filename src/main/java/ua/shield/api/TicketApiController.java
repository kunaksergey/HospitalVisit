package ua.shield.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.TicketEntityDtoConverter;
import ua.shield.converter.TicketPickerEntityDtoConverter;
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

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/ticket", produces = "application/json")
public class TicketApiController {
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
    @PreAuthorize( "!isAnonymous()")
    ResponseEntity<TicketDto> add(@RequestBody TicketDto ticketDto, Principal principal) {
        Patient patient = patientService.findByName(principal.getName());
        Doctor doctor = doctorService.findOne(ticketDto.getDoctorId());
        Ticket newTicket = ticketConverter.createFromDto(ticketDto, doctor, patient);
        Ticket savedTicket = ticketService.save(newTicket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(savedTicket),
                HttpStatus.CREATED);
    }


    @RequestMapping(value = "/patient/{patientId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketDto>> findAllByPatient(@PathVariable Integer patientId,
                                                              Principal principal) {
        Patient patient = patientService.findByName(principal.getName());

        List<Ticket> ticketList = ticketService.findAllByPatient(patient);

        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/patient/{patientId}/status/{statusId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketDto>> findAllByPatientAndStatus(@PathVariable Integer patientId,
                                                              @PathVariable StatusTicket statusTicket,
                                                              Principal principal) {
        Patient patient = patientService.findByName(principal.getName());

        List<Ticket> ticketList =ticketService.findAllByPatientAndStatus(patient, statusTicket);

        return (ticketList.size() > 0) ?
                new ResponseEntity<>(ticketConverter.createFromEntities(ticketList), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }

}
