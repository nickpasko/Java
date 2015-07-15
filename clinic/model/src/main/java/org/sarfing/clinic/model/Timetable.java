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

    @Override
    public String toString() {
        return String.format("%s, %d-%d: %s", weekDay, startHour, endHour, template.getName());
    }
}
