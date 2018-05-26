package ru.innopolis.stc9.gradesystem.db.dao.marks;

import ru.innopolis.stc9.gradesystem.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.gradesystem.db.dao.courses.CoursesDaoImpl;
import ru.innopolis.stc9.gradesystem.db.dao.students.StudentsDaoImpl;
import ru.innopolis.stc9.gradesystem.db.dao.users.UserDaoImpl;
import ru.innopolis.stc9.gradesystem.pojo.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarksDaoImpl implements MarksDao {

    @Override
    public Mark getById(int id) throws SQLException {
        Mark result = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT " +
                        "m.id" +
                        ", m.course" +
                        ", m.student" +
                        ", m.faculty" +
                        ", m.mark_value" +

                        "FROM marks as m" +
                        "WHERE m.id= id");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        CoursesDaoImpl cdi = new CoursesDaoImpl();
        StudentsDaoImpl sdi = new StudentsDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();

        while (resultSet.next()) {
            Mark mark = new Mark(
                    resultSet.getInt("m.id")
                    , cdi.getById(resultSet.getInt("m.course"))
                    , sdi.getById(resultSet.getInt("m.student"))
                    , udi.getUserById(resultSet.getInt("m.faculty"))
                    , resultSet.getDouble("m.mark_value")
                    , resultSet.getTimestamp("m.time_stamp")
            );
            result = mark;
        }
        connection.close();
        return result;
    }

    @Override
    public List<Mark> getAllByStudent(int studentId) throws SQLException {

        ArrayList<Mark> result = new ArrayList<>();

        Connection connection = new ConnectionManagerImpl().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT " +
                        "m.id" +
                        ", m.course" +
                        ", m.student" +
                        ", m.faculty" +
                        ", m.mark_value" +

                        "FROM marks as m" +
                        "WHERE m.student= studentId");

        preparedStatement.setInt(1, studentId);

        ResultSet resultSet = preparedStatement.executeQuery();

        CoursesDaoImpl cdi = new CoursesDaoImpl();
        StudentsDaoImpl sdi = new StudentsDaoImpl();
        UserDaoImpl udi = new UserDaoImpl();

        while (resultSet.next()) {
            Mark mark = new Mark(
                    resultSet.getInt("m.id")
                    , cdi.getById(resultSet.getInt("m.course"))
                    , sdi.getById(resultSet.getInt("m.student"))
                    , udi.getUserById(resultSet.getInt("m.faculty"))
                    , resultSet.getDouble("m.mark_value")
                    , resultSet.getTimestamp("m.time_stamp")
            );
            result.add(mark);
        }
        connection.close();
        return result;
    }
}
