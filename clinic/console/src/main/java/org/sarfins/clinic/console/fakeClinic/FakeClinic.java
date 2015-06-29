package org.sarfins.clinic.console.fakeClinic;

import org.sarfing.clinic.model.Roster;
import org.sarfins.clinic.files.DataLoader;
import org.sarfins.clinic.files.DataSaver;

import java.io.IOException;
import java.util.*;

/**
 * Created by Yakov on 26.04.2015.
 */
public class FakeClinic {
    public static void main(String[] args) throws IOException {


        //FakeClinicInit clinicInit = new FakeClinicInit();
        Roster roster = DataLoader.load();
        //Roster roster = new Roster();

        //SubMenuCycle roleCycle = new SubMenuCycle(roster.roles);
        //SubMenuCycle personCycle = new SubMenuCycle(roster.persons);
        //SubMenuCycle templateCycle = new SubMenuCycle(roster.templates);
        SubMenuCycle subCycle = new SubMenuCycle();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.printf(outputString());
            String inputString = scanner.nextLine();
            if(inputString == "0") {
                break;
            }
            if (inputString.equals("1")) {
                subCycle.run(roster.roles);
            }
            else if (inputString.equals("2")) {
                subCycle.run(roster.persons);
            }
            else if (inputString.equals("3")) {
                subCycle.run(roster.templates);
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

    private static Roster loadData() {
        try {
            return DataLoader.load();
        }
        catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
            return null;
        }
    }

    private static void saveData(Roster roster) {
        try {
            DataSaver.save(roster);
        }
        catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
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