package ru.innopolis.stc9.pojo;


import java.util.ArrayList;
import java.util.List;


public class Schedule {
    private List<String> weekDay = new ArrayList<>();
    private List<String> lessonNumber = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public Schedule() {
    }

    public void initStatState() {
        weekDay.add("Понедельник");
        weekDay.add("Вторник");
        weekDay.add("Среда");
        weekDay.add("Четверг");
        weekDay.add("Пятница");
        weekDay.add("Суббота");
        weekDay.add("Воскресенье");

        lessonNumber.add("урок 1");
        lessonNumber.add("урок 2");
        lessonNumber.add("урок 3");
        lessonNumber.add("урок 4");
        lessonNumber.add("урок 5");

        list.add("p11");
        list.add("p12");
        list.add("p13");
        list.add("p14");
        list.add("p15");
    }

    public List<String> getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(List<String> weekDay) {
        this.weekDay = weekDay;
    }

    public List<String> getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(List<String> lessonNumber) {
        this.lessonNumber = lessonNumber;
    }


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
