package com.tests.schedule;



import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.repository.ScheduleRepository;
import com.tests.demo.shared.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CreateSchedule {

    @InjectMocks
    ScheduleService service;

    @MockBean
    ScheduleRepository repository;

    @Test
    public void success(){
        Schedule schedule = new Schedule(1L, "Andershow" , new Date());
        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        assertEquals(schedule, service.createSchedule(schedule));
    }

    @Test
    public void errorRequiredDescription(){
        Schedule schedule = new Schedule();
        Mockito.when(repository.save(schedule)).thenReturn(schedule);

        assertThrows(
                MainException.class,
                () -> {
                    service.createSchedule(schedule);
                }
        );
    }
}
