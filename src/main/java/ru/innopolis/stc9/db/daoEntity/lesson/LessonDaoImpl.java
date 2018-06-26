package ru.innopolis.stc9.db.daoEntity.lesson;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.entity.Lesson;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LessonDaoImpl implements LessonDao {
    private static final Logger logger = Logger.getLogger(LessonDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    @Override
    public Lesson getById(long id) {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        Session session = factory.openSession();
        Lesson lesson = session.get(Lesson.class, id);
        session.close();
        logger.info("Class LessonDaoImpl method getById finished, id = " + id);
        return lesson;
    }

    @Override
    public Lesson getBySheduleId(long id) throws SQLException {
        Lesson result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE shedule_item= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Lesson lesson = new Lesson(
                            resultSet.getLong("id")
                            , resultSet.getLong("scheduleItem")
                            , resultSet.getDate("date")
                            , resultSet.getString("theme")
                            , resultSet.getString("homework"));
                    result = lesson;
                }
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        return result;
    }

    @Override
    public Lesson getByTeacherId(long id) throws SQLException {
        Lesson result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE teacher_item= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Lesson lesson = new Lesson(
                    resultSet.getLong("id")
                    , resultSet.getLong("scheduleItem")
                    , resultSet.getDate("date")
                    , resultSet.getString("theme")
                    , resultSet.getString("homework"));

            result = lesson;
        }
        return result;
    }

    @Override
    public List<Lesson> getAll() {
        List<Lesson> result;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> criteriaQuery = criteriaBuilder.createQuery(Lesson.class);
        Root<Lesson> root = criteriaQuery.from(Lesson.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;

    }

    @Override
    public void add(Lesson lesson) {
        logger.info("Class LessonDaoImpl method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(lesson);
        session.getTransaction().commit();
        session.close();
        logger.info("Class LessonDaoImpl method add finished");
    }

    @Override
    public void update(Lesson lesson) {
        logger.info("Class LessonDaoImpl method update started, id = " + lesson.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(lesson);
        session.getTransaction().commit();
        session.close();

        logger.info("Class LessonDaoImpl method update finished, id = " + lesson.getId());
    }


    @Override
    public void deleteById(long id) {
        logger.info("Class LessonDaoImpl method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        Lesson lesson = session.load(Lesson.class, id);
        session.delete(lesson);
        session.getTransaction().commit();
        session.close();
        logger.info("Class LessonDaoImpl method deleteById finished, id = " + id);
    }
}
