package ru.innopolis.stc9.db.dao.teacherLesson;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.TeacherLesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherLessonDaoImpl implements TeacherLessonDao {
    private static final Logger logger = Logger.getLogger(TeacherLessonDaoImpl.class);
    public  final String ClassName= this.getClass().getName();

    @Override
    public TeacherLesson getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        TeacherLesson teacherLesson = null;

        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teacher_lesson WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        teacherLesson = new TeacherLesson(
                                resultSet.getLong("id")
                                , resultSet.getLong("teacher_item")
                                , resultSet.getLong("subject_item"));
                    }
                }
            }
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return teacherLesson;
    }

    @Override
    public TeacherLesson getByName(String name) throws SQLException {
        TeacherLesson result = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teacher_lesson WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        TeacherLesson teacherLesson = new TeacherLesson(
                                resultSet.getLong("id")
                                , resultSet.getLong("teacher_item")
                                , resultSet.getLong("subject_item"));
                        result = teacherLesson;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<TeacherLesson> getAll() throws SQLException {
        ArrayList<TeacherLesson> result = new ArrayList<>();

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT teacher_lesson.id, subjects.name as subjName, persons.name as persName\n" +
                            "FROM teacher_lesson\n" +
                            "  LEFT JOIN persons\n" +
                            "    on teacher_lesson.teacher_item = persons.id\n" +
                            "  LEFT JOIN subjects\n" +
                            "    on teacher_lesson.subject_item = subjects.id ORDER BY subjects.name")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        TeacherLesson teacherLesson = new TeacherLesson(
                                resultSet.getLong("id")
                                , resultSet.getString("persName")
                                , resultSet.getString("subjName"));
                        result.add(teacherLesson);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void add(TeacherLesson teacherLesson) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO teacher_lesson (teacher_item, subject_item) VALUES (?,?)";

        executeStatement(teacherLesson, sql);
        logger.info("Class SheduleDaoImpl method add finished");
    }

    private void executeStatement(TeacherLesson teacherLesson, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, teacherLesson.getTeacherItem());
                statement.setLong(2, teacherLesson.getSubjectItem());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(TeacherLesson teacherLesson) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + teacherLesson.getId());

        String sql = "UPDATE teacher_lesson SET teacher_item = ?, subject_item = ? WHERE id = "+ teacherLesson.getId()+"";

        executeStatement(teacherLesson, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + teacherLesson.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM teacher_lesson WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
