package ua.shield.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.TicketConverter;
import ua.shield.converter.TicketPickerConverter;
import ua.shield.domen.DateRange;
import ua.shield.domen.TicketPicker;
import ua.shield.dto.TicketDto;
import ua.shield.dto.TicketPickerDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
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
    private TicketConverter ticketConverter;

    @Autowired
    private TicketPickerConverter ticketPickerConverter;


    @RequestMapping(value = "listdata/doctor/{doctorId}", method = RequestMethod.GET)
    ResponseEntity<List<TicketPickerDto>> findAllDailyInfoByDoctor(@PathVariable Integer doctorId) {
        Doctor doctor = doctorService.findOne(doctorId);
        if (doctor == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().plusDays(range));
        List<TicketPicker> ticketPickerList= ticketPickerService.findAllInRangeByDoctor(dateRange,doctor);

        List<TicketPickerDto> ticketPickerDtoList = ticketPickerConverter.createFromEntities(ticketPickerList);

        return new ResponseEntity<>(ticketPickerDtoList
                .stream()
                .sorted(Comparator.comparing(TicketPickerDto::getDate))
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
