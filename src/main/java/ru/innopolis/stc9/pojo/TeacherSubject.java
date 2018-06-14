package ru.innopolis.stc9.pojo;

public class TeacherSubject {

  private long id;
  private long teacherItem;
  private long subjectItem;
  private String teacherName;
  private String subjectName;

  public TeacherSubject(long id, long teacherItem, long subjectItem) {
    this.id = id;
    this.subjectItem = subjectItem;
    this.teacherItem = teacherItem;
  }

  public TeacherSubject(long teacherItem, long subjectItem) {
    this.teacherItem = teacherItem;
    this.subjectItem = subjectItem;
  }

  public TeacherSubject(long id, String subjectName, String teacherName) {
    this.id = id;
    this.teacherName = teacherName;
    this.subjectName = subjectName;
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

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
}
