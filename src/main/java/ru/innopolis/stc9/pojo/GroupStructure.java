package ru.innopolis.stc9.pojo;

public class GroupStructure {

  private long id;
  private long studentItem;
  private long groupItem;

  public GroupStructure(long id, long studentItem, long groupItem) {
    this.id = id;
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

  public GroupStructure(long studentItem, long groupItem) {
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getStudentItem() {
    return studentItem;
  }

  public void setStudentItem(long studentItem) {
    this.studentItem = studentItem;
  }


  public long getGroupItem() {
    return groupItem;
  }

  public void setGroupItem(long groupItem) {
    this.groupItem = groupItem;
  }

}
