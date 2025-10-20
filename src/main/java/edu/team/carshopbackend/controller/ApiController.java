package edu.team.carshopbackend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class ApiController {

    @GetMapping("hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("test_post/{id}")
    public Integer post(@PathVariable("id") Integer id) {
        return id;
    }

}