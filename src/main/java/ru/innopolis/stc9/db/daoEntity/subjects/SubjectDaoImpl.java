package ru.innopolis.stc9.db.daoEntity.subjects;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.Subject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class);
    public final String ClassName = this.getClass().getName();
    @Autowired
    private SessionFactory factory;

    @Override
    public Subject getById(long id) throws SQLException {
        Session session = factory.openSession();
        Subject subject = session.get(Subject.class, id);
        session.close();

        logger.info("Class  " + ClassName + " method getById finished, id = " + id);
        return subject;
    }

    @Override
    public Subject getByName(String name) throws SQLException {

        return null;
    }

    @Override
    public List<Subject> getAll() throws SQLException {
        Session session = factory.openSession();
        List<Subject> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(Subject subject) throws SQLException {
        logger.info("Class " + ClassName + " method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method add finished");
    }


    @Override
    public void update(Subject subject) throws SQLException {
        logger.info("Class " + ClassName + " method update started, id = " + subject.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(subject);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method update finished, id = " + subject.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        Subject subject = session.load(Subject.class, id);
        session.delete(subject);
        session.getTransaction().commit();
        session.close();

        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
