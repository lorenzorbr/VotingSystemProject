package com.tests.demo.shared.dto;



import com.tests.demo.shared.model.Schedule;

public class ResultVoteDto {

    private Schedule schedule;
    private int approve;
    private int reprove;
    private String winner;

    public ResultVoteDto(){

    }

    public void incrementApprove(){
        approve++;
    }

    public void incrementReprove(){
        reprove++;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getReprove() {
        return reprove;
    }

    public void setReprove(int reprove) {
        this.reprove = reprove;
    }

    public String getWinner(){
        return winner;
    }

    public void setWinner(String winner){
        this.winner = winner;
    }
}
