package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Program;
import ru.innopolis.stc9.pojo.Speciality;
import ru.innopolis.stc9.service.IProgramService;
import ru.innopolis.stc9.service.ISpecialityService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SpecialityController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SpecialityController.class);

    @Autowired
    private ISpecialityService service;
    @Autowired
    private IProgramService programService;

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.GET)
    public String addOrUpdate(HttpServletRequest request, Model model) {

        if (model.containsAttribute("speciality")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        return "/addOrUpdateSpeciality";
    }

    @RequestMapping(value = "/addOrUpdateSpeciality", method = RequestMethod.POST)
    public String addOrUpdate(HttpServletRequest request,
                              @RequestAttribute String id,
                              @RequestAttribute String action,
                              @RequestAttribute String name,
                              @RequestAttribute String semesterCount, Model model) {

        if (action.equals("add")) {
            Speciality speciality = new Speciality(name, Integer.parseInt(semesterCount));
            service.add(speciality);
        } else {
            if (action.equals("update")) {
                Speciality speciality = new Speciality(Long.parseLong(id), name, Long.parseLong(semesterCount));
                service.updateById(speciality);
            }
        }
        return "redirect:specialityAll";
    }

    @RequestMapping(value = "/deleteSpeciality", method = RequestMethod.GET)
    public String delete(@RequestAttribute String id, Model model) {
        service.deleteById(Long.parseLong(id));
        return "redirect:specialityAll";
    }

    @RequestMapping(value = "/specialityAll", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Speciality> specialityList = service.getAll();
        model.addAttribute("specialityList", specialityList);
        return "/specialityList";
    }

    @RequestMapping(value = "/updateSpeciality", method = RequestMethod.GET)
    public String update(HttpServletRequest request,
                         @RequestAttribute String id, Model model) {
        model.addAttribute("speciality", service.getById(Long.parseLong(id)));
        model.addAttribute("action", "update");
        return ("/addOrUpdateSpeciality");
    }

    @RequestMapping(value = "/speciality", method = RequestMethod.GET)
    public String showSpecialtyProgram(@RequestAttribute String id, Model model) {
        Speciality speciality = service.getById(Long.parseLong(id));
        model.addAttribute("speciality", speciality);
        List<Program> programs = programService.getBySpecialty(speciality);
        model.addAttribute("programs", programs);
        return "/getSpeciality";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProgramOfSpecialty(@RequestAttribute long id,
                                           @RequestAttribute long programId,
                                           Model model) {
        programService.deleteById(programId);
        model.addAttribute("id", String.valueOf(id));
        return "redirect:/speciality";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String addNewSubjectToSpecialtyProgram(@RequestAttribute long id,
                                                  Model model) {
        model.addAttribute("id", id);
        return "addSubjectToProgram";
    }
}
