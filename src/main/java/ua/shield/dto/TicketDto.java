package ua.shield.dto;

import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

import java.time.format.DateTimeFormatter;

public class TicketDto {
    private Integer id;
    private Integer doctorId;
    private String time;
    private String date;
    private String note;
    private StatusTicket status;
    private boolean isBusy;

    public TicketDto() {
    }

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.doctorId = ticket.getDoctor().getId();
        this.time = ticket.getTime();
        this.date = ticket.getDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = ticket.getDate().format(formatter);
        this.isBusy = ticket.getStatus() != StatusTicket.NEW;
        this.status = ticket.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDto ticketDto = (TicketDto) o;

        if (doctorId != null ? !doctorId.equals(ticketDto.doctorId) : ticketDto.doctorId != null) return false;
        if (time != null ? !time.equals(ticketDto.time) : ticketDto.time != null) return false;
        return date != null ? date.equals(ticketDto.date) : ticketDto.date == null;
    }

    @Override
    public int hashCode() {
        int result = doctorId != null ? doctorId.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

}
