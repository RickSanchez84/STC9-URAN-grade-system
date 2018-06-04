package ru.innopolis.stc9.pojo;

public class Speciality {

  private long id;
  private String name;
  private long semesterCount;

  public Speciality(long id, String name, long semesterCount) {
    this.id = id;
    this.name = name;
    this.semesterCount = semesterCount;
  }

  public Speciality(String name, long semesterCount) {
    this.name = name;
    this.semesterCount = semesterCount;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getSemesterCount() {
    return semesterCount;
  }

  public void setSemesterCount(long semesterCount) {
    this.semesterCount = semesterCount;
  }

}
