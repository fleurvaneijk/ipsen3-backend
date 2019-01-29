package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.sql.Date;

public class Answer {
    private String _parent_email;
    private int _dilemma_id;
    private String _answered_time;
    private int _answer;

    public Answer(String _parent_email, int _dilemma_id, String _answered_time, int _answer) {
        this._parent_email = _parent_email;
        this._dilemma_id = _dilemma_id;
        this._answered_time = _answered_time;
        this._answer = _answer;
    }

    public Answer() {

    }

    public String get_parent_email() {
        return _parent_email;
    }

    public void set_parent_email(String _parent_email) {
        this._parent_email = _parent_email;
    }

    public int get_dilemma_id() {
        return _dilemma_id;
    }

    public void set_dilemma_id(int _dilemma_id) {
        this._dilemma_id = _dilemma_id;
    }

    public String get_answered_time() {
        return _answered_time;
    }

    public void set_answered_time(String _answered_time) {
        this._answered_time = _answered_time;
    }

    public int get_answer() {
        return _answer;
    }

    public void set_answer(int _answer) {
        this._answer = _answer;
    }
}


