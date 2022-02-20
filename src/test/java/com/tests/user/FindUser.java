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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FindUser {

    @InjectMocks
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void success(){
        User user = new User(1L,"Fulano", "590.817.440-89");

        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        assertEquals(user,service.getUser(user.getId()));
    }

    @Test
    public void errorNotFound(){
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(
                MainException.class,
                () -> {
                    service.getUser(2L);
                }
        );
    }
}
