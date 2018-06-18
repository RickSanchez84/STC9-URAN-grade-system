package ru.innopolis.stc9.pojo;

public class Group {

  private long id;
  private long curSemesterEducation;
    private Program program;

    public Group(long id, long curSemesterEducation, Program program) {
    this.id = id;
    this.curSemesterEducation = curSemesterEducation;
    this.program = program;
  }

    public Group(long curSemesterEducation, Program program) {
    this.curSemesterEducation = curSemesterEducation;
    this.program = program;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCurSemesterEducation() {
    return curSemesterEducation;
  }

  public void setCurSemesterEducation(long curSemesterEducation) {
    this.curSemesterEducation = curSemesterEducation;
  }


    public Program getProgram() {
    return program;
  }

    public void setProgram(Program program) {
    this.program = program;
  }

}
