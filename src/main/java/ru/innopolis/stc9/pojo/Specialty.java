package ru.innopolis.stc9.pojo;

public class Specialty {

  private long id;
  private String name;
  private long semesterCount;


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
