package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Subject;
import ru.innopolis.stc9.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SubjectController {
    private static final Logger logger = Logger.getLogger(SubjectController.class);
    @Autowired
    private SubjectService service;

    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String addSubject(HttpServletRequest request, Model model) {
        return "/addSubject";
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public String addSubject2(HttpServletRequest request,
                                 @RequestAttribute String name, Model model ) {

        Subject subject = new Subject(name);
        service.add(subject);
        model.addAttribute("subject", subject);
        return "/getSubject";
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.GET)
    public String deleteSubject(HttpServletRequest request,
                                   @RequestAttribute Subject subject, Model model) {
        service.deleteById(subject.getId());
        return "/subjectList";
    }

    @RequestMapping(value = "/subjectAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Subject> subjectList = service.getAll();
        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            return "/subjectList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSubject(HttpServletRequest request,
                                   @RequestAttribute Subject subject, Model model) {
        model.addAttribute("subject", subject);
        return "/updateSubject";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSubject2(HttpServletRequest request,
                                    @RequestAttribute String id,
                                    @RequestAttribute String name, Model model) {
        Subject subject = new Subject(Long.parseLong(id), name);
        service.update(subject);
        model.addAttribute("subject", subject);
        return "/getSubject";
    }

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String getSubject(HttpServletRequest request,
                                @RequestAttribute String id, Model model) {
        Subject subject = service.getById(Long.parseLong(id));
        model.addAttribute("subject", subject);
        return "/getSubject";
    }
}
