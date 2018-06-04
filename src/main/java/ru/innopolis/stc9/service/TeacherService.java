package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc9.db.dao.teachers.TeachersDao;
import ru.innopolis.stc9.pojo.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherService implements ITeacherService {
    private static final Logger logger = Logger.getLogger(TeacherService.class);
    private static final Logger loggerError = Logger.getLogger(TeacherService.class);

    @Autowired
    private TeachersDao teacherDao;

    @Override
    public void update(Teacher teacher) {
        logger.info(this.getClass().getName() + " method update started, id = " + teacher.getId());
        try {
            teacherDao.update(teacher);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + teacher.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + teacher.getId());
    }

    @Override
    public Teacher getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Teacher teacher = null;
        try {
            teacher = teacherDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return teacher;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            teacherDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Teacher teacher) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            teacherDao.add(teacher);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Teacher> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Teacher> teacherList = new ArrayList<>();
        try {
            teacherList = teacherDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return teacherList;
    }
}
