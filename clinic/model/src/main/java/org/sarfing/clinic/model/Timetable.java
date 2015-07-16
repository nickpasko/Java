package org.sarfing.clinic.model;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Timetable {
    public String weekDay;
    public int startHour;
    public int endHour;
    public Template template;

    public Timetable(Template template) {
        this.template = template;
    }

    public Timetable(String weekDay, int startHour, int endHour, Template template) {
        this.weekDay = weekDay;
        this.startHour = startHour;
        this.endHour = endHour;
        this.template = template;
    }

    public Template getTemplate() {
        return template;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return String.format("%s, %d-%d: %s", weekDay, startHour, endHour, template.getName());
    }
}
