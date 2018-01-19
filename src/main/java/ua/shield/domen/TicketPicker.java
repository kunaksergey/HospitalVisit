package ua.shield.domen;


import ua.shield.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public class TicketPicker {
private LocalDate date;
private String notice;
private List<Ticket> ticketList;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
