package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.shield.dto.ScheduleDto;
import ua.shield.entity.Doctor;
import ua.shield.service.DoctorService;
import ua.shield.service.ScheduleService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sa on 14.12.17.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/{id}")
    @ResponseBody
    ScheduleDto findOne(@PathVariable Integer id) {
        return new ScheduleDto(scheduleService.findOne(id));
    }

    @RequestMapping("/doctor/{id}")
    @ResponseBody
    List<ScheduleDto> findAllByDoctor(@PathVariable Integer id) {
        Doctor doctor = doctorService.findOne(id);
        return doctor.getSchedules()
                .stream()
                .map(ScheduleDto::new)
                .sorted(Comparator.comparing(ScheduleDto::getStart))
                .collect(Collectors.toList());

    }

}
