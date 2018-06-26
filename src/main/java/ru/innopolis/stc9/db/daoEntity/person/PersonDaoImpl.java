package ru.innopolis.stc9.db.daoEntity.person;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.entity.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    private static final Logger logger = Logger.getLogger(PersonDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    @Override
    public Person getById(long id) throws SQLException {
        Session session = factory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public Person getByName(String name) throws SQLException {
        if (name.isEmpty())
            return null;
        List<Person> personList;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(name), name));
        personList = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return (Person) personList;

    }

    @Override
    public List<Person> getAll() throws SQLException {
        List<Person> result;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> userRoot = criteriaQuery.from(Person.class);
        criteriaQuery.select(userRoot);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;

    }

    @Override
    public void add(Person person) throws SQLException {
        logger.info("Class PersonDaoImpl method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();

        logger.info("Class PersonDaoImpl method add finished");
    }

    @Override
    public void update(Person person) throws SQLException {
        logger.info("Class PersonDaoImpl method update started, id = " + person.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
        session.close();

        logger.info("Class PersonDaoImpl method update finished, id = " + person.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class PersonDaoImpl method deleteById started, id = " + id);

        Session session = factory.openSession();
        session.beginTransaction();
        Person person = session.load(Person.class, id);
        session.delete(person);
        session.getTransaction().commit();
        session.close();
        logger.info("Class PersonDaoImpl method deleteById finished, id = " + id);
    }

    @Override
    public List<Person> getPersonByRole(int role) throws SQLException {
        ArrayList<Person> result = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM persons WHERE role=role ORDER by id")) {
                try (ResultSet resultSet1 = resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Person person = new Person(
                                resultSet.getLong("id")
                                , resultSet.getString("name")
                                , resultSet.getDate("birthday")
                                , resultSet.getString("email")
                                , resultSet.getInt("role"));
                        result.add(person);
                    }
                }
            }

        }
        return result;
    }
}
