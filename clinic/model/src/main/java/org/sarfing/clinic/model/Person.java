package org.sarfing.clinic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Person implements IHaveName, IHaveList, IWorkInClinic {
    public String name;
    public List <Role> roles;
    public List<Timetable>timetables;

    public Person(String name, List <Role> roles) {
        this.name = name;
        this.roles = roles;
        this.timetables = new ArrayList<Timetable>();
    }

    public List<Timetable> getTimetables() {
        return timetables;
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
    public List<Role> getList() {
        return roles;
    }

    @Override
    public void addRole(IHaveName element) {
        roles.add((Role) element);
    }

    @Override
    public void deleteRole(IHaveName element) {
        roles.remove((Role) element);
    }

    @Override
    public Boolean isReallyWorking() {
        for(Role role: roles) {
            if(role.isReallyWorking() == true) {
                return true;
            }
        }
        return false;
    }
}
