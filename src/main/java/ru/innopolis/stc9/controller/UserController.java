package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.pojo.User;
import ru.innopolis.stc9.pojo.User;
import ru.innopolis.stc9.service.IUserService;
import ru.innopolis.stc9.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "login", required = true) String login,
                          @RequestParam(value = "password", required = true) String password,
                          @RequestParam(value = "personId", required = true) String personId, Model model) {
        userService.add(new User(login, password, Long.parseLong(personId)));
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        return "addUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        userService.deleteById(Long.parseLong(id));
        return ("redirect:userAll");
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<User> userList = userService.getAll();
        if (userList != null) {
            model.addAttribute("userList", userList);
            return "/userList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        model.addAttribute("user", userService.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        User user = userService.getById(Long.parseLong(id));
        model.addAttribute("user", user);
        return "/getUser";
    }
}
