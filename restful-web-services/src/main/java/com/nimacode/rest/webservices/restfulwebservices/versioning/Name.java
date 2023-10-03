package com.nimacode.rest.webservices.restfulwebservices.versioning;

public class Name {
    private String firstName;
    private String secondName;

    public Name(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    @Override
    public String toString() {
        return "{" +
                " firstName='" + firstName + "'" +
                ", secondName='" + secondName + "'" +
                "}";
    }

}
