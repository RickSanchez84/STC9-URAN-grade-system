package ru.innopolis.stc9.db.daoEntity.performance;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.entity.Performance;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PerformanceDaoImpl implements PerformanceDao {
    private static final Logger logger = Logger.getLogger(PerformanceDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    @Override
    public Performance getById(long id) {
        logger.info("Class PerformanceDaoImpl method getById started, id = " + id);
        Session session = factory.openSession();
        Performance performance = session.get(Performance.class, id);
        session.close();
        return performance;
    }

    @Override
    public Performance getByStudentId(long id) throws SQLException {
        Performance result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance WHERE student= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Performance performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong("mark")
            );
            result = performance;
        }
        return result;
    }

    @Override
    public Performance getByLessonId(long id) throws SQLException {
        Performance result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM academic_performance WHERE lesson= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Performance performance = new Performance(
                    resultSet.getLong("id")
                    , resultSet.getLong("student")
                    , resultSet.getLong("lesson")
                    , resultSet.getBoolean("on_lesson")
                    , resultSet.getLong("mark")
            );

            result = performance;
        }
        return result;
    }

    @Override
    public List<Performance> getAll() {
        List<Performance> result;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Performance> criteriaQuery = criteriaBuilder.createQuery(Performance.class);
        Root<Performance> userRoot = criteriaQuery.from(Performance.class);
        criteriaQuery.select(userRoot);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(Performance performance) {
        logger.info("Class PerformanceDaoImpl method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(performance);
        session.getTransaction().commit();
        session.close();

        logger.info("Class PerformanceDaoImpl method add finished");
    }

    @Override
    public void update(Performance performance) {
        logger.info("Class PerformanceDaoImpl method update started, id = " + performance.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(performance);
        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void deleteById(long id) {
        logger.info("Class PerformanceDaoImpl method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        Performance performance = session.load(Performance.class, id);
        session.delete(performance);
        session.getTransaction().commit();
        session.close();
        logger.info("Class PerformanceDaoImpl method deleteById finished, id = " + id);
    }
}
