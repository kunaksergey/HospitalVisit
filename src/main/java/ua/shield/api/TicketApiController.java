package ua.shield.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.TicketConverter;
import ua.shield.converter.TicketSlotAssambler;
import ua.shield.domen.DateRange;
import ua.shield.dto.TicketDto;
import ua.shield.dto.TicketSlotDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.ScheduleDay;
import ua.shield.entity.Ticket;
import ua.shield.repository.DoctorRepository;
import ua.shield.service.DoctorService;
import ua.shield.service.PatientService;
import ua.shield.service.ScheduleService;
import ua.shield.service.TicketService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/ticket", produces = "application/json")
public class TicketApiController {
    private final int range = 14;

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;


    @Autowired
    TicketService ticketService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TicketSlotAssambler scheduleAssambler;

    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(value = "/doctor/{doctorId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketSlotDto>> findAllDailyInfoByDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findOne(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().plusDays(range));
        List<Ticket> ticketList = ticketService.assambledInRangeByDoctor(dateRange, doctor);
        List<TicketSlotDto> ticketSlotDtoList =scheduleAssambler.toList(
                scheduleService.findAllByDoctor(doctor),
                ticketList,
                dateRange);

        return new ResponseEntity<>(ticketSlotDtoList
                .stream()
                .sorted(Comparator.comparing(TicketSlotDto::getDate))
                .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST,consumes = "application/json")
    ResponseEntity<TicketDto> save(@RequestBody TicketDto ticketDto, Principal principal){
        Patient patient=patientService.findByName(principal.getName());
        Doctor doctor=doctorService.findOne(ticketDto.getDoctorId());
        Ticket newTicket = ticketConverter.createFromDto(ticketDto, doctor, patient);
        Ticket savedTicket = ticketService.save(newTicket);
        return new ResponseEntity<>(ticketConverter.createFromEntity(savedTicket),
                HttpStatus.CREATED);
    }
}
