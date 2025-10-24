package edu.team.carshopbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@Tag(name = "API Test", description = "Przykładowe endpointy testowe")
public class ApiController {

    @GetMapping("hello_world")
    @Operation(summary = "Hello World", description = "Zwraca prosty komunikat Hello World")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("test_post/{id}")
    @Operation(summary = "Test POST", description = "Zwraca przekazane ID w ścieżce")
    public Integer post(@PathVariable("id") Integer id) {
        return id;
    }

}
