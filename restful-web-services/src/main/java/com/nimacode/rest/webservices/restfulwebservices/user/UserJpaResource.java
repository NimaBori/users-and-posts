package com.nimacode.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nimacode.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.nimacode.rest.webservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJpaResource {

    private UserDaoService service;

    private UserRepository repository;

    private PostRepository postRepository;

    public UserJpaResource(UserDaoService service, UserRepository repository, PostRepository postRepository) {
        this.service = service;
        this.repository = repository;
        this.postRepository = postRepository;
    }

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        // return service.findAll();
        return repository.findAll();

    }

    // GET /user
    @GetMapping("/jpa/users/{id}")
    public Optional<User> retrieveUser(@PathVariable int id) { // public User retrieveUser(@PathVariable int id)
        // User user = service.findOne(id);
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) { // user == null
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    // delete /user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        // service.deleteById(id);
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) { // user == null
            throw new UserNotFoundException("id:" + id);
        }
        return user.get().getPosts();

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id,
            @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) { // user == null
            throw new UserNotFoundException("id:" + id);
        }
        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") // add this to url
                .buildAndExpand(savedPost.getId()) // get user id
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> creatUser(@Valid @RequestBody User user) {
        // User savedUser = service.save(user);
        User savedUser = repository.save(user);

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
