package ru.innopolis.stc9.pojo;


public class Program {

  private long id;
  private long specialty;
  private long semester;
  private long subject;
  private long hours;

  public Program(long id, long specialty, long semester, long subject, long hours) {
    this.id = id;
    this.specialty = specialty;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public Program(long specialty, long semester, long subject, long hours) {
    this.specialty = specialty;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSpecialty() {
    return specialty;
  }

  public void setSpecialty(long specialty) {
    this.specialty = specialty;
  }


  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }


  public long getSubject() {
    return subject;
  }

  public void setSubject(long subject) {
    this.subject = subject;
  }


  public long getHours() {
    return hours;
  }

  public void setHours(long hours) {
    this.hours = hours;
  }

}
