package ru.innopolis.stc9.db.daoEntity.users;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository

public class UsersDaoImpl implements UsersDao {
    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);
    public final String ClassName = this.getClass().getName();

    @Autowired
    private SessionFactory factory;

    @Override
    public User getById(long id) {
        logger.info("Class " + ClassName + " method getById started, id = " + id);

        Session session = factory.openSession();
        User user = session.get(User.class, id);
        session.close();


        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return user;
    }


    @Override
    public User getByName(String name) {
        if (name.isEmpty())
            return null;
        List<User> userArrayList;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get(name), name));
        userArrayList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return (User) userArrayList;
    }


    @Override
    public List<User> getAll() {
        List<User> result;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot);
            result = session.createQuery(criteriaQuery).getResultList();
        }
        return result;
    }

    @Override
    public void add(User user) {
        logger.info("Class " + ClassName + " method add started");

        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method add finished");
    }


    @Override
    public void update(User user) {
        logger.info("Class " + ClassName + " method update started, id = " + user.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method update finished, id = " + user.getId());
    }

    @Override
    public void deleteById(long id) {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);

        Session session = factory.openSession();
        session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();

        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }

    @Override
    public void addUsers(String login, String password, String role) {
        logger.info("add users start ");
        User user = new User();
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<User> criteriaAdd = criteriaBuilder.createCriteriaUpdate(User.class);
            Root<User> userRoot = criteriaAdd.from(User.class);
            criteriaAdd.set(login, user.getLogin()).
                    set(password, user.getPassword()).
                    set(role, user.getRole());
            session.beginTransaction();
            int result = session.createQuery(criteriaAdd).executeUpdate();
            session.close();
        }

        logger.info("add users finish");
    }
}
