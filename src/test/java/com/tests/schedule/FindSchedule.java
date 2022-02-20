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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FindSchedule {
    @InjectMocks
    private ScheduleService service;

    @MockBean
    private ScheduleRepository repository;

    @Test
    public void findScheduleSuccess() {
        Schedule schedule = new Schedule();
        schedule.setDescription("PautaXYZ");
        Date date = new Date();
        schedule.setVotingDeadline(date);

        Optional<Schedule> optionalSchedule = Optional.of(schedule);
        Mockito.when(repository.findById(schedule.getId())).thenReturn(optionalSchedule);

        assertEquals(schedule, service.getSchedule(schedule.getId()));

    }

    @Test
    public void errorNotFound(){
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(
                MainException.class,
                () -> {
                    service.getSchedule(2L);
                }
        );
    }

}
