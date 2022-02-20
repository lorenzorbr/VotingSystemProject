package com.tests.demo.shared.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date votingDeadline;

    public Schedule(){

    }

    public Schedule(Long id, String description, Date votingDeadline){
        this.id = id;
        this.description = description;
        this.votingDeadline = votingDeadline;
    }

    public Long getId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Date getVotingDeadline(){
        return votingDeadline;
    }

    public void setVotingDeadline(Date votingDeadline) {
        this.votingDeadline = votingDeadline;
    }
}
