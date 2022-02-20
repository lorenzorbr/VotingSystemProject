package com.tests.demo.shared.repository;

import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.model.User;
import com.tests.demo.shared.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByUserAndSchedule(User user, Schedule schedule);

    List<Vote> findBySchedule_id(Long id);
}
