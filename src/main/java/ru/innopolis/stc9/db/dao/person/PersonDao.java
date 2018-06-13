package ru.innopolis.stc9.db.dao.person;
import ru.innopolis.stc9.pojo.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    Person getById(long id) throws SQLException;

    Person getByName(String name) throws SQLException;

    List<Person> getAll() throws SQLException;

    void add(Person person) throws SQLException;

    void update(Person person) throws SQLException;

    void deleteById(long id) throws SQLException;

    List<Person> getPersonByRole(int role) throws SQLException;
}
