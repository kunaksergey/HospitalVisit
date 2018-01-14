package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.domen.DateRange;
import ua.shield.dto.TicketDto;
import ua.shield.dto.TicketSlotDto;
import ua.shield.entity.Schedule;
import ua.shield.entity.Ticket;
import ua.shield.helper.ScheduleHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketSlotAssambler {
    public List<TicketSlotDto> toList(List<Schedule> scheduleList, List<Ticket> ticketList, DateRange dateRange) {
        List<TicketSlotDto> ticketSlotDtoList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < dateRange.between(); i++) {
            final LocalDate currentDay = LocalDate.now().plusDays(i);
            List<TicketDto> collectDto = ticketList
                    .stream()
                    .filter(t -> t.getDate().equals(currentDay))
                    .map(TicketDto::new)
                    .sorted(Comparator.comparing(TicketDto::getTime))
                    .collect(Collectors.toList());
            TicketSlotDto ticketSlotDto = new TicketSlotDto();
            ticketSlotDto.setDate(currentDay.format(formatter));
            ticketSlotDto.setTicketDtoList(collectDto);
            ticketSlotDtoList.add(ticketSlotDto);
        }
        return ticketSlotDtoList;
    }


}
