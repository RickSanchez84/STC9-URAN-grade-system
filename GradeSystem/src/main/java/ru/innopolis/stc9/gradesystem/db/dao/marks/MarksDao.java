package ru.innopolis.stc9.gradesystem.db.dao.marks;

import ru.innopolis.stc9.gradesystem.pojo.Mark;

import java.sql.SQLException;
import java.util.List;

public interface MarksDao {
    Mark getById(int id) throws SQLException;

    List<Mark> getAllByStudent(int category) throws SQLException;
}
