package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Role;
import ru.innopolis.stc9.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService service;

    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String addRole(HttpServletRequest request, Model model) {
        return "/addRole";
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole2(HttpServletRequest request,
                              @RequestAttribute String name, Model model ) {

        Role role = new Role(name);
        service.add(role);
        model.addAttribute("role", role);
        return "/getRole";
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
    public String deleteRole(HttpServletRequest request,
                                @RequestAttribute Role role, Model model) {
        service.deleteById(role.getId());
        return "/roleList";
    }

    @RequestMapping(value = "/roleAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Role> roleList = service.getAll();
        if (roleList != null) {
            model.addAttribute("roleList", roleList);
            return "/roleList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateRole(HttpServletRequest request,
                                @RequestAttribute Role role, Model model) {
        model.addAttribute("role", role);
        return "/updateRole";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRole2(HttpServletRequest request,
                                 @RequestAttribute String id,
                                 @RequestAttribute String name, Model model) {
        Role role = new Role(Long.parseLong(id), name);
        service.update(role);
        model.addAttribute("role", role);
        return "/getRole";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String getRole(HttpServletRequest request,
                             @RequestAttribute String id, Model model) {
        Role role = service.getById(Long.parseLong(id));
        model.addAttribute("role", role);
        return "/getRole";
    }
}
