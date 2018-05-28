package ru.innopolis.stc9.gradesystem.db.dao.users;

import ru.innopolis.stc9.gradesystem.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    public User getUserByLogin(String login) throws SQLException;

    public User getUserById(int id) throws SQLException;
}
