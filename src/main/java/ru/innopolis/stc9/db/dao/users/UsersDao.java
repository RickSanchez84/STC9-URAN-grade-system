package ru.innopolis.stc9.db.dao.users;
import ru.innopolis.stc9.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
    User getById(long id) throws SQLException;

    User getByName(String name) throws SQLException;

    List<User> getAll() throws SQLException;

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    void deleteById(long id) throws SQLException;

    long addUsers(String login, String password, String role, int enabled, long personId);
}
