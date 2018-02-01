package ua.shield.converter;


import org.springframework.stereotype.Component;
import ua.shield.dto.DoctorDto;
import ua.shield.entity.Doctor;
@Component
public class DoctorEntityDtoConverter implements GenericEntityDtoConverter<Doctor,DoctorDto>{

    @Override
    public Doctor createFromDto(DoctorDto dto) {
        return null;
    }

    @Override
    public DoctorDto createFromEntity(Doctor entity) {
        return new DoctorDto(entity);
    }

    @Override
    public Doctor updateEntity(Doctor entity, DoctorDto dto) {
        return null;
    }
}
