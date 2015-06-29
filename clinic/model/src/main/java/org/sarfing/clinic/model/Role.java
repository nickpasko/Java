package org.sarfing.clinic.model;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Role implements IHaveName {
    public String name;


    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ElementType getType() {
        return ElementType.Role;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }
}
