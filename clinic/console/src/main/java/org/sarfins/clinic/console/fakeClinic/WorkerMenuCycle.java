package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.ElementCollection;
import org.sarfing.clinic.model.Person;
import org.sarfing.clinic.model.Roster;
import org.sarfing.clinic.model.Timetable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yakov on 23.07.2015.
 */
public class WorkerMenuCycle {
    public void run(String personName, ElementCollection persons) {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        int currentHour = Integer.parseInt(dateFormat.format(date));
        Person currentPerson = (Person) persons.get(personName);

        for(Timetable timetable: currentPerson.getTimetables()) {
            if(currentHour >= timetable.startHour && currentHour <= timetable.endHour) {
                System.out.println("Вы работаете по шаблону: " + timetable.template.getName());
                break;
            }
        }
    }
}
