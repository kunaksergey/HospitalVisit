package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.dto.ScheduleDto;
import ua.shield.entity.Schedule;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class ScheduleConverter implements GenericConverter<Schedule,ScheduleDto>{

    @Override
    public Schedule createFromDto(ScheduleDto dto) {
        return null;
    }

    @Override
    public ScheduleDto createFromEntity(Schedule entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ScheduleDto scheduleDto =new ScheduleDto();
        scheduleDto.setId(entity.getId());
        scheduleDto.setRoom(entity.getRoom());
        scheduleDto.setStart(entity.getStart().format(formatter));
        scheduleDto.setNotice(entity.getNotice());
        scheduleDto.setScheduleDays(new ArrayList<>(entity.getScheduleDaySet()));
        return scheduleDto;
    }

    @Override
    public Schedule updateEntity(Schedule entity, ScheduleDto dto) {
        return null;
    }
}
