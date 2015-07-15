package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.*;

import java.util.Scanner;

/**
 * Created by Yakov on 29.06.2015.
 */
public class EditElementCycle {
    private String cycleInfo;
    private IHaveName element;
    private ElementCollection parentCollection;
    private ElementCollection roles;
    private ElementCollection templates;

    public EditElementCycle(IHaveName element, ElementCollection parentCollection, ElementCollection roles, ElementCollection templates) {
        this.element = element;
        this.parentCollection = parentCollection;
        this.roles = roles;
        this.templates = templates;
    }

    public void run() {
        cycleInfo = elementDescription();
        while(true) {
            System.out.printf(cycleInfo);
            UserOption option = readOption();
            switch(option) {
                case EditName:
                    editName();
                    break;
                case AddElement:
                    addRoleToElement();
                    break;
                case DeleteElement:
                    deleteRoleFromElement();
                    break;
                case EditTimetables:
                    EditTimetablesCycle editTimetablesCycle = new EditTimetablesCycle(element);
                    editTimetablesCycle.run(templates);
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
            sb.append(String.format("Список ролей элемента:"));
            IHaveList temp = (IHaveList) element;
            for (IHaveName role : temp.getList()) {
                sb.append(role.getName());
                sb.append(" ");
            }
            sb.append("\r\n");
        }

        sb.append("1 - редактировать имя");
        sb.append("\r\n");
        sb.append("2 - добавить роль в список");
        sb.append("\r\n");
        sb.append("3 - удалить роль из списка");
        sb.append("\r\n");
        if(element instanceof Person) {
            sb.append("4 - редактировать расписания");
            sb.append("\r\n");
        }
        sb.append("0 - выход");
        sb.append("\r\n");
        return sb.toString();
    }

    private UserOption readOption() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if (inputString.equals("1")) {
            return UserOption.EditName;
        }
        else if (inputString.equals("2")) {
            return UserOption.AddElement;
        }
        else if (inputString.equals("3")) {
            return UserOption.DeleteElement;
        }
        else if (inputString.equals("4") && element instanceof Person) {
            return UserOption.EditTimetables;
        }
        else if (inputString.equals("0")) {
            return UserOption.Quit;
        }
        return UserOption.Undefined;
    }

    public enum UserOption {
        EditName,
        AddElement,
        DeleteElement,
        EditTimetables,
        Quit,
        Undefined
    }

    private void editName() {
        Scanner scanner = new Scanner(System.in);
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            System.out.println("Введите новое имя:");
            String newName = scanner.nextLine();
            parentCollection.renameElement(element.getName(), newName);
            cycleInfo = elementDescription();
        }
    }

    private void addRoleToElement() {
        if(!(element instanceof IHaveList)) {
            System.out.println("Для данного типа элементов нельзя поменять список!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(IHaveName role : roles.values()) {
            sb.append(String.format("%d. ", i++));
            sb.append(role.getName());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер элемента, который хотите добавить:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString);
        String key = roles.getName(inputNumber);
        System.out.println(String.format("Вы действительно хотите добавить роль %s?(1/0)", key));
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            ((IHaveList) element).addRole(roles.get(key));
            System.out.println(String.format("Роль %s была добавлена", key));
            cycleInfo = elementDescription();
        }
    }

    private void deleteRoleFromElement() {
        if(!(element instanceof IHaveList)) {
            System.out.println("Для данного типа элементов нельзя поменять список!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        // list all elements with corresponding numbers (1-N)
        int i = 1;
        for(IHaveName role : ((IHaveList) element).getList()) {
            sb.append(String.format("%d. ", i++));
            sb.append(role.getName());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер элемента, который хотите удалить:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString);
        String key = roles.getName(inputNumber);
        System.out.println(String.format("Вы действительно хотите удалить роль %s? (1/0)", key));
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            ((IHaveList) element).deleteRole(roles.get(key));
            System.out.println(String.format("Роль %s была удалена", key));
            cycleInfo = elementDescription();
        }
    }
}