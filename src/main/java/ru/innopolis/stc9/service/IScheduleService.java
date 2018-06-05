package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Schedule;

import java.util.List;

public interface IScheduleService {
    void updateById(Schedule schedule);

    Schedule getById(long id);

    void deleteById(long id);

    void add(Schedule schedule);

    List<Schedule> getAll();
}
