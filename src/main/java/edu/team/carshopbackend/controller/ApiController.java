package edu.team.carshopbackend.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/")
public class ApiController {

    @GetMapping("ping")
    public String pingPong() {
        return "pong";
    }

    @PostMapping("test_post/{id}")
    public Integer post(@PathVariable("id") Integer id) {
        return id;
    }

}