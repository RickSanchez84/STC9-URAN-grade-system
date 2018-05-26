package ru.innopolis.stc9.gradesystem.db.dao.groups;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.gradesystem.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.gradesystem.pojo.Groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsDAOImpl implements GroupsDAO {
    private static Logger logger = Logger.getLogger(GroupsDAOImpl.class);

    @Override
    public void updateGroup(Groups groups) {
        Connection connection = new ConnectionManagerImpl().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE groups SET   cur_semester_education=?," +
                            "program=? WHERE  id=?");
            statement.setInt(1, groups.getSemester_education());
            statement.setInt(2, groups.getId_programm());
            logger.info("update table groups");
            connection.close();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteGroup(int id) {
        Connection connection = new ConnectionManagerImpl().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM  groups WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("deleted");
            connection.close();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return true;
    }

    @Override
    public void addGroup(Groups groups) {
        Connection connection = new ConnectionManagerImpl().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT  INTO groups(id,cur_semester_education,program)" +
                            "VALUES (?,?,?)");

            statement.setInt(1, groups.getId());
            statement.setInt(1, groups.getSemester_education());
            statement.setInt(1, groups.getId_programm());
            statement.executeUpdate();
            logger.info("deleted");
            connection.close();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public Groups getGroupByID(int id) {
        Connection connection = new ConnectionManagerImpl().getConnection();
        Groups groups = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM groups WHERE id=?");

            statement.setInt(1, groups.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                groups = new Groups(
                        resultSet.getInt("id"),
                        resultSet.getInt("cur_semester_education"),
                        resultSet.getInt("program")
                );
                connection.close();
                logger.info("Groups added ");
            }
            connection.close();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return groups;
    }
}
