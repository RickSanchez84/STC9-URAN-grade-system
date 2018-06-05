package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.User;

import java.util.List;

public interface IUserService {
    void update(User user);

    User getById(long id);

    void deleteById(long id);

    void add(User user);

    List<User> getAll();
}
