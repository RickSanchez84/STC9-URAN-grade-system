package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.GroupStructure;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.service.IGroupStructureService;
import ru.innopolis.stc9.service.IPersonService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GroupStructureController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    private IGroupStructureService groupStructureService;

    @Autowired
    private IPersonService personService;


    @RequestMapping(value = "/addOrUpdateGroupStructure", method = RequestMethod.GET)
    public String addGroup(HttpServletRequest request, Model model) {
        if (model.containsAttribute("groupStructure")) {
            model.addAttribute("action", "update");
            model.addAttribute("id", request.getParameter("id"));
        } else {
            model.addAttribute("action", "add");
        }
        List<Person> personList = personService.getAll();
        model.addAttribute("personList", personList);
        return "/addOrUpdateGroupStructure";
    }

    @RequestMapping(value = "/addOrUpdateGroupStructure", method = RequestMethod.POST)
    public String addGroupPost(HttpServletRequest request,
                               @RequestAttribute String id,
                               @RequestAttribute String action,
                               @RequestAttribute String nameGroup,
                               @RequestAttribute String studentItem,
                               @RequestAttribute String groupItem,
                               Model model) {

        if (action.equals("add")) {
            GroupStructure groupStructure =
                    new GroupStructure(Long.parseLong(id),
                            nameGroup,
                            personService.getById(Long.parseLong(studentItem)),
                            Long.parseLong(groupItem));
            groupStructureService.add(groupStructure);
        } else {
            if (action.equals("update")) {
                GroupStructure groupStructure = new GroupStructure(Long.parseLong(id),
                        nameGroup,
                        personService.getById(Long.parseLong(studentItem)),
                        Long.parseLong(groupItem));
                groupStructureService.update(groupStructure);
            }
        }
        return "redirect:groupStructureAll";
    }

    @RequestMapping(value = "/deleteGroupStructure", method = RequestMethod.GET)
    public String deleteGroup(HttpServletRequest request,
                              @RequestAttribute GroupStructure group, Model model) {
        groupStructureService.deleteById(group.getId());
        return "/groupListStructure";
    }

    @RequestMapping(value = "/deleteGroupStructure", method = RequestMethod.POST)
    public String deleteGroupPost(HttpServletRequest request,
                                  @RequestAttribute String id,
                                  Model model) {
//        service.deleteById(group.getId());
        groupStructureService.deleteById(Long.parseLong(id));
        logger.info("GroupStructure deleted");
        return "/deleteGroupStructure";
    }

    @RequestMapping(value = "/groupStructureAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<GroupStructure> groupList = groupStructureService.getAll();
        if (groupList != null) {
            model.addAttribute("groupStructureList", groupList);
            return "/groupListStructure";
        }
        else {
            return "error";
        }
    }


    @RequestMapping(value = "/groupStructure", method = RequestMethod.GET)
    public String getGroup(HttpServletRequest request,
                           @RequestAttribute String id, Model model) {
        GroupStructure group = groupStructureService.getById(Long.parseLong(id));

        String groupName = String.valueOf(group.getNameGroup());
        String studentItems = group.getStudentItem().getName();
        String groupItem = String.valueOf(group.getGroupItem());


        model.addAttribute("group", group);
        model.addAttribute("groupName", groupName);
        model.addAttribute("studentItems", studentItems);
        model.addAttribute("groupItem", groupItem);
        return "/getGroupStructure";
    }


}
