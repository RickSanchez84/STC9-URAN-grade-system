package ru.innopolis.stc9.gradesystem.db.dao.courses;

import ru.innopolis.stc9.gradesystem.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.gradesystem.pojo.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoursesDaoImpl implements CoursesDao {
    @Override
    public Course getById(int id) throws SQLException {
        Course result = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT *" +

                        "FROM courses as c" +

                        "WHERE c.id= ?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Course course = new Course(resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getInt("duration"));


            result = course;
        }
        connection.close();
        return result;
    }
}
