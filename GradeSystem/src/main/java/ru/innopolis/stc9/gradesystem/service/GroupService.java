package ru.innopolis.stc9.gradesystem.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.gradesystem.db.dao.groups.GroupsDAO;
import ru.innopolis.stc9.gradesystem.db.dao.groups.GroupsDAOImpl;
import ru.innopolis.stc9.gradesystem.pojo.Groups;

public class GroupService {
    private static final Logger logger = Logger.getLogger(GroupService.class);
    private GroupsDAO groupsDAO = new GroupsDAOImpl();

    public void updateGroup(Groups groups) {
        groupsDAO.updateGroup(groups);
        logger.info("Update group");
    }

    public void deleteGroup(int id) {
        if (groupsDAO.deleteGroup(id)) {
            logger.info("deleted");
        } else {
            logger.info(" Is not deleted,sorry bro:( ");
        }
    }

    public void addGroup(Groups groups) {
        groupsDAO.addGroup(groups);
        logger.info("Added this groups.Don`t worry bro");
    }

    public void getGroupById(int id) {
        groupsDAO.getGroupByID(id);
        logger.info("Get group");
    }
}
