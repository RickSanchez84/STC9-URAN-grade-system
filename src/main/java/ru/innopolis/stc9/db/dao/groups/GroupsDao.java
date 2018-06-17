package ru.innopolis.stc9.db.dao.groups;

import ru.innopolis.stc9.pojo.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupsDao {
    Group getById(long id) throws SQLException;

    Group getByProgram(String name) throws SQLException;

    List<Group> getAll() throws SQLException;

    void add(Group group) throws SQLException;

    void update(Group group) throws SQLException;

    void deleteById(long id) throws SQLException;
}
