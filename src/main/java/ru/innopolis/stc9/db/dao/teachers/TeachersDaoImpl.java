package ru.innopolis.stc9.db.dao.teachers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeachersDaoImpl implements TeachersDao {
    private static final Logger logger = Logger.getLogger(TeachersDaoImpl.class);
    public  final String ClassName= this.getClass().getName();

    @Override
    public Teacher getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        Teacher teacher = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teacher_facilities WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        teacher = new Teacher(
                                resultSet.getLong("id")
                                , resultSet.getLong("teacher_item")
                                , resultSet.getLong("subject_item"));
                    }
                }                
            }
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return teacher;
    }

    @Override
    public Teacher getByName(String name) throws SQLException {
        Teacher result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teacher_facilities WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher(
                                  resultSet.getLong("id")
                                , resultSet.getLong("teacher_item")
                                , resultSet.getLong("subject_item"));
                        result = teacher;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        ArrayList<Teacher> result = new ArrayList<>();
      
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM teachers")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Teacher teacher = new Teacher(
                                resultSet.getLong("id")
                                , resultSet.getLong("teacher_item")
                                , resultSet.getLong("subject_item"));
                        result.add(teacher);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(Teacher teacher) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO teacher_facilities (teacher_item, subject_item) VALUES (?,?)";

        execureStatement(teacher, sql);
        logger.info("Class SheduleDaoImpl method add finished");
    }

    private void execureStatement(Teacher teacher, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, teacher.getTeacherItem());
                statement.setLong(2, teacher.getSubjectItem());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + teacher.getId());

        String sql = "UPDATE teacher_facilities SET teacher_item = ?, subject_item = ? WHERE id = "+teacher.getId()+"";

        execureStatement(teacher, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + teacher.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM teacher_facilities WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
