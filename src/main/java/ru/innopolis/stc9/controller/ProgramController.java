package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Program;
import ru.innopolis.stc9.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProgramController {
    private static final Logger logger = Logger.getLogger(ProgramController.class);
    @Autowired
    private ProgramService service;

    @RequestMapping(value = "/addProgram", method = RequestMethod.GET)
    public String addProgram(HttpServletRequest request, Model model) {
        return "/addProgram";
    }

    @RequestMapping(value = "/addProgram", method = RequestMethod.POST)
    public String addProgram2(HttpServletRequest request,
                              @RequestAttribute long specialty,
                              @RequestAttribute long semester,
                              @RequestAttribute long subject,
                              @RequestAttribute long hours,Model model ) {

        Program program = new Program(specialty,semester,subject,hours);
        service.add(program);
        model.addAttribute("program", program);
        return "/getProgram";
    }

    @RequestMapping(value = "/deleteProgram", method = RequestMethod.GET)
    public String deleteProgram(HttpServletRequest request,
                                @RequestAttribute Program program, Model model) {
        service.deleteById(program.getId());
        return "/programList";
    }

    @RequestMapping(value = "/programAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Program> programList = service.getAll();
        if (programList != null) {
            model.addAttribute("programList", programList);
            return "/programList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateProgram(HttpServletRequest request,
                                @RequestAttribute Program program, Model model) {
        model.addAttribute("program", program);
        return "/updateProgram";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProgram2(HttpServletRequest request,
                                 @RequestAttribute long id,
                                 @RequestAttribute long specialty,
                                 @RequestAttribute long semester,
                                 @RequestAttribute long subject,
                                 @RequestAttribute long hours,Model model) {
        Program program = new Program(id,specialty,semester,subject,hours);
        service.update(program);
        model.addAttribute("program", program);
        return "/getProgram";
    }

    @RequestMapping(value = "/program", method = RequestMethod.GET)
    public String getProgram(HttpServletRequest request,
                             @RequestAttribute String id, Model model) {
        Program program = service.getById(Long.parseLong(id));
        model.addAttribute("program", program);
        return "/getProgram";
    }
}
