package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    private IUserService service;

    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {
        if (model.containsAttribute("user")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateUser";
    }

    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
    public String addOrUpdateUser(HttpServletRequest request,
                                    @RequestAttribute String id,
                                    @RequestAttribute String action,
                                    @RequestAttribute String login,
                                    @RequestAttribute String password,
                                    @RequestAttribute String person_id,
                                     Model model) {

        if (action.equals("add")) {
            User user = new User(login, password, Integer.parseInt(person_id));
            service.add(user);
        } else {
            if (action.equals("update")) {
                User user = new User(Long.parseLong(id), login, password, Integer.parseInt(person_id));
                service.update(user);
            }
        }
        return "redirect:userAll";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
        return ("redirect:userAll");
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<User> userList = service.getAll();
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
        model.addAttribute("user", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        User user = service.getById(Long.parseLong(id));
        model.addAttribute("user", user);
        return "/getUser";
    }
}
