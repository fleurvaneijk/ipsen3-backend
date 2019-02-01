package nl.hsleiden.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Child;
import nl.hsleiden.model.Couple;
import nl.hsleiden.persistence.CoupleDAO;
import nl.hsleiden.persistence.CoupleManagementDAO;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

/**
 * Connects the DAO to the resource and handles logic
 * @author Robin Silverio & Fleur van Eijk & Joost de Winter & Dennis van Beelen
 */
@Singleton
public class CoupleService extends BaseService<Couple> {
    private final CoupleDAO dao;
    private final CoupleManagementDAO cmDao;
    private final ChildService childService;

    @Inject
    public CoupleService(CoupleDAO dao, CoupleManagementDAO cmDao, ChildService childService)
    {
        this.childService = childService;
        this.dao = dao;
        this.cmDao = cmDao;
        this.dao.setDatabase(ApiApplication.getDatabase());
        this.cmDao.setDatabase(ApiApplication.getDatabase());
    }

    public List<Couple> getAll() {
        return dao.getAll();
    }

    public Couple get(String id) {
        return requireResult(dao.get(id));
    }

    public void add(Couple user) {
        dao.add(user);
    }

    public void update(String id, Couple couple) {
        dao.update(id, couple);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public List getCoupleTableInfo() {
        ArrayList<ArrayList> coupleTableInfo = cmDao.getCoupleTableInfo();
        for (int i = 0; i < coupleTableInfo.size(); i++) {
            int coupleId = (int) coupleTableInfo.get(i).get(0);
            Date childBirthdate = cmDao.getBirthdate(coupleId);
            if (childBirthdate == null) {
                coupleTableInfo.get(i).add(null);
            } else {
                DateTime dateTime = new DateTime(childBirthdate);
                DateTime today = new DateTime();
                int weeks = Weeks.weeksBetween(dateTime, today).getWeeks();
                coupleTableInfo.get(i).add(weeks);
            }
        }
        return coupleTableInfo;
    }

    public void updatePregnant(String email, boolean pregnant) {
        cmDao.changePregnant(dao.getCoupleId(email), pregnant);
    }

    public void updatePregnantWeeks(String email, int weeks) {
        cmDao.changePregnantWeeks(dao.getCoupleId(email), weeks);
    }

    public void updateBirthdate(String email, String birthdate) {
        int coupleId = dao.getCoupleId(email);
        if (childService.getChildExistsByCoupleId(coupleId) == false) { // If child doesnt exist
            childService.add(new Child(email, birthdate));   // Make new child
        }
        else {
            Date dateBirthdate = null;
            try {
                dateBirthdate = new SimpleDateFormat("yyyy-mm-dd").parse(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            childService.updateBirthdate(coupleId, dateBirthdate);
        }
    }

    public void updateCouplePregnant(String email2, boolean pregnant, int weeks_pregnant) {
        cmDao.changeCouplePregnant(email2, pregnant, weeks_pregnant);
    }

    public void updateFullCouple(Couple couple) {
        cmDao.changeCoupleComplete(couple.getParentMail2(), couple.getWeeksPregnant(), couple.getLastAnswerWeekNo());
    }
}