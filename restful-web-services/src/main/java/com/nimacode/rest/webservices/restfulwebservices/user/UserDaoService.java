package com.nimacode.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List

    private static List<User> users = new ArrayList<>();

    // The static modifier means that this field is associated with the class
    // itself, rather than with instances of the class.
    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Nima", LocalDate.now().minusYears(38)));
        users.add(new User(3, "Bahareh", LocalDate.now().minusYears(35)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(id);
            }
        }

    }

}
