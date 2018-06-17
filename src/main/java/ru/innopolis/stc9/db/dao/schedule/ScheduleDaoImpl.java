package ru.innopolis.stc9.db.dao.schedule;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class);
    private static final String BEFORE = "First line of method.";
    private static final String AFTER = "Before exit.";

    @Override
    public Schedule getById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        Schedule schedule = null;
        ResultSet resultSet = null;
        int iid = (int) id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        if (resultSet.next()) {
            schedule = parseSchedule(resultSet);
        }
        logger.info("Class PerformanceDaoImpl method getById finished, id = " + id);
        return schedule;
    }

    @Override
    public Schedule getByLessonId(long id) throws SQLException {
        Schedule result = null;
        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE lesson_nummber= ?")) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Schedule schedule = parseSchedule(resultSet);
            result = schedule;
        }
        return result;
    }

    /**
     * Get schedule for group
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Schedule> getByGroupId(long id) throws SQLException {
        logger.debug(BEFORE);
        List<Schedule> result = new ArrayList<>();
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules WHERE group_item= ?")) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Schedule schedule = parseSchedule(resultSet);
                        result.add(schedule);
                    }
                }
                logger.info("SUCCESS? " + (!result.isEmpty()));
            }
        }
        logger.debug(AFTER);
        return result;
    }

    /**
     * Take pojo from DB
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Schedule parseSchedule(ResultSet resultSet) throws SQLException {
        return new Schedule(
                resultSet.getLong("id")
                , resultSet.getLong("day_of_week")
                , resultSet.getLong("lesson_nummber")
                , resultSet.getLong("group_item")
                , resultSet.getLong("subject")
                , resultSet.getLong("room"));
    }

    @Override
    public List<Schedule> getAll() throws SQLException {
        ArrayList<Schedule> result = new ArrayList<>();

        ResultSet resultSet = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schedules")) {

                resultSet = preparedStatement.executeQuery();
            }
        } finally {
            if (resultSet != null)
                resultSet.close();
        }

        while (resultSet.next()) {
            Schedule schedule = parseSchedule(resultSet);
            result.add(schedule);
        }
        return result;
    }

    @Override
    public void add(Schedule schedule) throws SQLException {
        logger.info("Class PerformanceDaoImpl method add started");

        String sql = "INSERT INTO schedules (  day_of_week" +
                "                           ,lesson_nummber" +
                "                           ,group_item" +
                "                           ,subject" +
                "                           ,room) " +
                "     VALUES (?,?,?,?,?)";

        sqlStatementExecute(schedule, sql);
        logger.info("Class PerformanceDaoImpl method add finished");
    }

    @Override
    public void update(Schedule schedule) throws SQLException {
        logger.info("Class PerformanceDaoImpl method update started, id = " + schedule.getId());

        String sql = "UPDATE schedules SET  day_of_week = ?" +
                "                       , lesson_nummber = ?" +
                "                       , group_item = ?" +
                "                       , subject = ?" +
                "                       , room= ?" +
                "     WHERE id = ?";

        sqlStatementExecute(schedule, sql);
        logger.info("Class PerformanceDaoImpl method update finished, id = " + schedule.getId());
    }

    private void sqlStatementExecute(Schedule schedule, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, schedule.getDayOfWeek());
                statement.setLong(2, schedule.getLessonNumber());
                statement.setLong(3, schedule.getGroupItem());
                statement.setLong(4, schedule.getSubject());
                statement.setLong(5, schedule.getRoom());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class PerformanceDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM schedules WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class PerformanceDaoImpl method deleteById finished, id = " + id);
    }
}
