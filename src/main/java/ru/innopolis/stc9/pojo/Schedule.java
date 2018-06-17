package ru.innopolis.stc9.pojo;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<ScheduleItem> list = new ArrayList<>();

    public Schedule() {
    }

    public void add(ScheduleItem item) {
        if (item != null) {
            list.add(item);
        }
    }

}
