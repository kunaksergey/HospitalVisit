package ua.shield.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shield.domen.TicketPicker;
import ua.shield.dto.TicketPickerDto;

import java.time.format.DateTimeFormatter;

@Component
public class TicketPickerConverter implements GenericConverter<TicketPicker,TicketPickerDto> {

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public TicketPicker createFromDto(TicketPickerDto dto) {
        return null;
    }

    @Override
    public TicketPickerDto createFromEntity(TicketPicker entity) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        TicketPickerDto ticketPickerDto=new TicketPickerDto();
        ticketPickerDto.setDate(entity.getDate().format(formatter));
        ticketPickerDto.setNotice(entity.getNotice());
        ticketPickerDto.setTicketDtoList(ticketConverter.createFromEntities(entity.getTicketList()));
        return ticketPickerDto;
    }

    @Override
    public TicketPicker updateEntity(TicketPicker entity, TicketPickerDto dto) {
        return null;
    }
}
