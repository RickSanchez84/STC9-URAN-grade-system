package ru.innopolis.stc9.service;

import ru.innopolis.stc9.entity.Role;

import java.util.List;

public interface IRoleService {
    void update(Role role);

    Role getById(long id);

    void deleteById(long id);

    void add(Role role);

    List<Role> getAll();
}
