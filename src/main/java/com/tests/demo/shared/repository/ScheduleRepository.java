package com.tests.demo.shared.repository;

import com.tests.demo.shared.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
