package ru.innopolis.stc9.db.dao.users;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDaoImpl implements UsersDao {
    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);
    private final String className = this.getClass().getName();

    @Override
    public User getById(long id) throws SQLException {
        logger.info(className + " method getById started, id = " + id);
        User user = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = parseUserFromTable(resultSet);
                    }
                }                
            }
        }

        logger.info(className + " method getById finished, id = " + id);
        return user;
    }

    private User parseUserFromTable(ResultSet resultSet) throws SQLException {
        logger.debug("Parse object \'User\' from table \'user\'");
        return new User(
                resultSet.getLong("id")
                , resultSet.getString("login")
                , resultSet.getString("password")
                , resultSet.getLong("person_id"));
    }

    @Override
    public User getByName(String name) throws SQLException {
        User result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        User user = parseUserFromTable(resultSet);
                        result = user;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<User> getAll() throws SQLException {
        ArrayList<User> result = new ArrayList<>();
      
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = parseUserFromTable(resultSet);
                        result.add(user);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(User user) throws SQLException {
        logger.info(className + " method add started");

        String sql = "INSERT INTO user (user_item, subject_item) VALUES (?,?)";

        execureStatement(user, sql);
        logger.info(className + " method add finished");
    }

    private void execureStatement(User user, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setLong(3, user.getPersonId());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(User user) throws SQLException {
        logger.info(className + " method update started, id = " + user.getId());

        String sql = "UPDATE user SET login = ?, password = ?, person_id= ? WHERE id = "+user.getId()+"";

        execureStatement(user, sql);
        logger.info(className + " method update finished, id = " + user.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + className + " method deleteById started, id = " + id);
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class " + className + " method deleteById finished, id = " + id);
    }

    @Override
    public void addUsers(String login, String password, String role, int enabled, long personId) {
        logger.info("add users start ");
        try (Connection connection = new ConnectionManagerImpl().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users(login, password, role, enabled, personid) VALUES (?,?,?,?,?)")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.setInt(4, enabled);
            preparedStatement.setLong(5, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        logger.info("add users finish");
    }
}
