package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.db.dao.lesson.PersonService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class PersonController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PersonController.class);
    @Autowired
    private PersonService service;

    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public String addPerson(HttpServletRequest request, Model model) {
        return "/addPerson";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson2(HttpServletRequest request,
                             @RequestAttribute String name,
                             @RequestAttribute String birthday,
                             @RequestAttribute String address, Model model) {
        Person person = new Person(name, Date.valueOf(birthday),address);
        service.add(person);
        model.addAttribute("person", person);
        return "/getPerson";
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public String deletePerson(HttpServletRequest request,
                               @RequestAttribute Person person, Model model) {
        service.deleteById(person.getId());
        return "/personList";
    }

    @RequestMapping(value = "/personAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Person> personList = service.getAll();
        if (personList != null) {
            model.addAttribute("personList", personList);
            return "/personList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePerson(HttpServletRequest request,
                                @RequestAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "/updatePerson";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePerson2(HttpServletRequest request,
                                @RequestAttribute String id,
                                @RequestAttribute String name,
                                @RequestAttribute String birthday,
                                @RequestAttribute String address, Model model) {
        Person person = new Person(Long.parseLong(id), name, Date.valueOf(birthday),address);
        service.updateById(person);
        model.addAttribute("person", person);
        return "/getPerson";
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String getPerson(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Person person = service.getById(Long.parseLong(id));
        model.addAttribute("person", person);
        return "/getPerson";
    }
}