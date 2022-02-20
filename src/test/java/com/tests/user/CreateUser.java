package com.tests.user;

import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.User;
import com.tests.demo.shared.repository.UserRepository;
import com.tests.demo.shared.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CreateUser {

    @InjectMocks
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void success(){
        User user = new User(1L, "ABC" , "59081744089");
        Mockito.when(repository.save(user)).thenReturn(user);

        assertEquals(user, service.createUser(user));
    }

    @Test
    public void errorCouldNotCreateUser(){
        User user = new User();
        Mockito.when(repository.save(user)).thenReturn(user);

        assertThrows(
                MainException.class,
                () -> {
                    service.createUser(user);
                }
        );
    }
}
