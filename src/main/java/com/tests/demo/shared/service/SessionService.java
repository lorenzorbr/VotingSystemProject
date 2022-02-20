package com.tests.demo.shared.service;

import com.tests.demo.SessionApplication;
import com.tests.demo.shared.constants.Constants;
import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class SessionService {

    private ScheduleRepository repository;

    private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

    public SessionService(ScheduleRepository scheduleRepository){
        this.repository = scheduleRepository;
    }

    //a schedule has to exist in order to create a new session
    public Schedule createSession(Schedule schedule) {
        Optional<Schedule> newSchedule = repository.findById(schedule.getId());
        if(newSchedule.isPresent()){
            if(newSchedule.get().getVotingDeadline() == null){
                if(schedule.getVotingDeadline() == null){
                    newSchedule.get().setVotingDeadline(addOneMinute());
                }
                else if(schedule.getVotingDeadline() != null){
                    newSchedule.get().setVotingDeadline(schedule.getVotingDeadline());
                }
                logger.info(Constants.SCHEDULE.SESSION_CREATED);
                return repository.save(newSchedule.get());
            }
        }
        throw new MainException(Constants.ERROR.ENTRY_ERROR);
    }

    public Date addOneMinute(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }

    public Schedule getSchedule(Long id){
        Optional<Schedule> schedule = repository.findById(id);
        if(schedule.isPresent()){
            return schedule.get();
        }
        logger.info(Constants.SCHEDULE.SESSION_RETURNED_SUCCESFUL);
        throw new MainException(Constants.SCHEDULE.ERROR_NOT_FOUND);
    }
}
