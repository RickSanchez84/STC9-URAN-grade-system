package ru.innopolis.stc9.gradesystem.db.dao.students;

import ru.innopolis.stc9.gradesystem.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentsDao {
    Student getById(int id) throws SQLException;

    Student getByName(String name) throws SQLException;

    List<Student> getAll() throws SQLException;

    void add(Student student) throws SQLException;

    void update(Student student) throws SQLException;

    void deleteById(int id) throws SQLException;
}
