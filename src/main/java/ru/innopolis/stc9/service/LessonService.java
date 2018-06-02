package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.lesson.LessonDao;
import ru.innopolis.stc9.pojo.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    private static final Logger logger = Logger.getLogger(LessonService.class);
    private static final Logger loggerError = Logger.getLogger(LessonService.class);

    @Autowired
    private LessonDao lessonDao;

    public void updateById(Lesson lesson) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + lesson.getId());
        try {
            lessonDao.update(lesson);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + lesson.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + lesson.getId());
    }

    public Lesson getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Lesson lesson = null;
        try {
            lesson = lessonDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return lesson;
    }

    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            lessonDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    public void add(Lesson lesson) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            lessonDao.add(lesson);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    public List<Lesson> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Lesson> lessonList = new ArrayList<>();
        try {
            lessonList = lessonDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return lessonList;
    }
}
