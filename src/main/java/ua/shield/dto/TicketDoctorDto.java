package ua.shield.dto;

import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

public class TicketDoctorDto {
    private TicketDto ticketDto;
    private String patientFullName;
    private StatusTicket status;
    public TicketDoctorDto() {
    }

    public TicketDoctorDto(Ticket ticket) {
        this.ticketDto=new TicketDto(ticket);
        this.patientFullName=ticket.getPatient().getUser().getFullName();
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
