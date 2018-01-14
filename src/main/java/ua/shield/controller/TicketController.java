package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.shield.dto.TicketDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.service.DoctorService;
import ua.shield.service.ScheduleService;
import ua.shield.service.TicketService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final int period = 14;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/{id}")
    @ResponseBody
    Set<TicketDto> showByDoctor(@PathVariable Integer id) {
//        Doctor doctor = doctorService.findOne(id);
////        Schedule schedule=scheduleService.getCurrentSchedule(doctor);
//
//        Set<TicketDto> ticketDtoPeriodSet=new HashSet<>();
//
////        List<Ticket> ticketsForTwoWeek = ticketService.findAllForTwoWeek(doctor,
////                LocalDate.now(),
////                LocalDate.now().plusDays(period)
////        );
////        ticketsForTwoWeek.forEach(t->ticketDtoPeriodSet.add(new TicketDto(t)));
//
//         for (int i = 0; i < period; i++) {
//            final LocalDate currentDay = LocalDate.now().plusDays(i);
//            final DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
//            final EvenOddEnum evenOrOddDay = (currentDay.getDayOfMonth() % 2 == 0) ? EvenOddEnum.EVEN : EvenOddEnum.ODD;
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            Set<TicketDto> collect = schedule.getScheduleDaySet()
//                    .stream()
//                    .filter(sd -> sd.getEvenOrOdd().equals(evenOrOddDay) && sd.getWeekDay().name().equals(dayOfWeek.name()))
//                    .map(s -> {
//                        TicketDto ticketDto = new TicketDto();
//                        ticketDto.setDate(currentDay.format(formatter));
//                        ticketDto.setTime(s.getTime());
//                        ticketDto.setDoctorId(doctor.getId());
//                        ticketDto.setBusy(false);
//                        return ticketDto;
//                    })
//                    .collect(Collectors.toSet());
//         ticketDtoPeriodSet.addAll(collect);
//        }
//    return ticketDtoPeriodSet;
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addTicket(@RequestBody TicketDto ticketDto) {
        Doctor doctor = doctorService.findOne(ticketDto.getDoctorId());
        Ticket ticket = new Ticket();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ticket.setDate(LocalDate.parse(ticketDto.getDate(), formatter));
        ticket.setTime(ticketDto.getTime());
        ticket.setStatus(StatusTicket.PROCESED);
        ticket.setDoctor(doctor);
        //ticket.setPatient();
        //ticketService.add(ticket);
        System.out.println();
    }

}
