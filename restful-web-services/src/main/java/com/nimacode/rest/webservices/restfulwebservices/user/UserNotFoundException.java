package com.nimacode.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// to make this class result in a 404 (NOT FOUND) repsonse status
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    } // cstor > accepts a message and passes it to the superclass which is
      // RuntimeExeption
}
