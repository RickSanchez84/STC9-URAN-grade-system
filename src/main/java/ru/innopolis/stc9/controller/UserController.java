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
import ru.innopolis.stc9.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    private IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

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

//    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
//    public String addOrUpdateUser(HttpServletRequest request,
//                                  @RequestAttribute String id,
//                                  @RequestAttribute String action,
//                                  @RequestAttribute String login,
//                                  @RequestAttribute String password,
//                                  @RequestAttribute String role,
//                                  Model model) {
//
//        if (action.equals("add")) {
//            User user = new User(login, password, role);
//            service.add(user);
//        } else {
//            if (action.equals("update")) {
//                User user = new User(Long.parseLong(id), login, password, role);
//                service.update(user);
//            }
//        }
//        return "redirect:userAll";
//    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        service.deleteById(Integer.parseInt(id));
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
        model.addAttribute("user", service.getById(Integer.parseInt(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdate");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        User user = service.getById(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "/getUser";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String setRegistrationUser(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String setRegistrationUser(@RequestParam String login,
                                      @RequestParam String password,
                                      @RequestParam String role,
                                      Model model) {
        service.addUsers(login, password, role);
        //  model.addAttribute("registration", "registed");
        return "login";
    }
}
