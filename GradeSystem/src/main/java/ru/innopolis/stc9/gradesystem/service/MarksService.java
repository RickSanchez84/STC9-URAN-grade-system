package ru.innopolis.stc9.gradesystem.service;

import ru.innopolis.stc9.gradesystem.db.dao.marks.MarksDao;
import ru.innopolis.stc9.gradesystem.db.dao.marks.MarksDaoImpl;
import ru.innopolis.stc9.gradesystem.pojo.Mark;

import java.sql.SQLException;
import java.util.List;

public class MarksService {
    MarksDao md = new MarksDaoImpl();

    public List<Mark> marks(int studentID
    ) {
        try {
            return md.getAllByStudent(studentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
