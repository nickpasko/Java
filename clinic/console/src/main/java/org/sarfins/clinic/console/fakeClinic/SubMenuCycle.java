package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.ElementCollection;
import org.sarfing.clinic.model.ElementCreator;
import org.sarfing.clinic.model.ElementType;
import org.sarfing.clinic.model.IHaveName;

import java.util.*;

/**
 * Created by Yakov on 03.05.2015.
 */
public class SubMenuCycle {
    private String cycleInfo;
    private ElementCollection roles;

    public void run(ElementCollection elements, ElementCollection roles) {
        cycleInfo = listElements(elements);
        this.roles = roles;
        while(true) {
            System.out.printf(cycleInfo);
            UserOption option = readOption();
            switch(option) {
                case Add:
                    addItem(elements);
                    break;
                case Edit:
                    editItem(elements);
                    break;
                case Delete:
                    promtDeleteItem(elements);
                    break;
                case Quit:
                    return;
            }
        }
    }

//    private void addItem(Map<String, IHaveName> elements) {
    private void addItem(ElementCollection elements) {
        System.out.println("Введите имя элемента, который хотите добавить:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if(elements.containsKey(inputString)) {
            System.out.println("Ошибка! Элемент с таким именем уже существует");
            return;
        }

        //ElementType type = elements.values().iterator().next().getType();
        IHaveName newElement = ElementCreator.createElement(elements.type, inputString);
        elements.put(inputString, newElement);
        try {
            System.out.println(String.format("Элемент %s был добавлен", newElement.getName()));
        }
        catch(NullPointerException e) {
            System.out.println("Произошла ошибка: элемент не был создан");
        }
        cycleInfo = listElements(elements);
    }

    private void editItem(ElementCollection elements) {
        StringBuilder sb = new StringBuilder();
        // list all elements with corresponding numbers (1-N)
        int i = 1;
        for(IHaveName element : elements.values()) {
            sb.append(String.format("%d. ", i++));
            sb.append(element.getName());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер элемента, который хотите редактировать:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString);
        String oldName = elements.getName(inputNumber);

        ListElementCycle editListCycle = new ListElementCycle(elements.getElementByName(oldName), elements, roles);
        editListCycle.run();
        // ask for a number of element to delete
        // where 0 == cancel
        // delete this element from Map, or exit if 0
    }

    private void promtDeleteItem(ElementCollection elements) {
        StringBuilder sb = new StringBuilder();
        // list all elements with corresponding numbers (1-N)
        int i = 1;
        for(IHaveName element : elements.values()) {
            sb.append(String.format("%d. ", i++));
            sb.append(element.getName());
            sb.append("\r\n");
        }
        System.out.print(sb.toString());
        System.out.println("Введите номер элемента, который хотите удалить:");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int inputNumber = Integer.parseInt(inputString);
        String key = elements.getName(inputNumber);
        System.out.println(String.format("Вы действительно хотите удалить человека номер %d по имени %s? (1/0)", inputNumber, key));
        String inputNumberConfirm = scanner.nextLine();
        if(inputNumberConfirm.equals("1")) {
            elements.remove(key);
            System.out.println(String.format("Элемент %d был удален", inputNumber));
            cycleInfo = listElements(elements);
        }
        // ask for a number of element to delete
        // where 0 == cancel
        // delete this element from Map, or exit if 0
    }

    private UserOption readOption() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        if (inputString.equals("1")) {
            return UserOption.Add;
        }
        else if (inputString.equals("2")) {
            return UserOption.Edit;
        }
        else if (inputString.equals("3")) {
            return UserOption.Delete;
        }
        else if (inputString.equals("0")) {
            return UserOption.Quit;
        }
        return UserOption.Undefined;
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
        sb.append("1 - добавить");
        sb.append("\r\n");
        sb.append("2 - редактировать");
        sb.append("\r\n");
        sb.append("3 - удалить");
        sb.append("\r\n");
        sb.append("0 - выход");
        sb.append("\r\n");
        return sb.toString();
    }
    public enum UserOption {
        Add,
        Edit,
        Delete,
        Quit,
        Undefined
    }
}
