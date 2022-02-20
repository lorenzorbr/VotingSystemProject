package com.tests.demo.controller;


import com.tests.demo.shared.dto.ResultVoteDto;
import com.tests.demo.shared.model.Vote;
import com.tests.demo.shared.service.VoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }

    @PostMapping()
    public Vote makeVote(@RequestBody Vote vote){return voteService.makeVote(vote);}

    @GetMapping("/result/{id}")
    public ResultVoteDto getResult(@PathVariable Long id){return voteService.getResult(id);}

    @GetMapping("/{id}")
    public Vote getVote(@PathVariable Long id){return voteService.getVote(id);}
}
