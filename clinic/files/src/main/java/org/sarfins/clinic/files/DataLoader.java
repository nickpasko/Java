package org.sarfins.clinic.files;

import com.sun.media.sound.InvalidFormatException;
import org.sarfing.clinic.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yakov on 13.06.2015.
 */
public class DataLoader {

    public static Roster load() throws IOException {
        Roster roster = new Roster();
        roster.roles = loadRoles();
        roster.persons = loadPersons(roster.roles);
        roster.templates = loadTemplates(roster.roles);
        loadTimetables(roster.persons, roster.templates);
        roster.users = loadUsers(roster.persons);
        return roster;
    }

    private static ElementCollection loadUsers(ElementCollection persons) {
        Map<String, IHaveName> usersMap = new HashMap<String, IHaveName>();
        try {
            File file = new File("C:\\Users\\Yakov\\IdeaProjects\\Java\\clinic\\users.tsv");
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    String[] singleVar = s.split("\t");
                    Person tmpPerson = (Person) persons.get(singleVar[2]);
                    User user = new User(singleVar[0], singleVar[1], tmpPerson);
                    usersMap.put(singleVar[0], user);
                }
            }
            finally {
                in.close();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new ElementCollection(usersMap, ElementType.User);
    }

    private static void loadTimetables(ElementCollection persons, ElementCollection templates) {
        try {
            File file = new File("C:\\Users\\Yakov\\IdeaProjects\\Java\\clinic\\timetables.tsv");
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    String[] singleVar = s.split("\t");
                    Template temp = (Template) templates.get(singleVar[4]);
                    Timetable timetable = new Timetable(singleVar[1], Integer.parseInt(singleVar[2]), Integer.parseInt(singleVar[3]), temp);
                    IHaveName tempPerson = persons.get(singleVar[0]);
                    IHaveName tempTolik = persons.get("Толик");
                    Person person = (Person) tempPerson;
                    person.addTimetable(timetable);
                }
            }
            finally {
                in.close();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ElementCollection loadTemplates(ElementCollection roles) {
        if(roles.type != ElementType.Role)
            throw new IllegalArgumentException(String.format("Ожидается список ролей, а пришел список %s", roles.type));
        Map<String, IHaveName> templatesMap = new HashMap<String, IHaveName>();
        try {
            File file = new File("C:\\Users\\Yakov\\IdeaProjects\\Java\\clinic\\templates.tsv");
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    String[] singleVar = s.split("\t");
                    List<Role> rolesList = new ArrayList<Role>();
                    for(int i = 2; i < singleVar.length; i++) {
                        IHaveName tmpRole = roles.getElementByName(singleVar[i]);
                        if(tmpRole != null)
                        rolesList.add((Role)(roles.getElementByName(singleVar[i])));
                    }
                    templatesMap.put(singleVar[0], new Template(singleVar[0], singleVar[1], rolesList));
                }
            }
            finally {
                in.close();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new ElementCollection(templatesMap, ElementType.Template);
    }

    private static ElementCollection loadRoles() {
        Map<String, IHaveName> rolesMap = new HashMap<String, IHaveName>();
        try {
            File file = new File("C:\\Users\\Yakov\\IdeaProjects\\Java\\clinic\\roles.tsv");
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    String[] singleVar = s.split("\t");
                    Role role = new Role(singleVar[0], singleVar[1] == "true");
                    rolesMap.put(singleVar[0], role);
                }
            }
            finally {
                in.close();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new ElementCollection(rolesMap, ElementType.Role);
    }

    private static ElementCollection loadPersons(ElementCollection roles) {
        if(roles.type != ElementType.Role)
            throw new IllegalArgumentException(String.format("Ожидается список ролей, а пришел список %s", roles.type));
        Map<String, IHaveName> personsMap = new HashMap<String, IHaveName>();
        try {
            File file = new File("C:\\Users\\Yakov\\IdeaProjects\\Java\\clinic\\persons.tsv");
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) { // цикл по людчм в файле
                    String[] singleVar = s.split("\t");
                    List<Role> rolesList = new ArrayList<Role>(singleVar.length - 1);
                    for(int i = 1; i < singleVar.length; i++) { // цикл по ролям в строке
                        IHaveName tmpRole = roles.getElementByName(singleVar[i]);
                        if(tmpRole != null)
                            rolesList.add((Role)(roles.getElementByName(singleVar[i])));
                    }
                    Person person = new Person(singleVar[0], rolesList);
                    personsMap.put(singleVar[0], person);
                }
            }
            finally {
                in.close();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        return new ElementCollection(personsMap, ElementType.Person);
    }
}
