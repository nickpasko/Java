package org.sarfing.clinic.model;

import java.util.List;

/**
 * Created by Yakov on 29.06.2015.
 */
public interface IHaveList {
    public List<Role> getList();
    public void addRole(IHaveName element);
    public void deleteRole(IHaveName element);
}
