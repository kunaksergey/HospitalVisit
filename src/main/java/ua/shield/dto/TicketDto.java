package ua.shield.dto;

import org.hibernate.validator.constraints.NotEmpty;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.validator.TicketExisting;
import ua.shield.validator.TicketNew;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.time.format.DateTimeFormatter;

public class TicketDto {
    @NotNull(
            groups = TicketExisting.class,
            message = "{NotNull.ticketDto.id}"
    )
    @Null(groups = TicketNew.class)
    private Integer id;

    @NotNull(groups={TicketNew.class,TicketExisting.class},
            message = "{NotNull.ticketDto.id}"
    )
    private Integer doctorId;

    @NotEmpty(groups={TicketNew.class,TicketExisting.class},
            message = "{NotEmpty.ticketDto.time.requered}"
    )
    @Pattern(regexp = "/^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/",
    message="{Pattern.ticketDto.time.badformat}"
    )
    private String time;

    @NotNull(groups={TicketNew.class,TicketExisting.class},
            message = "{NotNull.ticketDto.date.requered}"
    )
    @Pattern(regexp = "/^(0[0-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[1-2]|)-(19[0-9][0-9]|20[0-9][0-9])$/",
            message="{Pattern.ticketDto.date.badformat}"
    )
    private String date;

    private String note;

    @NotNull(groups={TicketNew.class,TicketExisting.class},
            message = "{NotNull.ticketDto.isBusy.requered}"
    )
    private boolean isBusy;

    public TicketDto() {
    }

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.doctorId = ticket.getDoctor().getId();
        this.time = ticket.getTime();
        this.date = ticket.getDate().toString();
        this.note = ticket.getNote();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = ticket.getDate().format(formatter);
        this.isBusy = ticket.getStatus() != StatusTicket.NEW;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
