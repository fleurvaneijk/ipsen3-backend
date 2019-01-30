package nl.hsleiden.model;

public class DilemmaAdminAsAttributes {


    private Integer _dilemmaId;
    private String _subject;
    private boolean _pregnant;
    private int _weekNr;
    private String _textLeft;
    private String _textRight;
    private String _imagePathLeft;
    private String _imagePathRight;
    private String _link;
    private int _ratingTime;
    private int _ratingDilemma;

    public DilemmaAdminAsAttributes() {
    }

    public Integer get_dilemmaId() {
        return _dilemmaId;
    }

    public void set_dilemmaId(Integer _dilemmaId) {
        this._dilemmaId = _dilemmaId;
    }

    public String get_subject() {
        return _subject;
    }

    public void set_subject(String _subject) {
        this._subject = _subject;
    }

    public boolean is_pregnant() {
        return _pregnant;
    }

    public void set_pregnant(boolean _pregnant) {
        this._pregnant = _pregnant;
    }

    public int get_weekNr() {
        return _weekNr;
    }

    public void set_weekNr(int _weekNr) {
        this._weekNr = _weekNr;
    }

    public String get_textLeft() {
        return _textLeft;
    }

    public void set_textLeft(String _textLeft) {
        this._textLeft = _textLeft;
    }

    public String get_textRight() {
        return _textRight;
    }

    public void set_textRight(String _textRight) {
        this._textRight = _textRight;
    }

    public String get_imagePathLeft() {
        return _imagePathLeft;
    }

    public void set_imagePathLeft(String _imagePathLeft) {
        this._imagePathLeft = _imagePathLeft;
    }

    public String get_imagePathRight() {
        return _imagePathRight;
    }

    public void set_imagePathRight(String _imagePathRight) {
        this._imagePathRight = _imagePathRight;
    }

    public String get_link() {
        return _link;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public int get_ratingTime() {
        return _ratingTime;
    }

    public void set_ratingTime(int _ratingTime) {
        this._ratingTime = _ratingTime;
    }

    public int get_ratingDilemma() {
        return _ratingDilemma;
    }

    public void set_ratingDilemma(int _ratingDilemma) {
        this._ratingDilemma = _ratingDilemma;
    }
}
