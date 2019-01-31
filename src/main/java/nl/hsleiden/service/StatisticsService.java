package nl.hsleiden.service;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.persistence.StatisticsDAO;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fleur van Eijk
 */
public class StatisticsService {

    private final StatisticsDAO dao;

    @Inject
    public StatisticsService(StatisticsDAO dao) {
        this.dao = dao;
        this.dao.setDatabase(ApiApplication.getDatabase());
    }

    public List getRatingPerSubject() {
        return dao.getRatingPerSubject();
    }

    public List getAmountDilemmaAnswersFeedbackClicks() {
        return dao.getAmountDilemmaAnswersFeedbackClicks();
    }

    public ArrayList<String> getAnswerDates(){
        List dateTimes = dao.getAnswerDateTimes();
        String date;
        ArrayList<String> dates = new ArrayList<String>();
        for(Object row : dateTimes){
            String dateTime = row.toString();
            date = dateTime.substring(0, dateTime.indexOf(' '));
            dates.add(date);
        }

        return dates;
    }

    public List getAnswerTimes(){
        List dateTimes = dao.getAnswerDateTimes();
        String time;
        ArrayList<String> times = new ArrayList<String>();
        for(Object row : dateTimes){
            String dateTime = row.toString();
            time = dateTime.substring(dateTime.indexOf(' ') + 1);
            times.add(time);
        }

        return times;
    }
}
