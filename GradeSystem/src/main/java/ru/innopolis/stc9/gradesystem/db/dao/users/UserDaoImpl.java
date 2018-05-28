package ru.innopolis.stc9.gradesystem.db.dao.users;

import ru.innopolis.stc9.gradesystem.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.gradesystem.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByLogin(String login) throws SQLException {
        User result = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                "* FROM users " +
                "WHERE login=?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            result = new User(resultSet.getInt("id")
                    , resultSet.getString("login")
                    , resultSet.getString("passwordHash")
                    , resultSet.getInt("role_id"));
        }

        connection.close();
        return result;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User result = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                "* FROM users " +
                "WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            result = new User(resultSet.getInt("id")
                    , resultSet.getString("login")
                    , resultSet.getString("passwordHash")
                    , resultSet.getInt("role_id")
            );
        }

        connection.close();
        return result;
    }
}
