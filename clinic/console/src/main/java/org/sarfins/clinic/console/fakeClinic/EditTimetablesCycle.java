package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.*;

import java.util.Scanner;

/**
 * Created by Yakov on 09.07.2015.
 */
public class EditTimetablesCycle {
    private String cycleInfo;
    private Person person;
    private ElementCollection templates;

    public EditTimetablesCycle(IHaveName element) {
        if(element.getType() != ElementType.Person) {
            throw new IllegalArgumentException("Ожидается персон");
        }
        this.person = (Person)element;
    }

    public void run(ElementCollection templates) {
        this.templates = templates;
        if(templates.type != ElementType.Template) {
            throw new IllegalArgumentException("Ожидается коллекция темплейтов");
        }
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
        sb.append(String.format("Элемент %s:", person.getName()));
        sb.append("\r\n");
        if(person instanceof IHaveList) {
            sb.append(String.format("Список расписаний элемента:"));
            Person elementTemp = (Person) person;
            for (Timetable timetable : elementTemp.getTimetables()) {
                sb.append(timetable.toString());
                sb.append("\r\n");
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
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(IHaveName template : templates.values()) {
            sb.append(String.format("%d. ", i++));
            sb.append(template.getName());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер шаблона, по которому создается расписание:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString);
        String key = templates.getName(inputNumber);
        System.out.println(String.format("Вы действительно хотите добавить шаблон %s?(1/0)", key));
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            Timetable tmp = new Timetable((Template)templates.get(key));
            person.addTimetable(tmp);
            System.out.println(String.format("Шаблон %s был добавлен", key));
            cycleInfo = elementDescription();
        }
    }

    private void deleteTimetable() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(Timetable timetable : person.getTimetables()) {
            sb.append(String.format("%d. ", i++));
            sb.append(timetable.toString());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер шаблона, который хотите удалить:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString) - 1;
        System.out.println(String.format("Вы действительно хотите удалить расписание %d?(1/0)", inputNumber));
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            person.deleteTimetable(inputNumber);
            System.out.println(String.format("Расписание %d было удалено", inputNumber));
            cycleInfo = elementDescription();
        }
    }

    private void editTimetable() {

    }
}
