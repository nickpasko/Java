package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.Roster;
import org.sarfins.clinic.files.DataLoader;
import org.sarfins.clinic.files.DataSaver;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Yakov on 22.07.2015.
 */
public class AdminMenuCycle {
    public void run(Roster roster) throws IOException {
        SubMenuCycle subCycle = new SubMenuCycle();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.printf(outputString());
            String inputString = scanner.nextLine();
            if(inputString == "0") {
                break;
            }
            if (inputString.equals("1")) {
                subCycle.run(roster.roles, roster.roles, roster.templates);
            }
            else if (inputString.equals("2")) {
                subCycle.run(roster.persons, roster.roles, roster.templates);
            }
            else if (inputString.equals("3")) {
                subCycle.run(roster.templates, roster.roles, roster.templates);
            }
            else if (inputString.equals("4")) {
                saveData(roster);
            }
            else if (inputString.equals("5")) {
                Roster tmpRoster = loadData();
                if(tmpRoster != null)
                    roster = tmpRoster;

            }
        }
    }

    private static Roster loadData() throws IOException {
        try {
            return DataLoader.load();
        }
        catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
            throw e;
        }
    }

    private static void saveData(Roster roster) throws IOException {
        try {
            DataSaver.save(roster);
        }
        catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
            throw e;
        }
    }

    private static String outputString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Здравствуйте!");
        sb.append("\r\n");
        sb.append("Введите:");
        sb.append("\r\n");
        sb.append("1 - список ролей");
        sb.append("\r\n");
        sb.append("2 - список людей");
        sb.append("\r\n");
        sb.append("3 - список шаблонов");
        sb.append("\r\n");
        sb.append("4 - сохранить данные");
        sb.append("\r\n");
        sb.append("5 - считать данные");
        sb.append("\r\n");
        sb.append("0 - выйти из программы");
        sb.append("\r\n");
        return sb.toString();
    }
}
