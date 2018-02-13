package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.shield.domen.DateRange;
import ua.shield.domen.TicketPicker;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.entity.ScheduleDay;
import ua.shield.entity.Ticket;
import ua.shield.enum_.EvenOddEnum;
import ua.shield.helper.ScheduleHelper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("ticketPicker")
@Transactional
public class TicketPickerServiceImpl implements TicketPickerService{

    private final TicketService ticketService;

    private final ScheduleService scheduleService;

    @Autowired
    public TicketPickerServiceImpl(TicketService ticketService, ScheduleService scheduleService) {
        this.ticketService = ticketService;
        this.scheduleService = scheduleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketPicker> findAllInRangeByDoctor(DateRange dateRange, Doctor doctor) {
        List<Ticket> ticketList = ticketService.assambledInRangeByDoctor(dateRange, doctor);
        List<Schedule> scheduleList = scheduleService.findAllByDoctor(doctor);

        List<TicketPicker> ticketPickerList =new ArrayList<>();

        for (int i = 0; i < dateRange.between(); i++) {
            final LocalDate currentDay = LocalDate.now().plusDays(i);
            final DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
            Schedule currentSchedule = ScheduleHelper.defineCurrentSchedule(scheduleList, currentDay);
            final EvenOddEnum evenOrOddDay = (currentDay.getDayOfMonth() % 2 == 0) ? EvenOddEnum.EVEN : EvenOddEnum.ODD;


            List<Ticket> collectTicket = ticketList
                    .stream()
                    .filter(t -> t.getDate().equals(currentDay))
                    .sorted(Comparator.comparing(Ticket::getTime))
                    .collect(Collectors.toList());

            String notice = currentSchedule.getScheduleDaySet()
                    .stream()
                    .filter(sd -> sd.getEvenOrOdd().equals(evenOrOddDay) && sd.getWeekDay().name().equals(dayOfWeek.name()))
                    .findFirst().orElse(new ScheduleDay()).getNotice();

            TicketPicker ticketPicker = new TicketPicker();
            ticketPicker.setDate(currentDay);
            ticketPicker.setNotice(notice);
            ticketPicker.setTicketList(collectTicket);
            ticketPickerList.add(ticketPicker);
        }
        return ticketPickerList;
     }
}
