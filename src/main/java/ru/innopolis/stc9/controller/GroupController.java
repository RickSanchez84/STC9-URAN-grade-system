package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc9.service.IGroupService;
import ru.innopolis.stc9.service.IProgramService;

public class GroupController {
    private static final Logger logger = Logger.getLogger(GroupController.class);
    IProgramService programService;
    @Autowired
    private IGroupService service;

//    @RequestMapping(value = "/addGroup", method = RequestMethod.GET)
//    public String addGroup(HttpServletRequest request, Model model) {
//
//        List<Program> listProgram = programService.getAll();
//        model.addAttribute("listProgram", listProgram);
//
//        return "/addGroup";
//    }
//
//    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
//    public String addGroupPost(HttpServletRequest request,
//                               @RequestAttribute String cur_semester_education,
//                               @RequestAttribute String program,
//                               Model model) {
//        int cur_semestr = Integer.parseInt(cur_semester_education);
//        int prog = Integer.parseInt(program);
//
//        Group group = new Group(cur_semestr, prog);
//        service.add(group);
//        model.addAttribute("group", group);
//        return "redirect:groupAll";
//    }
//
//    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
//    public String deleteGroupGet(HttpServletRequest request,
//                                 @RequestAttribute Group group, Model model) {
//        service.deleteById(group.getId());
//        return "/redirect:personAll";
//    }
//
//    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
//    public String deleteGroupPost(HttpServletRequest request,
//                                  @RequestAttribute String id,
//                                  Model model) {
////        service.deleteById(group.getId());
//        service.deleteById(Long.parseLong(id));
//        logger.info("Group deleted");
//        return "/deleteGroup";
//    }
//
//
//    @RequestMapping(value = "/groupAll", method = RequestMethod.GET)
//    public String getAll(HttpServletRequest request,
//                         Model model) {
//        List<Group> groupList = service.getAll();
//        if (groupList != null) {
//            model.addAttribute("groupList", groupList);
//            return "/groupList";
//        }
//        else {
//            return "index";
//        }
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.GET)
//    public String updateGroupGet(HttpServletRequest request,
//                                 @RequestAttribute Group group,
//                                 Model model) {
//        model.addAttribute("group", group);
//        return "/updateGroup";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateGroupPost(HttpServletRequest request,
//                                  @RequestAttribute String id,
//                                  @RequestAttribute String cur_semester_education,
//                                  @RequestAttribute String program,
//                                  Model model) {
//        int cur_semestr = Integer.parseInt(cur_semester_education);
//        int prog = Integer.parseInt(program);
//        Group group = new Group(cur_semestr, prog);
//        service.update(group);
//        model.addAttribute("group", group);
//        return "/addGroup";
//    }
//
//    @RequestMapping(value = "/group", method = RequestMethod.GET)
//    public String getGroup(HttpServletRequest request,
//                             @RequestAttribute String id, Model model) {
//        Group group = service.getById(Integer.parseInt(id));
//        model.addAttribute("group", group);
//        return "/getGroup";
//    }
}
