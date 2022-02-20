package com.tests.demo.shared.repository;

import com.tests.demo.shared.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
