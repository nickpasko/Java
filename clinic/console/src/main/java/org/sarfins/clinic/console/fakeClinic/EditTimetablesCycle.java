package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.IHaveList;
import org.sarfing.clinic.model.IHaveName;
import org.sarfing.clinic.model.Person;
import org.sarfing.clinic.model.Timetable;

import java.util.Scanner;

/**
 * Created by Yakov on 09.07.2015.
 */
public class EditTimetablesCycle {
    private String cycleInfo;
    private IHaveName element;

    public EditTimetablesCycle(IHaveName element) {
        this.element = element;
    }

    public void run() {
        cycleInfo = elementDescription();
        while (true) {
            System.out.printf(cycleInfo);
            UserOption option = readOption();
            switch (option) {
                case AddTimetable:
                    addTimetable();
                    break;
                case deleteTimetable:
                    deleteTimetable();
                    break;
                case EditTimetable:
                    editTimetable();
                    break;
                case Quit:
                    return;
            }
        }
    }

    public String elementDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Элемент %s:", element.getName()));
        sb.append("\r\n");
        if(element instanceof IHaveList) {
            sb.append(String.format("Список расписаний элемента:"));
            Person elementTemp = (Person) element;
            for (Timetable timetable : elementTemp.getTimetables()) {
                //sb.append(timetable.getName());
                sb.append(" ");
            }
            sb.append("\r\n");
        }

        sb.append("1 - добавить расписание");
        sb.append("\r\n");
        sb.append("2 - удалить расписание");
        sb.append("\r\n");
        sb.append("3 - редактировать расписание");
        sb.append("\r\n");
        sb.append("0 - выход");
        sb.append("\r\n");
        return sb.toString();
    }

    private UserOption readOption() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if (inputString.equals("1")) {
            return UserOption.AddTimetable;
        }
        else if (inputString.equals("2")) {
            return UserOption.deleteTimetable;
        }
        else if (inputString.equals("3")) {
            return UserOption.EditTimetable;
        }
        else if (inputString.equals("0")) {
            return UserOption.Quit;
        }
        return UserOption.Undefined;
    }

    public enum UserOption {
        AddTimetable,
        deleteTimetable,
        EditTimetable,
        Quit,
        Undefined
    }

    private void addTimetable() {

    }

    private void deleteTimetable() {

    }

    private void editTimetable() {

    }
}
