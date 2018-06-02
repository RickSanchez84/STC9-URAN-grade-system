package ru.innopolis.stc9.db.dao.teachers;
import ru.innopolis.stc9.pojo.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeachersDao {
    Teacher getById(long id) throws SQLException;

    Teacher getByName(String name) throws SQLException;

    List<Teacher> getAll() throws SQLException;

    void add(Teacher teacher) throws SQLException;

    void update(Teacher teacher) throws SQLException;

    void deleteById(long id) throws SQLException;
}
