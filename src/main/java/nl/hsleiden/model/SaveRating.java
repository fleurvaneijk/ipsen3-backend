package nl.hsleiden.model;

public class SaveRating {
    private int _id;
    private String _parent_email;
    private int _dilemma_id;
    private int _rating_time;
    private int _rating_dilemma;

    public SaveRating(int _id, String _parent_email, int _dilemma_id, int _rating_time, int _rating_dilemma) {
        this._id = _id;
        this._parent_email = _parent_email;
        this._dilemma_id = _dilemma_id;
        this._rating_time = _rating_time;
        this._rating_dilemma = _rating_dilemma;
    }

    public SaveRating() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public int get_rating_time() {
        return _rating_time;
    }

    public void set_rating_time(int _rating_time) {
        this._rating_time = _rating_time;
    }

    public int get_rating_dilemma() {
        return _rating_dilemma;
    }

    public void set_rating_dilemma(int _rating_dilemma) {
        this._rating_dilemma = _rating_dilemma;
    }
}
