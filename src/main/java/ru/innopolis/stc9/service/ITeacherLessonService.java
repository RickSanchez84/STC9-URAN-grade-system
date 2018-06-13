package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.TeacherLesson;

import java.util.List;

public interface ITeacherLessonService {
    void update(TeacherLesson teacherLesson);

    TeacherLesson getById(long id);

    void deleteById(long id);

    void add(TeacherLesson teacherLesson);

    List<TeacherLesson> getAll();
}
