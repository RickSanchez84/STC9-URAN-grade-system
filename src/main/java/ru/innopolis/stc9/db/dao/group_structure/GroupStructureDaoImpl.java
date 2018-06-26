package ru.innopolis.stc9.db.dao.group_structure;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.db.dao.groups.GroupsDao;
import ru.innopolis.stc9.db.dao.person.PersonDao;
import ru.innopolis.stc9.pojo.GroupStructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupStructureDaoImpl implements GroupStructureDao {
    private static final Logger logger = Logger.getLogger(GroupStructureDaoImpl.class);
    public  final String ClassName= this.getClass().getName();

    @Autowired
    PersonDao personDao;
    @Autowired
    GroupsDao groupsDao;
    @Override
    public GroupStructure getById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method getById started, id = " + id);
        GroupStructure groupStructure = null;
    
        int iid = (int)id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM group_structure WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet  resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        groupStructure = new GroupStructure(
                                resultSet.getLong("id"),
                                resultSet.getString("name_group"),
                                personDao.getById(resultSet.getLong("student_item")),
                                resultSet.getLong("group_item"));
                    }
                }                
            }
        }

        logger.info("Class "+ClassName+" method getById finished, id = " + id);
        return groupStructure;
    }

    @Override
    public GroupStructure getByGroup(Long group) throws SQLException {
        GroupStructure result = null;        

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM group_structure WHERE group_item= ?")) {
                preparedStatement.setLong(1, group);
                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    while (resultSet.next()) {
                        GroupStructure groupStructure = new GroupStructure(
                                resultSet.getLong("id"),
                                resultSet.getString("name_group")
                                , personDao.getById(resultSet.getLong("student_item"))
                                , resultSet.getLong("group_item"));
                        result = groupStructure;
                    }
                } 
            }
        }
        return result;
    }

    @Override
    public List<GroupStructure> getAll() throws SQLException {
        ArrayList<GroupStructure> result = new ArrayList<>();
//      String getAllSQL="SELECT * FROM group_structure, persons, roles \" +\n" +
//              "                            \"WHERE roles.name='Студент' \" +\n" +
//              "                            \"AND group_structure.student_item=persons.id ";

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM group_structure " +
                            "ORDER BY id")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        GroupStructure groupStructure = new GroupStructure(
                                resultSet.getLong("id"),
                                resultSet.getString("name_group")
                                , personDao.getById(resultSet.getLong("student_item"))
                                , resultSet.getLong("group_item"));
                        result.add(groupStructure);
                    } 
                }                   
            }
        }

        return result;
    }

    @Override
    public void add(GroupStructure groupStructure) throws SQLException {
        logger.info("Class "+ClassName+" method add started");

        String sql = "INSERT INTO group_structure (student_item, group_item) VALUES (?,?)";

        execureStatement(groupStructure, sql);
        logger.info("Class "+ClassName+" method add finished");
    }

    private void execureStatement(GroupStructure groupStructure, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, groupStructure.getNameGroup());
                statement.setLong(2, groupStructure.getStudentItem().getId());
                
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(GroupStructure groupStructure) throws SQLException {
        logger.info("Class "+ClassName+" method update started, id = " + groupStructure.getId());

        String sql = "UPDATE group_structure SET student_item = ?, group_item = ? WHERE id = "+groupStructure.getId()+"";

        execureStatement(groupStructure, sql);
        logger.info("Class "+ClassName+" method update finished, id = " + groupStructure.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class "+ClassName+" method deleteById started, id = " + id);
        String sql = "DELETE FROM group_structure WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class "+ClassName+" method deleteById finished, id = " + id);
    }
}
