package ua.shield.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.shield.converter.ScheduleEntityDtoConverter;
import ua.shield.dto.ScheduleDto;
import ua.shield.entity.Doctor;
import ua.shield.entity.Schedule;
import ua.shield.service.DoctorService;
import ua.shield.service.ScheduleService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/schedule")
public class ScheduleApiController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleEntityDtoConverter scheduleConverter;


    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @ResponseBody
    ScheduleDto findOne(@PathVariable Integer id) {
        return scheduleConverter.createFromEntity(scheduleService.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    ScheduleDto add(@RequestBody ScheduleDto scheduleDto) {
        Doctor doctor = doctorService.findOne(scheduleDto.getDoctorId());
        Schedule schedule = scheduleConverter.createFromDto(scheduleDto);
        schedule.setDoctor(doctor);
        Schedule savedSchedule = scheduleService.save(schedule);
        return scheduleConverter.createFromEntity(savedSchedule);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    ScheduleDto update(@RequestBody ScheduleDto scheduleDto) {
        Doctor doctor = doctorService.findOne(scheduleDto.getDoctorId());
        Schedule schedule = scheduleConverter.createFromDto(scheduleDto);
        schedule.setDoctor(doctor);
        Schedule savedSchedule = scheduleService.save(schedule);
        return scheduleConverter.createFromEntity(savedSchedule);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") Integer id) {
        scheduleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/doctor/{id}")
    @ResponseBody
    List<ScheduleDto> listData(@PathVariable Integer id) {
        Doctor doctor = doctorService.findOne(id);
        return doctor.getSchedules()
                .stream()
                .map(scheduleConverter::createFromEntity)
                .sorted(Comparator.comparing(ScheduleDto::getStart))
                .collect(Collectors.toList());
    }

}
