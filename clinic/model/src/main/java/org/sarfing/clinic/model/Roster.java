package org.sarfing.clinic.model;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yakov on 02.05.2015.
 */
public class Roster {
    public ElementCollection roles;
    public ElementCollection persons;
    public ElementCollection templates;

    public Roster() {
        roles = new ElementCollection(new HashMap<String, IHaveName>(), ElementType.Role);
        persons = new ElementCollection(new HashMap<String, IHaveName>(), ElementType.Person);
        templates = new ElementCollection(new HashMap<String, IHaveName>(), ElementType.Template);
    }
}