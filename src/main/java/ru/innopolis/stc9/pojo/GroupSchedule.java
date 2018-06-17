package ru.innopolis.stc9.pojo;

import java.util.List;

public class GroupSchedule {
    private Group group;
    private List<Schedule> schedules;

    public GroupSchedule() {
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
