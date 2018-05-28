package ru.innopolis.stc9.gradesystem.db.dao.groups;

import ru.innopolis.stc9.gradesystem.pojo.Groups;

public interface GroupsDAO {

    public void updateGroup(Groups groups);

    public boolean deleteGroup(int id);

    public void addGroup(Groups groups);

    public Groups getGroupByID(int id);

}
