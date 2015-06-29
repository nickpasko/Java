package org.sarfins.clinic.files;

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
        roster.persons = loadPersons();
        roster.roles = loadRoles();
        roster.templates = loadTemplates();
        return roster;
    }

    private static ElementCollection loadTemplates() {
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
                        rolesList.add(new Role(singleVar[i]));
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
                    Role role = new Role(s);
                    rolesMap.put(s, role);
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

    private static ElementCollection loadPersons() {
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
                        rolesList.add(new Role(singleVar[i]));
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
