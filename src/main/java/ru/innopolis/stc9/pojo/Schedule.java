package ru.innopolis.stc9.pojo;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
public class Schedule {
    private static final Logger logger = Logger.getLogger(Schedule.class);
    private static final String BEFORE = "First line.";
    private static final String AFTER = "Before exit.";
    /**
     * Day of week for view form
     */
    private List<String> weekDay = new ArrayList<>();
    /**
     * Count of lesson in a day
     */
    private List<String> lessonNumber = new ArrayList<>();
    /**
     * Draft of schedule
     */
    private List<ScheduleItem> list = new ArrayList<>();

    {
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
    }

    public Schedule() {
    }

//    public void initStatState() {
//        list.add("p11");
//        list.add("p12");
//        list.add("p13");
//        list.add("p14");
//        list.add("p15");
//    }

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

    public List<ScheduleItem> getList() {
        return list;
    }

    public void setList(List<ScheduleItem> list) {
        this.list = list;
    }

    public void add(ScheduleItem item) {
        list.add(item);
    }

    public TeacherSubject getSubjectInDraft(Group group, long dayOfWeek, long lessonNumber) {
        logger.debug(BEFORE);
        TeacherSubject result = null;
        if (group != null) {
            for (ScheduleItem item : list) {
                if (item.getDayOfWeek() == dayOfWeek && item.getLessonNumber() == lessonNumber && item.getGroupItem() == group) {
                    result = item.getSubjectWithTeacher();
                }
            }
        } else {
            logger.warn("group is null argument");
        }
        logger.info("Success? " + (result == null ? "Result is null" : result.toString()));
        logger.debug(AFTER);
        return result;
    }
}
