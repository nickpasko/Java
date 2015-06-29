package org.sarfing.clinic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Person implements IHaveName, IHaveList {
    public String name;
    public List <Role> roles;
    public List<Timetable>timetables;

    public Person(String name, List <Role> roles) {
        this.name = name;
        this.roles = roles;
        this.timetables = new ArrayList<Timetable>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ElementType getType() {
        return ElementType.Person;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    @Override
    public List getList() {
        return roles;
    }

    @Override
    public void addRole(Role element) {
        roles.add(element);
    }

    @Override
    public void deleteRole(Role element) {
        roles.remove(element);
    }
}
