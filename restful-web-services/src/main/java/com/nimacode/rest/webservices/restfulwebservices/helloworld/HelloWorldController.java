package com.nimacode.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// REST API - we can expose a REST API from here
@RestController
public class HelloWorldController {

    // Internationalization
    // MessageSource is a strategy interface for resolving messages,
    // which supports for parameterization and internationalization of such
    // messages.
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // to give a specific url to specific method - GET is method and url is path.
    @GetMapping(path = "/hello-world")
    public String hellowWorld() {
        return "Hello Woddrld";
    }

    // return JSON - bean
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean hellowWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Parameters - to ge access to path parmaters we use @PathVariable from
    // String framework
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean hellowWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World " + name);
    }

    // Internationalization implemnetation
    @GetMapping(path = "/hello-world-international")
    // get location from header
    public String hellowWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);

    }
}
