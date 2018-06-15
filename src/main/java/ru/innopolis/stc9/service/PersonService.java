package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.person.PersonDao;
import ru.innopolis.stc9.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {
    private static final Logger logger = Logger.getLogger(IPersonService.class);
    private static final Logger loggerError = Logger.getLogger(IPersonService.class);

    @Autowired
    private PersonDao personDao;

    @Override
    public void updateById(Person person) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + person.getId());
        try {
            personDao.update(person);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + person.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + person.getId());
    }

    @Override
    public Person getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Person person = null;
        try {
            person = personDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return person;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            personDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Person person) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            personDao.add(person);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Person> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Person> personList = new ArrayList<>();
        try {
            personList = personDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return personList;
    }

    @Override
    public List<Person> getTeachers() {
        logger.info(this.getClass().getName() + " method getTeachers started");
        List<Person> teacherList = new ArrayList<>();
        try {
            //добавить enum или константы
            teacherList = personDao.getPersonByRole(3);
        } catch (SQLException e) {
            loggerError.error("Error at method getTeachers", e);
        }
        logger.info(this.getClass().getName() + " method getTeachers finished");
        return teacherList;
    }
}