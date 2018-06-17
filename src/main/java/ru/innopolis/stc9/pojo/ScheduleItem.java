package ru.innopolis.stc9.pojo;


public class ScheduleItem {

    private long id;
    private long dayOfWeek;
    private long lessonNumber;
    private long groupItem;
    private long subject;
    private long room;

    public ScheduleItem(long id, long dayOfWeek, long lessonNumber, long groupItem, long subject, long room) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.groupItem = groupItem;
        this.subject = subject;
        this.room = room;
    }

    public ScheduleItem(long dayOfWeek, long lessonNumber, long groupItem, long subject, long room) {
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.groupItem = groupItem;
        this.subject = subject;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(long dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public long getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(long lessonNummber) {
        this.lessonNumber = lessonNummber;
    }

    public long getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(long groupItem) {
        this.groupItem = groupItem;
    }

    public long getSubject() {
        return subject;
    }

    public void setSubject(long subject) {
        this.subject = subject;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }

}
