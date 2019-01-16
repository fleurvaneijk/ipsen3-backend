package nl.hsleiden.persistence;

import nl.hsleiden.database.Database;
import nl.hsleiden.model.Dilemma;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DilemmaDAO {

    private Database database;

    public DilemmaDAO(){

    }

    public List<Dilemma> getAll(){
        List<Dilemma> dilemmas = new ArrayList<>();
        String SQL = "SELECT*FROM dilemma";
        PreparedStatement statement = null;
        ResultSet rs = null;
    }



}
