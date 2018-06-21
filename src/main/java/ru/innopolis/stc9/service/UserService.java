package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.person.PersonDao;
import ru.innopolis.stc9.db.dao.roles.RoleDao;
import ru.innopolis.stc9.db.dao.users.UsersDao;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.pojo.Role;
import ru.innopolis.stc9.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private static final Logger logger = Logger.getLogger(UserService.class);
    private static final Logger loggerError = Logger.getLogger(UserService.class);
    private static final String ROLE_FROM_ROLES_FOR_SECURITY_USERS_ROLE = "Студент";


    private UsersDao userDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UsersDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void update(User user) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + user.getId());
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
        try {
            userDao.add(user);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
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
    public void addUsers(String login, String pass, String role, Person person) {
        logger.debug("Save a new user " + login);
        String cryptPassword = bcryptEncoder.encode(pass);
        int enabled = (role.equals("ROLE_USER") || role.equals("ROLE_ADMIN")) ? 1 : 0;
        userDao.addUsers(login, cryptPassword, role, enabled, person.getId());
        logger.info("add user");
    }

    @Override
    public String getUserSecurityRole(String name) {
        logger.debug("Person " + name + " wants to register.");
        String result = null;
        if (name != null && !name.isEmpty()) {
            try {
                Person person = personDao.getByName(name);
                if (person != null) {
                    Role properRole = person.getRole();
                    List<Role> roles = roleDao.getAll();
                    if (properRole != null && roles.contains(properRole)) {
                        result = properRole.equals(roleDao.getByName(ROLE_FROM_ROLES_FOR_SECURITY_USERS_ROLE)) ? "ROLE_USER" : "ROLE_ADMIN";
                    }
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("Input argument is empty.");
        }
        logger.info(result != null ? "The user with " + name + " is " + result : "The system does not assume the user with the specified data.");
        return result;
    }
}
