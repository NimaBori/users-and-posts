package com.nimacode.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "user_details") // user is pre-reserved keyboard by h2
public class User {

    // defdault ctor for jpa
    protected User() {

    }

    @Id
    @GeneratedValue
    private int id;

    // @JsonProperty("user_name") // to customize json property in response
    @Size(min = 2, message = "Name should have at least two characters.") // added by validation dependency - min
                                                                          // length.
    private String name;

    @Past // date should be always in past.
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user") // use has one to many relationshipts with Post
    @JsonIgnore // we don't want Post to be part of jason responses
    private List<Post> posts;

    public User(int id, String name, LocalDate birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    } // ctor

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", name='" + name + "'" +
                ", birthDate='" + birthDate + "'" +
                "}";
    }

}
