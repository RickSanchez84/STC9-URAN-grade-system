package ru.innopolis.stc9.gradesystem.pojo;

import java.sql.Timestamp;

public class Mark {
    private int id;
    private Course course;
    private Student student;
    private User faculty;
    private double markValue;
    private Timestamp timestamp;

    public Mark(int id
            , Course course
            , Student student
            , User faculty
            , double markValue
            , Timestamp timestamp) {

        this.id = id;
        this.course = course;
        this.student = student;
        this.faculty = faculty;
        this.markValue = markValue;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }

    public double getMarkValue() {
        return markValue;
    }

    public void setMarkValue(double markValue) {
        this.markValue = markValue;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
