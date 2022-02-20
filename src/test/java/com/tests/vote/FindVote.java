package com.tests.vote;

import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.Vote;
import com.tests.demo.shared.repository.VoteRepository;
import com.tests.demo.shared.service.VoteService;
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
public class FindVote {

    @InjectMocks
    private VoteService service;

    @MockBean
    private VoteRepository repository;

    @Test
    public void findVoteSuccess(){
        Vote vote = new Vote(1L,null, null,true);

        Mockito.when(repository.findById(vote.getId())).thenReturn(Optional.of(vote));

        assertEquals(vote, service.getVote(vote.getId()));

    }

    @Test
    public void errorNotFound(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(
                MainException.class,
                () -> {
                    service.getVote(1L);
                }
        );
    }
}