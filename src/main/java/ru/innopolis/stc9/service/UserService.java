package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.users.UsersDao;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final Logger loggerError = Logger.getLogger(UserService.class);

    @Autowired
    private UsersDao userDao;


    @Override
    public void update(User user) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + user.getId());
        user = hashPass(user);
        try {
            userDao.update(user);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + user.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + user.getId());
    }

    @Override
    public User getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        User user = null;
        try {
            user = userDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return user;
    }

    @Override
    public User getUserByPerson(Person person) {
        logger.info(this.getClass().getName() + " method getUserByPerson started, id = " + person.getId());
        User user = null;
        try {
            user = userDao.getById(person.getId());
        } catch (SQLException e) {
            loggerError.error("Error at method getUserByPerson, id = " + person.getId(), e);
        }
        logger.info(this.getClass().getName() + " method getUserByPerson stoped, id = " + person.getId() + ". Success? " + user == null);
        return user;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            userDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(User user) {
        logger.info(this.getClass().getName() + " method add started");
        user = hashPass(user);
        try {
            userDao.add(user);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    /**
     * Хэшируем пароль
     *
     * @param user
     * @return
     */
    private User hashPass(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return user;
    }

    @Override
    public List<User> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<User> userList = new ArrayList<>();
        try {
            userList = userDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return userList;
    }


    @Override
    public boolean checkLogin(User user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }
}
