package ru.innopolis.stc9.pojo;

public class Teacher {

  private long id;
  private long teacherItem;
  private long subjectItem;

  public Teacher(long id, long teacherItem, long subjectItem) {
    this.id = id;
    this.teacherItem = teacherItem;
    this.subjectItem = subjectItem;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTeacherItem() {
    return teacherItem;
  }

  public void setTeacherItem(long teacherItem) {
    this.teacherItem = teacherItem;
  }

  public long getSubjectItem() {
    return subjectItem;
  }

  public void setSubjectItem(long subjectItem) {
    this.subjectItem = subjectItem;
  }

}
