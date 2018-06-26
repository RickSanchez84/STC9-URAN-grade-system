package ru.innopolis.stc9.pojo;

public class GroupStructure {

  private long id;
    private String nameGroup;
    private Person studentItem;
  private long groupItem;

    public GroupStructure(long id, String nameGroup, Person studentItem, long groupItem) {
    this.id = id;
        this.nameGroup = nameGroup;
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

    public GroupStructure(String nameGroup, Person studentItem) {
        this.nameGroup = nameGroup;
        this.studentItem = studentItem;
    }

    public GroupStructure(String nameGroup, Person studentItem, long groupItem) {
        this.nameGroup = nameGroup;
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Person getStudentItem() {
    return studentItem;
  }

    public void setStudentItem(Person studentItem) {
    this.studentItem = studentItem;
  }

  public long getGroupItem() {
    return groupItem;
  }

  public void setGroupItem(long groupItem) {
    this.groupItem = groupItem;
  }
}
