package com.tests.demo.shared.service;


import com.tests.demo.SessionApplication;
import com.tests.demo.shared.constants.Constants;
import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    private ScheduleRepository repository;

    private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.repository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        if(schedule.getDescription() != null && !schedule.getDescription().isEmpty()){
            Schedule response = repository.save(schedule);
            logger.info(Constants.SCHEDULE.SESSION_CREATED);
            return response;
        }
        logger.info(Constants.SCHEDULE.SESSION_NEEDS_DESCRIPTION);
        throw new MainException(Constants.SCHEDULE.SESSION_NEEDS_DESCRIPTION);
    }

    public Schedule getSchedule(Long id){
        Optional<Schedule> schedule = repository.findById(id);
        if(schedule.isPresent()){
            logger.info(Constants.SCHEDULE.SESSION_RETURNED_SUCCESFUL);
            return schedule.get();
        }
        logger.info(Constants.SCHEDULE.ERROR_NOT_FOUND);
        throw new MainException(Constants.SCHEDULE.ERROR_NOT_FOUND);
    }


}