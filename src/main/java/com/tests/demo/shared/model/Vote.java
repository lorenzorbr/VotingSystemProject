package com.tests.demo.shared.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Schedule schedule;

    private Boolean vote;

    public Vote(){
        this.vote = false;
    }

    public Vote(Long id, User user, Schedule schedule, Boolean vote){
        this.id = id;
        this.user = user;
        this.schedule = schedule;
        this.vote = vote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user.getId();
    }

    public Long getSchedule() {
        return schedule.getId();
    }


    public Date getScheduleVotingDeadline(){
        return schedule.getVotingDeadline();
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
