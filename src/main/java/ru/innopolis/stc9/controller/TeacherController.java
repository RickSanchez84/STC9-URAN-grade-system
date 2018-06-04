package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Teacher;
import ru.innopolis.stc9.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TeacherController {
    private static final Logger logger = Logger.getLogger(TeacherController.class);
    @Autowired
    private TeacherService service;

    @RequestMapping(value = "/addTeacher", method = RequestMethod.GET)
    public String addTeacher(HttpServletRequest request, Model model) {
        return "/addTeacher";
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public String addTeacher2(HttpServletRequest request,
                              @RequestAttribute long teacher_item,
                              @RequestAttribute long subject_item,Model model ) {

        Teacher teacher = new Teacher(teacher_item,subject_item);
        service.add(teacher);
        model.addAttribute("teacher", teacher);
        return "/getTeacher";
    }

    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.GET)
    public String deleteTeacher(HttpServletRequest request,
                                @RequestAttribute Teacher teacher, Model model) {
        service.deleteById(teacher.getId());
        return "/teacherList";
    }

    @RequestMapping(value = "/teacherAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Teacher> teacherList = service.getAll();
        if (teacherList != null) {
            model.addAttribute("teacherList", teacherList);
            return "/teacherList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTeacher(HttpServletRequest request,
                                @RequestAttribute Teacher teacher, Model model) {
        model.addAttribute("teacher", teacher);
        return "/updateTeacher";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateTeacher2(HttpServletRequest request,
                                 @RequestAttribute long id,
                                 @RequestAttribute long teacher_item,
                                 @RequestAttribute long subject_item,Model model) {
        Teacher teacher = new Teacher(id,teacher_item,subject_item);
        service.update(teacher);
        model.addAttribute("teacher", teacher);
        return "/getTeacher";
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String getTeacher(HttpServletRequest request,
                             @RequestAttribute String id, Model model) {
        Teacher teacher = service.getById(Long.parseLong(id));
        model.addAttribute("teacher", teacher);
        return "/getTeacher";
    }
}
