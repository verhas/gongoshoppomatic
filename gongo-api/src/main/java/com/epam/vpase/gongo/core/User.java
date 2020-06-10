package com.epam.vpase.gongo.core;

public class User implements HasId{
    public String login;
    public String name;
    public boolean verified;

    @Override
    public String getId() {
        return login;
    }
}
