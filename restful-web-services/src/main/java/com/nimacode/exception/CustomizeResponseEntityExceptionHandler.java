package com.nimacode.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nimacode.rest.webservices.restfulwebservices.user.UserNotFoundException;

// make this applicable to all controllers.
@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // get this signature from ResponseEntityExceptionHandler class itself.
    @ExceptionHandler(Exception.class) // we want to handle all exceptions.
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) // we want to handle all exceptions.
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
            throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
