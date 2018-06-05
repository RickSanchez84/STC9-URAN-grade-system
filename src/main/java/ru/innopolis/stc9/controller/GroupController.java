package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Group;
import ru.innopolis.stc9.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    private GroupService service;

    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
    public String addGroup(HttpServletRequest request, Model model) {
        return "/addGroup";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup2(HttpServletRequest request,
                              @RequestAttribute long cur_semester_education,
                              @RequestAttribute long program,Model model ) {

        Group group = new Group(cur_semester_education,program);
        service.add(group);
        model.addAttribute("group", group);
        return "/getGroup";
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(HttpServletRequest request,
                                @RequestAttribute Group group, Model model) {
        service.deleteById(group.getId());
        return "/groupList";
    }

    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Group> groupList = service.getAll();
        if (groupList != null) {
            model.addAttribute("groupList", groupList);
            return "/groupList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateGroup(HttpServletRequest request,
                                @RequestAttribute Group group, Model model) {
        model.addAttribute("group", group);
        return "/updateGroup";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateGroup2(HttpServletRequest request,
                                 @RequestAttribute long id,
                               @RequestAttribute long cur_semester_education,
                               @RequestAttribute long program,Model model) {
        Group group = new Group(id,cur_semester_education,program);
        service.update(group);
        model.addAttribute("group", group);
        return "/getGroup";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getGroup(HttpServletRequest request,
                             @RequestAttribute String id, Model model) {
        Group group = service.getById(Long.parseLong(id));
        model.addAttribute("group", group);
        return "/getGroup";
    }
}
