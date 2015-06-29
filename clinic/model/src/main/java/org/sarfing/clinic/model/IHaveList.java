package org.sarfing.clinic.model;

import java.util.List;

/**
 * Created by Yakov on 29.06.2015.
 */
public interface IHaveList {
    public List getList();
    public void addRole(Role element);
    public void deleteRole(Role element);
}
