package ru.innopolis.stc9.gradesystem.db.dao.courses;

import ru.innopolis.stc9.gradesystem.pojo.Course;

import java.sql.SQLException;

public interface CoursesDao {
    Course getById(int id) throws SQLException;
}
