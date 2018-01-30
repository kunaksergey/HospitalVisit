package ua.shield.converter;

import org.springframework.stereotype.Component;
import ua.shield.dto.ScheduleDto;
import ua.shield.entity.Schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleEntityDtoConverter implements GenericEntityDtoConverter<Schedule,ScheduleDto> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public Schedule createFromDto(ScheduleDto dto) {
        Schedule schedule=new Schedule();
        schedule.setId(dto.getId());
        schedule.setRoom(dto.getRoom());
        schedule.setStart(LocalDate.parse(dto.getStart(),formatter));
        schedule.setNotice(dto.getNotice());
        schedule.setScheduleDaySet(dto.getScheduleDaySet());
        schedule.getScheduleDaySet().forEach(day->{
            day.setSchedule(schedule);
            day.getScheduleTimeSet().forEach(time-> time.setScheduleDay(day));
        });
        return schedule;
    }

    @Override
    public ScheduleDto createFromEntity(Schedule entity) {
        ScheduleDto scheduleDto =new ScheduleDto();
        scheduleDto.setDoctorId(entity.getDoctor().getId());
        scheduleDto.setId(entity.getId());
        scheduleDto.setRoom(entity.getRoom());
        scheduleDto.setStart(entity.getStart().format(formatter));
        scheduleDto.setNotice(entity.getNotice());
        scheduleDto.setScheduleDaySet(entity.getScheduleDaySet());
        return scheduleDto;
    }

    @Override
    public Schedule updateEntity(Schedule entity, ScheduleDto dto) {
        return null;
    }
}
