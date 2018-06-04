package ru.innopolis.stc9.db.dao.groups;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.pojo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsDaoImpl implements GroupsDao {
    private static final Logger logger = Logger.getLogger(GroupsDaoImpl.class);
    public  final String ClassName= this.getClass().getName();
    
    @Override
    public Group getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        Group group = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        group = new Group(
                                  resultSet.getLong("id")
                                , resultSet.getLong("cur_semester_education")
                                , resultSet.getLong("program"));
                    }
                }                
            }
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return group;
    }

    @Override
    public Group getByProgram(String program) throws SQLException {
        Group result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE program= ?")) {
                preparedStatement.setString(1, program);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        Group group = new Group(
                                resultSet.getLong("id")
                                , resultSet.getLong("cur_semester_education")
                                , resultSet.getLong("program"));
                        result = group;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<Group> getAll() throws SQLException {
        ArrayList<Group> result = new ArrayList<>();
      
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM groups")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Group group = new Group(
                                resultSet.getLong("id")
                                , resultSet.getLong("cur_semester_education")
                                , resultSet.getLong("program"));
                        result.add(group);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(Group group) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO groups (cur_semester_education, program) VALUES (?,?)";

        execureStatement(group, sql);
        logger.info("Class "+ClassName+" method add finished");
    }

    private void execureStatement(Group group, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, group.getCurSemesterEducation());
                statement.setLong(2, group.getProgram());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Group group) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + group.getId());

        String sql = "UPDATE groups SET cur_semester_education = ?, program = ? WHERE id = "+group.getId()+"";

        execureStatement(group, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + group.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM groups WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
