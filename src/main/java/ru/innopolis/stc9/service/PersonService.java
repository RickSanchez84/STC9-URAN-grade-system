package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Person;

import java.util.List;

public interface PersonService {
    void updateById(Person person);

    Person getById(long id);

    void deleteById(long id);

    void add(Person person);

    List<Person> getAll();
}
