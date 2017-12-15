package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.shield.dto.ScheduleDto;
import ua.shield.entity.Schedule;
import ua.shield.service.ScheduleService;

/**
 * Created by sa on 14.12.17.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping("/{id}")
    @ResponseBody
    ScheduleDto findOne(@PathVariable Integer id ){
        return new ScheduleDto(scheduleService.findOne(id));
    }
}
