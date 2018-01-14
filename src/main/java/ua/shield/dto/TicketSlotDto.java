package ua.shield.dto;

import java.util.List;

public class TicketSlotDto {
    private String date;
    private String notice;
    private List<TicketDto> ticketDtoList;

    public TicketSlotDto() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<TicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
