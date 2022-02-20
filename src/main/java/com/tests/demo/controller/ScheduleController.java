package com.tests.demo.controller;

import com.tests.demo.SessionApplication;
import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class ScheduleController {
    private ScheduleService scheduleService;

    private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping()
    public Schedule createSchedule(@RequestBody Schedule schedule){

        return scheduleService.createSchedule(schedule);
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Long id){return scheduleService.getSchedule(id);}
}
