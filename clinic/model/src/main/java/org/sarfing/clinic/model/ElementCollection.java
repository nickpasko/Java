package org.sarfing.clinic.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;

/**
 * Created by Yakov on 27.06.2015.
 */
public class ElementCollection {
    private Map<String, IHaveName> map;
    public ElementType type;

    public ElementCollection(Map<String, IHaveName> map, ElementType type) {
        this.map = map;
        this.type = type;
    }

    public IHaveName get(String name) {
        return map.get(name);
    }

    public boolean containsKey(String inputString) {
        return map.containsKey(inputString);
    }

    public Collection<IHaveName> values() {
        return map.values();
    }

    public void put(String inputString, IHaveName newElement) {
        map.put(inputString, newElement);
    }

    public void remove(String name) {
        map.remove(name);
    }

    public String getName(int inputNumber) {
        Object[] elementsArr = map.keySet().toArray();
        return (String)elementsArr[inputNumber - 1];
    }

    public void renameElement(ElementCollection elements, String oldName, String newName) {
        IHaveName elem = elements.get(oldName);
        elem.setName(newName);
        elements.remove(oldName);
        elements.put(newName, elem);

    }
}
