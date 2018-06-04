package ru.innopolis.stc9.db.dao.person;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {
    private static final Logger logger = Logger.getLogger(PersonDaoImpl.class);

    @Override
    public Person getById(long id) throws SQLException {
        logger.info("Class PersonDaoImpl method getById started, id = " + id);
        Person person = null;
        ResultSet resultSet;
        int iid = (int)id;
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE id= ?");
            preparedStatement.setInt(1, iid);
            resultSet = preparedStatement.executeQuery();
        }
        if (resultSet.next()) {
            person = new Person(
                    resultSet.getLong("id")
                    , resultSet.getString("name")
                    , resultSet.getDate("birthday")
                    , resultSet.getString("address"));
        }
        logger.info("Class PersonDaoImpl method getById finished, id = " + id);
        return person;
    }

    @Override
    public Person getByName(String name) throws SQLException {
        Person result = null;
        ResultSet resultSet;
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM persons WHERE name= ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
        }

        while (resultSet.next()) {
            Person person = new Person(
                    resultSet.getLong("id")
                    , resultSet.getString("name")
                    , resultSet.getDate("birthday")
                    , resultSet.getString("address"));
            result = person;
        }
        return result;
    }

    @Override
    public List<Person> getAll() throws SQLException {
        ArrayList<Person> result = new ArrayList<>();

        ResultSet resultSet;
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM persons");
            resultSet = preparedStatement.executeQuery();
        }
        while (resultSet.next()) {
            Person person = new Person(
                    resultSet.getLong("id")
                    , resultSet.getString("name")
                    , resultSet.getDate("birthday")
                    , resultSet.getString("address"));
            result.add(person);
        }
        return result;
    }

    @Override
    public void add(Person person) throws SQLException {
        logger.info("Class PersonDaoImpl method add started");

        String sql = "INSERT INTO persons (name,birthday,address) VALUES (?,?,?)";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setDate(2, person.getBirthday());
            statement.setString(3, person.getAddress());
            statement.executeUpdate();
        }
        logger.info("Class PersonDaoImpl method add finished");
    }

    @Override
    public void update(Person person) throws SQLException {
        logger.info("Class PersonDaoImpl method update started, id = " + person.getId());

        String sql = "UPDATE persons SET name = ?, birthday = ?, address  = ? WHERE id = ?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setDate(2, person.getBirthday());
            statement.setString(3, person.getAddress());
            statement.setLong(4, person.getId());
            statement.executeUpdate();
        }
        logger.info("Class PersonDaoImpl method update finished, id = " + person.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class PersonDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM persons WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        logger.info("Class PersonDaoImpl method deleteById finished, id = " + id);
    }
}
