package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.dto.TicketDoctorDto;
import ua.shield.dto.TicketDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Patient;
import ua.shield.entity.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TicketEntityDoctorDtoConverter implements GenericEntityDtoConverter<Ticket,TicketDoctorDto> {

    @Override
    public Ticket createFromDto(TicketDoctorDto dto) {
        return null;
    }

    @Override
    public TicketDoctorDto createFromEntity(Ticket entity) {
        return new TicketDoctorDto(entity);
    }

    @Override
    public Ticket updateEntity(Ticket entity, TicketDoctorDto dto) {
        return null;
    }
}
