package com.nimacode.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /user
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    // delete /user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> creatUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // location - /users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") // add this to url
                .buildAndExpand(savedUser.getId()) // get user id
                .toUri();

        // response to return back
        // for create, response status is 201
        // then show the location header of post to url of the user -> for consumer of
        // API
        return ResponseEntity.created(location).build();
    }
}
