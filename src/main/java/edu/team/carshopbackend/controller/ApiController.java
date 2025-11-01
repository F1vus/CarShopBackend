package edu.team.carshopbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/")
public class ApiController {

    @GetMapping("v1/ping")
    public String pingPong() {
        return "pong";
    }

    @GetMapping("secured/ping")
    public String securedPingPong(){
        return "secured_pong";
    }

    @PostMapping("v1/test_post/{id}")
    public Integer post(@PathVariable("id") Integer id) {
        return id;
    }

}