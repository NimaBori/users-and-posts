package com.nimacode.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimacode.rest.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> { // JpaRepo manages User entity and the type of Id
                                                                       // field is int.

}
