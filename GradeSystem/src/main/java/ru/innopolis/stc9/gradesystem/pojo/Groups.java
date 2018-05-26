package ru.innopolis.stc9.gradesystem.pojo;

public class Groups {
    private int id;
    private int semester_education;
    private int id_programm;

    public Groups(int id, int semester_education, int id_programm) {
        this.id = id;
        this.semester_education = semester_education;
        this.id_programm = id_programm;
    }

    public Groups(int semester_education, int id_programm) {
        this.semester_education = semester_education;
        this.id_programm = id_programm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester_education() {
        return semester_education;
    }

    public void setSemester_education(int semester_education) {
        this.semester_education = semester_education;
    }

    public int getId_programm() {
        return id_programm;
    }

    public void setId_programm(int id_programm) {
        this.id_programm = id_programm;
    }
}
