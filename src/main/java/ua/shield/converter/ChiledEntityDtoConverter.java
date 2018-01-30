package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.dto.ChieldDto;
import ua.shield.entity.Chield;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ChiledEntityDtoConverter implements GenericEntityDtoConverter<Chield,ChieldDto> {
    @Override
    public Chield createFromDto(ChieldDto dto) {
        Chield chield=new Chield();
        chield.setId(dto.getId());
        chield.setFullName(dto.getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        chield.setBirthDay(LocalDate.parse(dto.getBirthDay(),formatter));
        return chield;
    }

    @Override
    public ChieldDto createFromEntity(Chield entity) {
        return new ChieldDto(entity);
    }

    @Override
    public Chield updateEntity(Chield entity, ChieldDto dto) {
        return null;
    }
}
