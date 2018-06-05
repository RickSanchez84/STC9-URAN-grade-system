package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.User;
import ru.innopolis.stc9.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService service;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(HttpServletRequest request, Model model) {
        return "/addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser2(HttpServletRequest request,
                              @RequestAttribute String login,
                              @RequestAttribute String password,
                              @RequestAttribute long person_id, Model model ) {

        User user = new User(login, password, person_id);
        service.add(user);
        model.addAttribute("user", user);
        return "/getUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,
                                @RequestAttribute User user, Model model) {
        service.deleteById(user.getId());
        return "/userList";
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<User> userList = service.getAll();
        if (userList != null) {
            model.addAttribute("userList", userList);
            return "/userList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateUser(HttpServletRequest request,
                                @RequestAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "/updateUser";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser2(HttpServletRequest request,
                                 @RequestAttribute long id,
                              @RequestAttribute String login,
                              @RequestAttribute String password,
                              @RequestAttribute long person_id,Model model) {
        User user = new User(id,login,password,person_id);
        service.update(user);
        model.addAttribute("user", user);
        return "/getUser";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(HttpServletRequest request,
                             @RequestAttribute String id, Model model) {
        User user = service.getById(Long.parseLong(id));
        model.addAttribute("user", user);
        return "/getUser";
    }
}
