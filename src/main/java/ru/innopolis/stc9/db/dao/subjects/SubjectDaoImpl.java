package ru.innopolis.stc9.db.dao.subjects;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject getById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        Subject subject = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM subjects WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        subject = new Subject(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                    }
                }                
            }
        }

        logger.info("Class SheduleDaoImpl method getById finished, id = " + id);
        return subject;
    }

    @Override
    public Subject getByName(String name) throws SQLException {
        Subject result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM subjects WHERE name= ?")) {
                preparedStatement.setString(1, name);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        Subject subject = new Subject(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                        result = subject;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<Subject> getAll() throws SQLException {
        ArrayList<Subject> result = new ArrayList<>();
      
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM subjects")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Subject subject = new Subject(
                                resultSet.getLong("id")
                                , resultSet.getString("name"));
                        result.add(subject);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(Subject subject) throws SQLException {
        logger.info("Class SheduleDaoImpl method add started");

        String sql = "INSERT INTO specialty (name) VALUES (?)";

        execureStatement(subject, sql);
        logger.info("Class SheduleDaoImpl method add finished");
    }

    private void execureStatement(Subject subject, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, subject.getName());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Subject subject) throws SQLException {
        logger.info("Class SheduleDaoImpl method update started, id = " + subject.getId());

        String sql = "UPDATE specialty SET name = ?, semester_count = ? WHERE id = ?";

        execureStatement(subject, sql);
        logger.info("Class SheduleDaoImpl method update finished, id = " + subject.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method deleteById started, id = " + id);
        String sql = "DELETE FROM subjects WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class SheduleDaoImpl method deleteById finished, id = " + id);
    }
}
