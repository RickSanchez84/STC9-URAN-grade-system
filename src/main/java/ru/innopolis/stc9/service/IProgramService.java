package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Program;
import ru.innopolis.stc9.pojo.Speciality;

import java.util.List;

public interface IProgramService {
    void update(Program program);

    Program getById(long id);

    void deleteById(long id);

    long add(Program program);

    List<Program> getAll();

    /**
     * Get list of planned subjects for the specialty.
     *
     * @param speciality
     * @return
     */
    List<Program> getBySpecialty(Speciality speciality);
}
