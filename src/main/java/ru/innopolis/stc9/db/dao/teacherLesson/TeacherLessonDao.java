package ru.innopolis.stc9.db.dao.teacherLesson;
import ru.innopolis.stc9.pojo.TeacherLesson;

import java.sql.SQLException;
import java.util.List;

public interface TeacherLessonDao {
    TeacherLesson getById(long id) throws SQLException;

    TeacherLesson getByName(String name) throws SQLException;

    List<TeacherLesson> getAll() throws SQLException;

    void add(TeacherLesson teacherLesson) throws SQLException;

    void update(TeacherLesson teacherLesson) throws SQLException;

    void deleteById(long id) throws SQLException;
}
