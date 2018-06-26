package ru.innopolis.stc9.db.daoEntity.speciality;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.Speciality;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpecialityDaoImpl implements SpecialityDao {
    private static final Logger logger = Logger.getLogger(SpecialityDaoImpl.class);
    public final String ClassName = this.getClass().getName();

    @Autowired
    private SessionFactory factory;

    @Override
    public Speciality getById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method getById started, id = " + id);
        Session session = factory.openSession();
        Speciality speciality = session.get(Speciality.class, id);
        session.close();
        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public Speciality getByName(String name) throws SQLException {
        Speciality result = null;


        return result;
    }

    @Override
    public List<Speciality> getAll() throws SQLException {
        Session session = factory.openSession();
        List<Speciality> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Speciality> criteriaQuery = criteriaBuilder.createQuery(Speciality.class);
        Root<Speciality> root = criteriaQuery.from(Speciality.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(Speciality speciality) throws SQLException {
        logger.info("Class " + ClassName + " method add started" + speciality.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.save(speciality);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method add finished");
    }


    @Override
    public void update(Speciality speciality) throws SQLException {
        logger.info("Class " + ClassName + " method update started, id = " + speciality.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(speciality);
        session.getTransaction().commit();
        session.close();

        logger.info("Class " + ClassName + " method update finished, id = " + speciality.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = factory.openSession();
        session.beginTransaction();
        Speciality speciality = session.load(Speciality.class, id);
        session.delete(speciality);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
