package ru.innopolis.stc9.db.daoEntity.roles;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.Role;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
    public final String ClassName = this.getClass().getName();

    @Autowired
    private SessionFactory factory;

    @Override
    public Role getById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method getById started, id = " + id);

        Session session = factory.openSession();
        Role role = session.get(Role.class, id);
        session.close();

        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return role;
    }

    @Override
    public Role getByName(String name) throws SQLException {

        return null;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        Session session = factory.openSession();
        List<Role> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(Role role) throws SQLException {
        logger.info("Class SheduleDaoImpl method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();

        logger.info("Class " + ClassName + " method add finished");
    }


    @Override
    public void update(Role role) throws SQLException {
        logger.info("Class " + ClassName + " method update started, id = " + role.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(role);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method update finished, id = " + role.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        Role role = session.load(Role.class, id);
        session.delete(role);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
