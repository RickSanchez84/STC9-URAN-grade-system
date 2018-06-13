package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.TeacherLesson;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.pojo.Subject;
import ru.innopolis.stc9.service.ITeacherLessonService;
import ru.innopolis.stc9.service.IPersonService;
import ru.innopolis.stc9.service.ISubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TeacherLessonController {
    private static final Logger logger = Logger.getLogger(TeacherLessonController.class);

    @Autowired
    private ITeacherLessonService service;

    @Autowired
    private IPersonService personService;

    @Autowired
    private ISubjectService subjectService;

    @RequestMapping(value = "/deleteTeacherLesson", method = RequestMethod.GET)
    public String deleteTeacherLesson(HttpServletRequest request,
                                      @RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
        return "redirect:tlAll";
    }

    @RequestMapping(value = "/tlAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<TeacherLesson> teacherLessonList = service.getAll();
        if (teacherLessonList != null) {
            model.addAttribute("teacherLessonList", teacherLessonList);
            return "/teacherLessonList";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/teacherLesson", method = RequestMethod.GET)
    public String getTeacherLesson(HttpServletRequest request,
                              @RequestAttribute String id, Model model) {
        TeacherLesson teacherLesson = service.getById(Long.parseLong(id));
        model.addAttribute("teacherLesson", teacherLesson);
        List<Person> teacherList = personService.getTeachers();
        List<Subject> subjectList = subjectService.getAll();
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("subjectList", subjectList);
        return "/getTeacherLesson";
    }

    @RequestMapping(value = "/addOrUpdateTL", method = RequestMethod.GET)
    public String addOrUpdateTLG(HttpServletRequest request, @RequestAttribute String id, Model model) {
        if (model.containsAttribute("id")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            List<Person> teacherList = personService.getTeachers();
            List<Subject> subjectList = subjectService.getAll();
            model.addAttribute("teacherList", teacherList);
            model.addAttribute("subjectList", subjectList);
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateTL";
    }

    @RequestMapping(value = "/addOrUpdateTL", method = RequestMethod.POST)
    public String addOrUpdateTLP(HttpServletRequest request,
                                 @RequestAttribute String action,
                                 @RequestAttribute String id,
                                 @RequestAttribute String teacher_item,
                                 @RequestAttribute String subject_item, Model model) {

        if (action.equals("add")) {
            TeacherLesson teacherLesson = new TeacherLesson(Long.parseLong(teacher_item), Long.parseLong(subject_item));
            service.add(teacherLesson);
            model.addAttribute("teacher", teacherLesson);
        } else {
            if (action.equals("update")) {
                TeacherLesson teacherLesson = new TeacherLesson(Long.parseLong(id), Long.parseLong(teacher_item), Long.parseLong(subject_item));
                service.update(teacherLesson);
                model.addAttribute("teacherLesson", teacherLesson);
            }
        }
        return "redirect:tlAll";
    }

    @RequestMapping(value = "/updateTeacherLesson", method = RequestMethod.GET)
    public String updatePerson(HttpServletRequest request,
                               @RequestAttribute String id, Model model) {
        List<Person> teacherList = personService.getTeachers();
        List<Subject> subjectList = subjectService.getAll();
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("teacherLesson", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateTL");
    }
}
