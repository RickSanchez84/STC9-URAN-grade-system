package ru.innopolis.stc9.gradesystem.pojo;

public class Student {
    private int id;
    private String name;
    private int age;
    private float progress;
    private Speciality speciality;

    public Student(int id
            , String name
            , int age
            , float progress
            , Speciality speciality) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.progress = progress;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}

