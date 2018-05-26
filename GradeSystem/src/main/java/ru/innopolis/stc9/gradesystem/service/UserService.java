package ru.innopolis.stc9.gradesystem.service;

import ru.innopolis.stc9.gradesystem.db.dao.users.UserDao;
import ru.innopolis.stc9.gradesystem.db.dao.users.UserDaoImpl;
import ru.innopolis.stc9.gradesystem.pojo.User;

import java.sql.SQLException;

public class UserService {
    private static UserDao userDao = new UserDaoImpl();

    public boolean checkAuth(String login, String password) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user != null) && (user.getPasswordHash().equals(password));
    }

    public Integer getRole(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user != null) ? user.getRole() : 0;
    }
}

