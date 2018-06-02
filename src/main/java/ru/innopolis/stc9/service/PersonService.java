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
public class PersonService {
    private static final Logger logger = Logger.getLogger(PersonService.class);
    private static final Logger loggerError = Logger.getLogger(PersonService.class);

    @Autowired
    private PersonDao studentDao;

    public void updateById(Person person) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + person.getId());
        try {
            studentDao.update(person);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + person.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + person.getId());
    }

    public Person getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Person person = null;
        try {
            person = studentDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return person;
    }

    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            studentDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    public void add(Person person) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            studentDao.add(person);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    public List<Person> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Person> personList = new ArrayList<>();
        try {
            personList = studentDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return personList;
    }
}
