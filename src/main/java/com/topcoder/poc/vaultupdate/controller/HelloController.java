package com.topcoder.poc.vaultupdate.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * This api is protected with http basic authentication
     *
     * @return The Hello World String
     */
    @GetMapping(value = "/hello",produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello(){
        return "Hello World";
    }
}
