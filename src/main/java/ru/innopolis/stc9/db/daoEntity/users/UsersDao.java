package ru.innopolis.stc9.db.daoEntity.users;

import ru.innopolis.stc9.entity.User;

import java.util.List;

public interface UsersDao {
    User getById(long id);

    User getByName(String name);

    List<User> getAll();

    void add(User user);

    void update(User user);

    void deleteById(long id);

    void addUsers(String login, String password, String role);
}
