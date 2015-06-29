package org.sarfing.clinic.model;

import java.util.ArrayList;

/**
 * Created by Yakov on 30.05.2015.
 */
public class ElementCreator {
    public static IHaveName createElement(ElementType type, String name) {
        switch (type) {
            case Role:
                return new Role(name);
            case Person:
                return new Person(name, new ArrayList<Role>());
            case Template:
                return new Template(name);
            default:
                return null;
        }
    }
}
