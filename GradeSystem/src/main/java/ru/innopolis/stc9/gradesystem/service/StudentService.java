package ru.innopolis.stc9.gradesystem.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.gradesystem.db.dao.students.StudentsDao;
import ru.innopolis.stc9.gradesystem.db.dao.students.StudentsDaoImpl;
import ru.innopolis.stc9.gradesystem.pojo.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final Logger logger = Logger.getLogger(StudentService.class);
    private static final Logger loggerError = Logger.getLogger(StudentService.class);
    private StudentsDao studentDao = new StudentsDaoImpl();

    public void updateById(Student student) {
        logger.info(this.getClass().getName() + " method updateById started, id = " + student.getId());
        try {
            studentDao.update(student);
        } catch (SQLException e) {
            loggerError.error("Error at method updateById, id = " + student.getId(), e);
        }
        logger.info(this.getClass().getName() + " method updateById finished, id = " + student.getId());
    }

    public Student getById(int id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Student student = null;
        try {
            student = studentDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return student;
    }

    public void deleteById(int id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            studentDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    public void add(Student student) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            studentDao.add(student);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    public List<Student> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = studentDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return studentList;
    }
}
