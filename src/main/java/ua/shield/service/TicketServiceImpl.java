package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shield.domen.DateRange;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.enum_.StatusTicket;
import ua.shield.helper.ScheduleHelper;
import ua.shield.repository.TicketRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllForTwoWeek(Doctor doctor, LocalDate start, LocalDate end) {
        return ticketRepository.findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(
                doctor, start, end);
    }

    @Override
    public List<Ticket> findAllInRangeByDoctor(DateRange range, Doctor doctor) {
        return ticketRepository.findAllByDoctorAndDateGreaterThanEqualAndDateLessThanEqual(
                doctor,
                range.getStart(),
                range.getEnd()
        );
    }

    @Override
    public List<Ticket> assambledInRangeByDoctor(DateRange range, Doctor doctor) {
        Set<Ticket> ticketSet = new HashSet<>();
        ticketSet.addAll(findAllInRangeByDoctor(range, doctor)); //db tickets
        ticketSet.addAll(virtualTicketList(range, doctor)); //virtual tickets
        return new ArrayList<>(ticketSet);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }


    private List<Ticket> virtualTicketList(DateRange dateRange, Doctor doctor) {
        List<Ticket> ticketList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.findAllByDoctor(doctor);
        for (int i = 0; i < dateRange.between(); i++) {
            final LocalDate currentDay = LocalDate.now().plusDays(i);
            final DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
            Schedule currentSchedule = ScheduleHelper.defineCurrentSchedule(scheduleList, currentDay);
            final EvenOddEnum evenOrOddDay = (currentDay.getDayOfMonth() % 2 == 0) ? EvenOddEnum.EVEN : EvenOddEnum.ODD;
            currentSchedule.getScheduleDaySet()
                    .stream()
                    .filter(sd -> sd.getEvenOrOdd().equals(evenOrOddDay) && sd.getWeekDay().name().equals(dayOfWeek.name()))
                    .forEach(s -> {
                        List<Ticket> collect = s.getScheduleTime().stream().map(t -> {
                            Ticket ticket = new Ticket();
                            ticket.setDate(currentDay);
                            ticket.setTime(t.getTime());
                            ticket.setDoctor(doctor);
                            ticket.setStatus(StatusTicket.NEW);
                            return ticket;
                        }).collect(Collectors.toList());
                        ticketList.addAll(collect);
                    });
        }
        return ticketList;
    }

}
