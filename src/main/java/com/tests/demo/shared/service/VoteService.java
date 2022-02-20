package com.tests.demo.shared.service;

import com.tests.demo.SessionApplication;
import com.tests.demo.shared.constants.Constants;
import com.tests.demo.shared.dto.ResultVoteDto;
import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.model.User;
import com.tests.demo.shared.model.Vote;
import com.tests.demo.shared.repository.ScheduleRepository;
import com.tests.demo.shared.repository.UserRepository;
import com.tests.demo.shared.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    private ScheduleRepository scheduleRepository;

    private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

    public VoteService(VoteRepository voteRepository, ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.voteRepository = voteRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public Vote getVote(Long id){
        Optional<Vote> vote = voteRepository.findById(id);
        if(vote.isPresent()){
            logger.info(Constants.VOTE.VOTE_RETURNED);
            return vote.get();
        }
        logger.info(Constants.VOTE.VOTE_NOT_FOUND);
        throw new MainException(Constants.VOTE.VOTE_NOT_FOUND);
    }

    public ResultVoteDto getResult(Long id){
        ResultVoteDto response = new ResultVoteDto();

        Schedule schedule = scheduleRepository.findById(id).get();
        Date date = new Date();

        if(!date.after(schedule.getVotingDeadline())) {
            logger.info(Constants.VOTE.SESSION_STILL_OPENED);
            throw new MainException(Constants.VOTE.SESSION_STILL_OPENED);
        }
        List<Vote> votes = voteRepository.findBySchedule_id(id);
        response.setSchedule(schedule);
        for(Vote vote: votes){
            if(vote.getVote())
                response.incrementApprove();
            else
                response.incrementReprove();
        }

        if(response.getApprove() > response.getReprove())
            response.setWinner("Schedule Approved");
        else
            response.setWinner("Schedule Reproved");
        return response;

    }

    public Vote makeVote(Vote vote) {
        User user = userRepository.findById(vote.getUser()).get();
        Schedule schedule = scheduleRepository.findById(vote.getSchedule()).get();

        if(schedule == null){
            logger.info(Constants.SCHEDULE.ERROR_NOT_FOUND);
            throw new MainException(Constants.SCHEDULE.ERROR_NOT_FOUND);
        }
        if(user == null){
            logger.info(Constants.USER.ERROR_NOT_FOUND);
            throw new MainException(Constants.USER.ERROR_NOT_FOUND);
        }
        if(schedule.getVotingDeadline() == null){
            logger.info(Constants.SCHEDULE.SESSION_CLOSED);
            throw new MainException(Constants.SCHEDULE.SESSION_CLOSED);
        }
        if(!timeHaveNotExceeded(vote)){
            logger.info(Constants.VOTE.ERROR_TIMEOUT);
            throw new MainException(Constants.VOTE.ERROR_TIMEOUT);
        }
        if(!userHaveNotVotedYet(vote)){
            logger.info(Constants.VOTE.ERROR_ALREADY_VOTED);
            throw new MainException(Constants.VOTE.ERROR_ALREADY_VOTED);
        }
        logger.info(Constants.VOTE.VOTE_RETURNED);
        return voteRepository.save(vote);
    }

    private boolean userHaveNotVotedYet(Vote vote) {
        User user = userRepository.findById(vote.getUser()).get();
        Schedule schedule = scheduleRepository.findById(vote.getSchedule()).get();

        List<Vote> votes = voteRepository.findByUserAndSchedule(user, schedule);

        return votes.size() == 0;
    }

    private boolean timeHaveNotExceeded(Vote vote){
        Optional<Schedule> schedule = scheduleRepository.findById(vote.getSchedule());
        if(schedule.isPresent()){
            Schedule s = schedule.get();
            Date votingDeadline = s.getVotingDeadline();
            Date now = new Date();
            if(now.after(votingDeadline)){
                return false;
            }
        }
        return true;
    }

}
