package ru.innopolis.stc9.db.dao.schedule;
import ru.innopolis.stc9.pojo.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleDao {
    Schedule getById(long id) throws SQLException;

    Schedule getByLessonId(long id) throws SQLException;

    /**
     * Get schedule for group
     *
     * @param id
     * @return
     * @throws SQLException
     */
    List<Schedule> getByGroupId(long id) throws SQLException;

    List<Schedule> getAll() throws SQLException;

    void add(Schedule shedule) throws SQLException;

    void update(Schedule shedule) throws SQLException;

    void deleteById(long id) throws SQLException;
}
