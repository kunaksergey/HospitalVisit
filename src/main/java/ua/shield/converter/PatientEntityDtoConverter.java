package ua.shield.converter;


import ua.shield.dto.PatientDto;
import ua.shield.entity.Patient;

public class PatientEntityDtoConverter implements GenericEntityDtoConverter<Patient,PatientDto>{
    @Override
    public Patient createFromDto(PatientDto dto) {
        return null;
    }

    @Override
    public PatientDto createFromEntity(Patient entity) {
        return new PatientDto(entity);
    }

    @Override
    public Patient updateEntity(Patient entity, PatientDto dto) {
        return null;
    }
}
