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
    private static final String[] securityRoles = new String[]{"ROLE_USER", "ROLE_ADMIN"};


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

    private boolean addUsers(String personName, String login, String pass, String role, int enabled) {
        logger.debug("Save a new user " + login);
        long result = -1;
        try {
            Person person = personDao.getByName(personName);
            if (person != null) {
                String cryptPassword = bcryptEncoder.encode(pass);
                result = userDao.addUsers(login, cryptPassword, role, enabled, person.getId());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("add user");
        return result > 0;
    }

    private String getUserSecurityRole(String name) {
        logger.debug("Person " + name + " wants to register.");
        String result = null;
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
        logger.info(result != null ? "The user with " + name + " is " + result : "The system does not assume the user with the specified data.");
        return result;
    }

    private int getUserBan(String securityRole) {
        int result = 0;
        for (String s : securityRoles) {
            if (securityRole.equals(s)) {
                result = 1;
                break;
            }
        }
        return result;
    }


    @Override
    public boolean addUserOnRegistration(String personName, String email, String login, String password, String passwordConfirm) {
        logger.debug("Start of the new user registration procedure");
        boolean result = false;
        boolean validation = validateArguments(personName, email, login, password, passwordConfirm) && checkPasswords(password, passwordConfirm);
        if (validation) {
            String securityRole = getUserSecurityRole(personName);
            int enable = getUserBan(securityRole);
            result = addUsers(personName, login, password, securityRole, enable);
        }
        logger.info("Registration is " + (result ? "Success" : "Failed"));
        return result;
    }

    private boolean checkPasswords(String password, String passwordConfirm) {
        logger.debug("compare two passwords");
        boolean result = false;
        result = password.equals(passwordConfirm);
        logger.info("Result = " + result);
        return result;
    }

    private boolean validateArguments(String... strings) {
        logger.debug("Start of inputs strings validations");
        boolean result = true;
        for (String s : strings) {
            if (s == null || s.isEmpty()) {
                result = false;
                break;
            }
        }
        logger.info("Result of validation = " + result);
        return result;
    }
}
