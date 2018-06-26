package ru.innopolis.stc9.db.daoEntity.lesson;

import ru.innopolis.stc9.entity.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface LessonDao {
    Lesson getById(long id) throws SQLException;

    Lesson getBySheduleId(long id) throws SQLException;

    Lesson getByTeacherId(long id) throws SQLException;

    List<Lesson> getAll() throws SQLException;

    void add(Lesson lesson) throws SQLException;

    void update(Lesson lesson) throws SQLException;

    void deleteById(long id) throws SQLException;
}
