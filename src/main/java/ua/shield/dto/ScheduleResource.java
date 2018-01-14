package ua.shield.dto;

import java.util.List;

public class ScheduleResource {
    List<TicketDto> ticketDtoList;

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<TicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }
}
