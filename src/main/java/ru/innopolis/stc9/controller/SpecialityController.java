package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.innopolis.stc9.pojo.Speciality;
import ru.innopolis.stc9.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

public class SpecialityController {
    private static final Logger logger = Logger.getLogger(SpecialityController.class);
    @Autowired
    private SpecialityService service;

    @RequestMapping(value = "/addSpeciality", method = RequestMethod.GET)
    public String addSpeciality(HttpServletRequest request, Model model) {
        return "/addSpeciality";
    }

    @RequestMapping(value = "/addSpeciality", method = RequestMethod.POST)
    public String addSpeciality2(HttpServletRequest request,
                             @RequestAttribute String name,
                             @RequestAttribute long semester_count, Model model ) {

        Speciality speciality = new Speciality(name, semester_count);
        service.add(speciality);
        model.addAttribute("speciality", speciality);
        return "/getSpeciality";
    }

    @RequestMapping(value = "/deleteSpeciality", method = RequestMethod.GET)
    public String deleteSpeciality(HttpServletRequest request,
                               @RequestAttribute Speciality speciality, Model model) {
        service.deleteById(speciality.getId());
        return "/specialityList";
    }

    @RequestMapping(value = "/specialityAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Speciality> specialityList = service.getAll();
        if (specialityList != null) {
            model.addAttribute("specialityList", specialityList);
            return "/specialityList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSpeciality(HttpServletRequest request,
                               @RequestAttribute Speciality speciality, Model model) {
        model.addAttribute("speciality", speciality);
        return "/updateSpeciality";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSpeciality2(HttpServletRequest request,
                                @RequestAttribute String id,
                                @RequestAttribute String name,
                                @RequestAttribute String semester_count, Model model) {
        Speciality speciality = new Speciality(Long.parseLong(id), name, Long.parseLong(semester_count));
        service.update(speciality);
        model.addAttribute("speciality", speciality);
        return "/getSpeciality";
    }

    @RequestMapping(value = "/speciality", method = RequestMethod.GET)
    public String getSpeciality(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Speciality speciality = service.getById(Long.parseLong(id));
        model.addAttribute("speciality", speciality);
        return "/getSpeciality";
    }
}
