package ua.shield.dto;

import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;
import ua.shield.validator.InStatusTicket;
import ua.shield.validator.TicketExisting;
import ua.shield.validator.TicketNew;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TicketDoctorDto {

    @NotNull(groups = {TicketNew.class, TicketExisting.class},
            message = "{NotNull.ticketDoctorDto.ticketDto.required}"
    )
    @Valid
    private TicketDto ticketDto;

    @NotNull(groups = {TicketNew.class, TicketExisting.class},
            message = "{NotNull.ticketDoctorDto.patientFullName.required}"
    )
    private String patientFullName;

    @NotNull
            (groups = {TicketNew.class, TicketExisting.class},
            message = "{NotNull.ticketDoctorDto.status.required}"
    )
    @InStatusTicket(
            groups = {TicketNew.class, TicketExisting.class},
            message = "{NotNull.ticketDoctorDto.status.inRange}"
    )
    private StatusTicket status;

    public TicketDoctorDto() {
    }

    public TicketDoctorDto(Ticket ticket) {
        this.ticketDto = new TicketDto(ticket);
        this.patientFullName = ticket.getPatient().getUser().getFullName();
        this.status = ticket.getStatus();
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

}
