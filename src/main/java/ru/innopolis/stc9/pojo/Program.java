package ru.innopolis.stc9.pojo;


public class Program {

  private long id;
  private Speciality specialty;
  private long semester;
  private Subject subject;
  private long hours;

  public Program(long id, Speciality specialty, long semester, Subject subject, long hours) {
    this.id = id;
    this.specialty = specialty;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public Program(Speciality specialty, long semester, Subject subject, long hours) {
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

  public Speciality getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Speciality specialty) {
    this.specialty = specialty;
  }

  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public long getHours() {
    return hours;
  }

  public void setHours(long hours) {
    this.hours = hours;
  }
}
