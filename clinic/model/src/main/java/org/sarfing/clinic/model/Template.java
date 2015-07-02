package org.sarfing.clinic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Template implements IHaveName, IHaveList {
    public String title;
    public List<Role> participants = new ArrayList<Role>();
    public String taskType = "Template";

    public Template(String title) { this.title = title; }

    public Template(String title, String taskType, List<Role> participants) {
        this.title = title;
        this.participants = participants;
    }


    @Override
    public String getName() {
        return title;
    }

    @Override
    public ElementType getType() {
        return ElementType.Template;
    }

    @Override
    public void setName(String newName) {
        title = newName;
    }

    public List <Role> getParticipants() {
        return participants;
    }

    @Override
    public List<Role> getList() {
        return participants;
    }

    @Override
    public void addRole(IHaveName element) {
        participants.add((Role) element);
    }

    @Override
    public void deleteRole(IHaveName element) {
        participants.remove((Role) element);
    }
}
