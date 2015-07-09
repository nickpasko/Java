package org.sarfing.clinic.model;

/**
 * Created by Yakov on 26.04.2015.
 */
public class Role implements IHaveName, IWorkInClinic {
    public String name;
    public Boolean isWorkerRole = false;


    public Role(String name, Boolean isWorkerRole) {
        this.name = name;
        this.isWorkerRole = isWorkerRole;
    }

    public Boolean getStatus() {
        return isWorkerRole;
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

    @Override
    public Boolean isReallyWorking() {
        return isWorkerRole;
    }
}
