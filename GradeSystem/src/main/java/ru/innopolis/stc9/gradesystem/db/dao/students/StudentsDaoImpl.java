package ru.innopolis.stc9.gradesystem.db.dao.students;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.gradesystem.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.gradesystem.db.dao.speciality.SpecialityDao;
import ru.innopolis.stc9.gradesystem.db.dao.speciality.SpecialityDaoImpl;
import ru.innopolis.stc9.gradesystem.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao {
    private static final Logger logger = Logger.getLogger(StudentsDaoImpl.class);

    @Override
    public Student getById(int id) throws SQLException {
        logger.info("Class StudentsDaoImpl method getById started, id = " + id);
        Student student = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id= ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        SpecialityDaoImpl sdi = new SpecialityDaoImpl();
        if (resultSet.next()) {
            student = new Student(
                    resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getInt("age")
                    , resultSet.getFloat("progress")
                    , sdi.getByID(resultSet.getInt("speciality"))
            );
        }
        connection.close();
        logger.info("Class StudentsDaoImpl method getById finished, id = " + id);
        return student;
    }

    @Override
    public Student getByName(String name) throws SQLException {
        Student result = null;
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM students WHERE name= ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        SpecialityDao sdi = new SpecialityDaoImpl();
        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getInt("age")
                    , resultSet.getFloat("progress")
                    , sdi.getByID(resultSet.getInt("speciality"))
            );
            result = student;
        }
        connection.close();
        return result;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        ArrayList<Student> result = new ArrayList<>();
        Connection connection = new ConnectionManagerImpl().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM students ");
        ResultSet resultSet = preparedStatement.executeQuery();
        SpecialityDaoImpl sdi = new SpecialityDaoImpl();
        while (resultSet.next()) {
            Student student = new Student(
                    resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getInt("age")
                    , resultSet.getFloat("progress")
                    , sdi.getByID(resultSet.getInt("speciality"))
            );
            result.add(student);
        }
        connection.close();
        return result;
    }

    @Override
    public void add(Student student) throws SQLException {
        logger.info("Class StudentsDaoImpl method add started");
        Connection connection = new ConnectionManagerImpl().getConnection();
        String sql = "INSERT INTO students (name,age,progress,speciality) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getName());
        statement.setInt(2, student.getAge());
        statement.setFloat(3, student.getProgress());
        statement.setObject(4, student.getSpeciality().getId());
        statement.executeUpdate();
        connection.close();
        logger.info("Class StudentsDaoImpl method add finished");
    }

    @Override
    public void update(Student student) throws SQLException {
        logger.info("Class StudentsDaoImpl method update started, id = " + student.getId());
        Connection connection = new ConnectionManagerImpl().getConnection();
        String sql = "UPDATE students SET name = ?, age = ?, progress  = ?, speciality = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getName());
        statement.setInt(2, student.getAge());
        statement.setFloat(3, student.getProgress());
        statement.setInt(4, student.getSpeciality().getId());
        statement.setInt(5, student.getId());
        statement.executeUpdate();
        connection.close();
        logger.info("Class StudentsDaoImpl method update finished, id = " + student.getId());
    }

    @Override
    public void deleteById(int id) throws SQLException {
        logger.info("Class StudentsDaoImpl method deleteById started, id = " + id);
        Connection connection = new ConnectionManagerImpl().getConnection();
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        logger.info("Class StudentsDaoImpl method deleteById finished, id = " + id);
        connection.close();
    }
}
