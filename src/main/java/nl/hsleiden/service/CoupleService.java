package nl.hsleiden.service;

import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.ApiApplication;
import nl.hsleiden.model.Couple;
import nl.hsleiden.persistence.CoupleDAO;
import nl.hsleiden.persistence.CoupleManagementDAO;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;


/**
 *
 * @author Robin Silverio
 */
@Singleton
public class CoupleService extends BaseService<Couple>
{
    private final CoupleDAO dao;
    private final CoupleManagementDAO cmDao;

    @Inject
    public CoupleService(CoupleDAO dao, CoupleManagementDAO cmDao)
    {
        this.dao = dao;
        this.cmDao = cmDao;
        this.dao.setDatabase(ApiApplication.getDatabase());
        this.cmDao.setDatabase(ApiApplication.getDatabase());
    }

    public List<Couple> getAll()
    {
        return dao.getAll();
    }

    public Couple get(String id)
    {
        return requireResult(dao.get(id));
    }

    public void add(Couple user)
    {
        dao.add(user);
    }

    public void update(String id, Couple couple)
    {
        // Controleren of deze gebruiker wel bestaat
        Couple oldCouple = get(id);
        dao.update(id, couple);
    }

    public void delete(int id)
    {
        dao.delete(id);
    }

    public List getCoupleTableInfo()
    {
        ArrayList<ArrayList> coupleTableInfo = cmDao.getCoupleTableInfo();
        for(int i = 0 ; i < coupleTableInfo.size() ; i++){
            int coupleId = (int)coupleTableInfo.get(i).get(0);
            Date childBirthdate = cmDao.getBirthdate(coupleId);
            if(childBirthdate == null){
                coupleTableInfo.get(i).add(null);
            }
            else {
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

    public void updateCouplePregnant(String email2, boolean pregnant, int weeks_pregnant){
        cmDao.changeCouplePregnant(email2, pregnant, weeks_pregnant);
    }
}