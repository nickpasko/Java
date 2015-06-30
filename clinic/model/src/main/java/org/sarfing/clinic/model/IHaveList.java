package org.sarfing.clinic.model;

import java.util.List;

/**
 * Created by Yakov on 29.06.2015.
 */
public interface IHaveList {
    public List getList();
    public void addRole(IHaveList element);
    public void deleteRole(IHaveList element);
}
