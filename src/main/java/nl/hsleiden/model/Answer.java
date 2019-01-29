package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.sql.Date;

public class Answer {
    private String parent_email;
    private int dilemma_id;
    private Date answered_time;
    private int answer;

    public Answer(String parent_email, int dilemma_id, Date answered_time, int answer) {
        this.parent_email = parent_email;
        this.dilemma_id = dilemma_id;
        this.answered_time = answered_time;
        this.answer = answer;
    }

    public Answer() {

    }

    public String getParent_email() {
        return parent_email;
    }

    public void setParent_email(String parent_email) {
        this.parent_email = parent_email;
    }

    public int getDilemma_id() {
        return dilemma_id;
    }

    public void setDilemma_id(int dilemma_id) {
        this.dilemma_id = dilemma_id;
    }

    public Date getAnswered_time() {
        return answered_time;
    }

    public void setAnswered_time(Date answered_time) {
        this.answered_time = answered_time;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}


