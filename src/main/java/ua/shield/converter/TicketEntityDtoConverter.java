package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.dto.TicketDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;
import ua.shield.enum_.StatusTicket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TicketEntityDtoConverter implements GenericEntityDtoConverter<Ticket,TicketDto> {
    @Override
    public Ticket createFromDto(TicketDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Ticket ticket=new Ticket();
        ticket.setId(dto.getId());
        ticket.setTime(dto.getTime());
        ticket.setDate(LocalDate.parse(dto.getDate(),formatter));
        ticket.setStatus(dto.getStatus());
        ticket.setNote(dto.getNote());
        return ticket;
    }

    public Ticket createFromDto(TicketDto ticketDto, Doctor doctor, Patient patient) {
        Ticket ticket=createFromDto(ticketDto);
        ticket.setDoctor(doctor);
        ticket.setPatient(patient);
        return ticket;
    }


    @Override
    public TicketDto createFromEntity(Ticket entity) {
        TicketDto ticketDto=new TicketDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ticketDto.setId(entity.getId());
        ticketDto.setDoctorId(entity.getDoctor().getId());
        ticketDto.setDate(entity.getDate().format(formatter));
        ticketDto.setTime(entity.getTime());
        ticketDto.setBusy(entity.getPatient()!=null);
        ticketDto.setStatus(entity.getStatus());
        ticketDto.setNote(entity.getNote());
        return ticketDto;
    }

    @Override
    public Ticket updateEntity(Ticket entity, TicketDto dto) {
        return null;
    }
}
