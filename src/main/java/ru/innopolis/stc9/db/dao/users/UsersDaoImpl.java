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
    public  final String ClassName= this.getClass().getName();

    @Override
    public User getByPersonId(long personId) throws SQLException {
        logger.info("Class " + ClassName + " method getByPersonId started, person_id = " + personId);
        String sqlQuery = "select * from users where person_id = ?";
        User result = getUserByLong(personId, sqlQuery);
        logger.info("Class " + ClassName + " method getByPersonId finished, person_id = " + personId + ". Success? " + result != null);
        return result;
    }

    @Override
    public User getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        String sqlQuery = "SELECT * FROM user WHERE id= ?";
        User result = getUserByLong(id, sqlQuery);
        logger.info("Class " + ClassName + " method getById finished, id = " + id + ". Success? " + result != null);
        return result;
    }

    /**
     * Некий универсальный метод, который достает пользователя по sql-запросу, осуществляющему поиск по некому параметру типа long
     *
     * @param longs
     * @param sqlQuery
     * @return
     * @throws SQLException
     */
    private User getUserByLong(long longs, String sqlQuery) throws SQLException {

        User user = null;

        int someLong = (int) longs;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlQuery)) {
                preparedStatement.setInt(1, someLong);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User(
                                resultSet.getLong("id")
                                , resultSet.getString("login")
                                , resultSet.getString("password")
                                , resultSet.getLong("person_id"));
                    }
                }
            }
        }
        return user;
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
                        User user = new User(
                                resultSet.getLong("id")
                                , resultSet.getString("login")
                                , resultSet.getString("password")
                                , resultSet.getLong("person_id"));
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
                        User user = new User(
                                resultSet.getLong("id")
                                , resultSet.getString("login")
                                , resultSet.getString("password")
                                , resultSet.getLong("person_id"));
                        result.add(user);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(User user) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "insert into users (login, password, person_id) VALUES (?,?,?)";

        execureStatement(user, sql);
        logger.info("Class "+ClassName+" method add finished");
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
        logger.info("Class "+ClassName+" method update started, id = " + user.getId());

        String sql = "UPDATE user SET login = ?, password = ?, person_id= ? WHERE id = "+user.getId()+"";

        execureStatement(user, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + user.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM user WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
