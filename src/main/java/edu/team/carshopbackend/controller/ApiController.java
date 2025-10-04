package edu.team.carshopbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class ApiController {

    @GetMapping("hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

}
