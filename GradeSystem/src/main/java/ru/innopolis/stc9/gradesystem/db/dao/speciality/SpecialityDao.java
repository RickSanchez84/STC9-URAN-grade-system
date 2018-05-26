package ru.innopolis.stc9.gradesystem.db.dao.speciality;

import ru.innopolis.stc9.gradesystem.pojo.Speciality;

import java.sql.SQLException;

public interface SpecialityDao {
    Speciality getByID(int id) throws SQLException;
}
