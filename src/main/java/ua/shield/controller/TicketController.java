package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.shield.dto.TicketDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.service.DoctorService;
import ua.shield.service.ScheduleService;
import ua.shield.service.TicketService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Doctor doctor = doctorService.findOne(id);
        Schedule schedule=scheduleService.getCurrentSchedule(doctor);

        Set<TicketDto> ticketDtoPeriodSet=new HashSet<>();

        List<Ticket> ticketsForTwoWeek = ticketService.findAllForTwoWeek(doctor,
                LocalDate.now(),
                LocalDate.now().plusDays(period)
        );
        ticketsForTwoWeek.forEach(t->ticketDtoPeriodSet.add(new TicketDto(t)));

         for (int i = 0; i < period; i++) {
            final LocalDate currentDay = LocalDate.now().plusDays(i);
            final DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
            final EvenOddEnum evenOrOddDay = (currentDay.getDayOfMonth() % 2 == 0) ? EvenOddEnum.EVEN : EvenOddEnum.ODD;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Set<TicketDto> collect = schedule.getDetailsSet()
                    .stream()
                    .filter(sd -> sd.getEvenOrOdd().equals(evenOrOddDay) && sd.getWeekDay().name().equals(dayOfWeek.name()))
                    .map(s -> {
                        TicketDto ticketDto = new TicketDto();
                        ticketDto.setDate(currentDay.format(formatter));
                        ticketDto.setTime(s.getTime());
                        ticketDto.setDoctorId(doctor.getId());
                        ticketDto.setBusy(false);
                        return ticketDto;
                    })
                    .collect(Collectors.toSet());
         ticketDtoPeriodSet.addAll(collect);
        }
    return ticketDtoPeriodSet;

    }

}
