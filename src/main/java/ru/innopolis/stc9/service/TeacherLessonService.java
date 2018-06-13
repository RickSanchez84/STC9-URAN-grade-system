package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.teacherLesson.TeacherLessonDao;
import ru.innopolis.stc9.pojo.TeacherLesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherLessonService implements ITeacherLessonService {
    private static final Logger logger = Logger.getLogger(TeacherLessonService.class);
    private static final Logger loggerError = Logger.getLogger(TeacherLessonService.class);

    @Autowired
    private TeacherLessonDao teacherDao;

    @Override
    public void update(TeacherLesson teacherLesson) {
        logger.info(this.getClass().getName() + " method update started, id = " + teacherLesson.getId());
        try {
            teacherDao.update(teacherLesson);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + teacherLesson.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + teacherLesson.getId());
    }

    @Override
    public TeacherLesson getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        TeacherLesson teacherLesson = null;
        try {
            teacherLesson = teacherDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return teacherLesson;
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
    public void add(TeacherLesson teacherLesson) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            teacherDao.add(teacherLesson);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<TeacherLesson> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<TeacherLesson> teacherLessonList = new ArrayList<>();
        try {
            teacherLessonList = teacherDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return teacherLessonList;
    }
}
