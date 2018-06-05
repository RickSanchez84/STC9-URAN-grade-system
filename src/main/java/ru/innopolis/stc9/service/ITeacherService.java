package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Teacher;

import java.util.List;

public interface ITeacherService {
    void update(Teacher teacher);

    Teacher getById(long id);

    void deleteById(long id);

    void add(Teacher teacher);

    List<Teacher> getAll();
}
