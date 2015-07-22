package org.sarfing.clinic.model;

/**
 * Created by Yakov on 22.07.2015.
 */
public class User implements IHaveName {
    public String login;
    public String password;
    public Person person;

    public User(String login, String pass, Person person) {
        this.login = login;
        this.password = pass;
        this.person = person;
    }

    public boolean passwordOk(String inputPass) {
        return inputPass.equals(password);
    }

    @Override
    public String getName() {
        return login;
    }

    @Override
    public ElementType getType() {
        return ElementType.User;
    }

    @Override
    public void setName(String newName) {
        login = newName;
    }
}
