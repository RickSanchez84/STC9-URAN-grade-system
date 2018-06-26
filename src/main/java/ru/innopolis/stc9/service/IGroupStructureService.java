package ru.innopolis.stc9.service;

import ru.innopolis.stc9.entity.GroupStructure;

import java.util.List;

public interface IGroupStructureService {
    void update(GroupStructure groupStructure);

    GroupStructure getById(long id);

    void deleteById(long id);

    void add(GroupStructure groupStructure);

    List<GroupStructure> getAll();
}
