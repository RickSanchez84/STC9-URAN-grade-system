package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.pojo.User;
import ru.innopolis.stc9.service.IPersonService;
import ru.innopolis.stc9.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private static final int PASSWORD_LENGTH = 1;
    @Autowired
    private IUserService service;
    @Autowired
    private IPersonService personService;

    /**
     * –егистраци€ в системе нового пользовател€. ¬ывод страницы с регистрацией
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpServletRequest request,
                           Model model) {
        model.addAttribute("action", "add");
        return "/register";
    }

    /**
     * ќбработка формы по регистрации в системе.
     *
     * @param request
     * @param action
     * @param name
     * @param birthday
     * @param email
     * @param login
     * @param password
     * @param password2
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request,
                           @RequestAttribute String action,
                           @RequestAttribute String name,
                           @RequestAttribute String birthday,
                           @RequestAttribute String email,
                           @RequestAttribute String login,
                           @RequestAttribute String password,
                           @RequestAttribute String password2,
                           Model model) {
        String result = "redirect:index";
        Person person = personService.getByName(name);
        if (person != null) {
            User user = service.getUserByPerson(person);
            if (user == null) {
                /*новый пользователь хочет попасть в систему*/
                /*ƒобавл€ем все данные с формы и обновл€ем таблицу в Ѕƒ*/
                if (birthday != null && email != null) {
                    person.setBirthday(Date.valueOf(birthday));
                    person.setEmail(email);
                    /*провер€ем на валидность данных по учетке*/
                    if (password.equals(password2) && password.length() > PASSWORD_LENGTH) {
                        /*¬се корректно, добавл€ем в таблицы персоны и юзеры*/
                        personService.updateById(person);
                        user = new User(login, password, person.getId());
                        service.add(user);
                        request.getSession().setAttribute("msgRegister", "success registration");
                    } else {
                        /*ќшибки при заполнении формы. Ќадо вывести какое-то сообщение и не перенапрввл€тьс€ на другую страницу*/
                        request.getSession().setAttribute("msgRegister", "lack of protection for the account");
                        result = "register";
                    }
                } else {
                    /*Ќадо заполнить все пол€*/
                    request.getSession().setAttribute("msgRegister", "bad data in the form");
                    result = "register";
                }
            } else {
                /*этот человек уже регистрировалс€ в системе. “.е. надо отработать восстановление парол€*/
                request.getSession().setAttribute("msgRegister", "account data must be reset");
                // TODO: 07.06.2018 здесь нужна заглушка
            }
        } else {
            /*не прошел модерацию в системе*/
            request.getSession().setAttribute("msgRegister", "not passed moderation in the system");
            result = "register";
        }
        return result;
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
