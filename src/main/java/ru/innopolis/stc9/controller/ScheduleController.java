package ru.innopolis.stc9.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.pojo.Schedule;
import ru.innopolis.stc9.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
public class ScheduleController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);
    @Autowired
    private ScheduleService service;

    @RequestMapping(value = "/addSchedule", method = RequestMethod.GET)
    public String addSchedule(HttpServletRequest request, Model model) {
        return "/addSchedule";
    }

    @RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
    public String addSchedule2(HttpServletRequest request,
                             @RequestAttribute Integer day_of_week,
                             @RequestAttribute Integer lesson_nummber,
                             @RequestAttribute Integer group_item,
                             @RequestAttribute Integer subject,
                             @RequestAttribute Integer room,

                             Model model) {
        Schedule schedule = new Schedule(day_of_week,lesson_nummber,group_item, subject, room );
        service.add(schedule);
        model.addAttribute("schedule", schedule);
        return "/getSchedule";
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.GET)
    public String deleteSchedule(HttpServletRequest request,
                               @RequestAttribute Schedule schedule, Model model) {
        service.deleteById(schedule.getId());
        return "/scheduleList";
    }

    @RequestMapping(value = "/scheduleAll", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request, Model model) {
        List<Schedule> scheduleList = service.getAll();
        if (scheduleList != null) {
            model.addAttribute("scheduleList", scheduleList);
            return "/scheduleList";
        }
        else {
            return "index";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSchedule(HttpServletRequest request,
                               @RequestAttribute Schedule schedule, Model model) {
        model.addAttribute("schedule", schedule);
        return "/updateSchedule";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSchedule2(HttpServletRequest request,
                                @RequestAttribute String id,
                                  @RequestAttribute Integer day_of_week,
                                  @RequestAttribute Integer lesson_nummber,
                                  @RequestAttribute Integer group_item,
                                  @RequestAttribute Integer subject,
                                  @RequestAttribute Integer room, Model model) {
        Schedule schedule = new Schedule(Long.parseLong(id), day_of_week, lesson_nummber, group_item , subject, room);
        service.updateById(schedule);
        model.addAttribute("schedule", schedule);
        return "/getSchedule";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String getSchedule(HttpServletRequest request,
                            @RequestAttribute String id, Model model) {
        Schedule schedule = service.getById(Long.parseLong(id));
        model.addAttribute("schedule", schedule);
        return "/getSchedule";
    }
}
