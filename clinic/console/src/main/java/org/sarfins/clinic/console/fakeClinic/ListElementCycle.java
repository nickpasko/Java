package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Yakov on 29.06.2015.
 */
public class ListElementCycle {
    private String cycleInfo;
    private IHaveName element;
    private ElementCollection elements;

    public ListElementCycle(IHaveName element, ElementCollection elements) {
        this.element = element;
        this.elements = elements;
    }

    public void run() {
        while(true) {
            System.out.printf(cycleInfo);
            UserOption option = readOption();
            switch(option) {
                case EditName:
                    editName();
                    break;
                case AddElement:
                    addListElement();
                    break;
                case DeleteElement:
                    deleteListElement(elements);
                    break;
                case Quit:
                    return;
            }
        }
    }

    public String listElements(ElementCollection elements) {
        StringBuilder sb = new StringBuilder();
        sb.append("Список элементов в системе:");
        sb.append("\r\n");
        for(IHaveName element : elements.values()) {
            sb.append(element.getName());
            sb.append(" ");
        }
        sb.append("\r\n");
        sb.append("1 - редактировать имя");
        sb.append("\r\n");
        sb.append("2 - добавить элемент в список");
        sb.append("\r\n");
        sb.append("3 - удалить элемент из списка");
        sb.append("\r\n");
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
        else if (inputString.equals("0")) {
            return UserOption.Quit;
        }
        return UserOption.Undefined;
    }

    public enum UserOption {
        EditName,
        AddElement,
        DeleteElement,
        Quit,
        Undefined
    }

    private void editName() {
        Scanner scanner = new Scanner(System.in);
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            System.out.println("Введите новое имя:");
            String newName = scanner.nextLine();
            elements.renameElement(element.getName(), newName);
            cycleInfo = listElements(elements);
        }
    }

    private void addListElement() {
        if(!(element instanceof IHaveList)) {
            System.out.println("Для данного типа элементов нельзя поменять список!");
            return;
        }
        ((IHaveList) element).addRole((Role) element);
    }

    private void deleteListElement(ElementCollection elements) {
        if(!(element instanceof IHaveList)) {
            System.out.println("Для данного типа элементов нельзя поменять список!");
            return;
        }
        ((IHaveList) element).deleteRole((Role) element);
    }
}
