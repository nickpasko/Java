package org.sarfins.clinic.files;

import org.sarfing.clinic.model.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Yakov on 13.06.2015.
 */
public class DataSaver {
    public static void save(Roster roster) throws IOException {
        savePersons(roster.persons);
        saveRoles(roster.roles);
        saveTemplates(roster.templates);
    }

    private static void saveTemplates(ElementCollection templates) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Yakov\\IdeaProjects\\clinic\\templates.txt"),"UTF-8"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Yakov\\IdeaProjects\\clinic\\templates.tsv"), false));
        for(IHaveName templateAsInterface: templates.values()) {
            Template template;
            try {
                template = (Template) templateAsInterface;
            }
            catch (ClassCastException e)
            {
                bw.close();
                throw new IllegalArgumentException("Map<String, Template> expected.", e);
            }
            bw.write(template.getName());
            bw.write("\t" + String.valueOf(template.getType()));
            List<Role> participants = template.getParticipants();
            for(Role participant: participants) {
                bw.write("\t" + participant.getName());
            }
            bw.newLine();
        }
        bw.close();
    }

    private static void saveRoles(ElementCollection roles) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Yakov\\IdeaProjects\\clinic\\roles.txt"),"UTF-8"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Yakov\\IdeaProjects\\clinic\\roles.tsv"), false));
        for(IHaveName role: roles.values()) {
            bw.write(role.getName());
            bw.newLine();
        }
        bw.close();
    }

    private static void savePersons(ElementCollection persons) throws IOException, ClassCastException {
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Yakov\\IdeaProjects\\clinic\\persons.txt"),"UTF-8"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Yakov\\IdeaProjects\\clinic\\persons.tsv"), false));
        for(IHaveName personByInterface: persons.values()) {
            Person person;
            try {
                person = (Person) personByInterface;
            }
            catch (ClassCastException e)
            {
                bw.close();
                throw new IllegalArgumentException("Map<String, Person> expected.", e);
            }
            bw.write(person.getName());
            List<Role> roles = person.getRoles();
            for(Role role: roles) {
                bw.write("\t" + role.getName());
            }
            bw.newLine();
        }
        bw.close();
    }
}