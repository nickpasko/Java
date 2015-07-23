package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.Role;
import org.sarfing.clinic.model.Roster;
import org.sarfing.clinic.model.User;
import org.sarfins.clinic.files.DataLoader;
import org.sarfins.clinic.files.DataSaver;

import java.io.IOException;
import java.util.*;

/**
 * Created by Yakov on 26.04.2015.
 */
public class FakeClinic {
    public static void main(String[] args) throws IOException {
        Roster roster = DataLoader.load();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        String inputLogin = scanner.nextLine();
        System.out.println("Введите пароль:");
        String inputPass = scanner.nextLine();

        User user = (User) roster.users.get(inputLogin);
        if(user != null && user.passwordOk(inputPass)) {
            System.out.println("Добро пожаловать, " + user.person.getName());
            boolean isAdmin = false;
            for(Role role : user.person.getRoles())
                if (role.getName().equals("Главврач")) {
                    isAdmin = true;
                    break;
                }

            if(isAdmin) {
                AdminMenuCycle adminMenuCycle = new AdminMenuCycle();
                adminMenuCycle.run(roster);
            }
            else {
                WorkerMenuCycle workerMenuCycle = new WorkerMenuCycle();
                workerMenuCycle.run(user.person.getName(), roster.persons);
            }
        }
        else {
            System.out.println("Не удалось авторизироваться");
        }
    }
}