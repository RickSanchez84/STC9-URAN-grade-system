package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.schedule.ScheduleDao;
import ru.innopolis.stc9.pojo.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private static final Logger logger = Logger.getLogger(ScheduleService.class);
    private static final Logger loggerError = Logger.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleDao scheduleDao;

    public void updateById(Schedule schedule) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + schedule.getId());
        try {
            scheduleDao.update(schedule);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + schedule.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + schedule.getId());
    }

    public Schedule getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Schedule schedule = null;
        try {
            schedule = scheduleDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return schedule;
    }

    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            scheduleDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    public void add(Schedule schedule) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            scheduleDao.add(schedule);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    public List<Schedule> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Schedule> scheduleList = new ArrayList<>();
        try {
            scheduleList = scheduleDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return scheduleList;
    }
}
