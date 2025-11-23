package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/")
public class ApiController {

    @GetMapping("v1/ping")
    @Operation(summary = "Simple ping endpoint", description = "return  pong, to test API")
    public String pingPong() {
        return "pong!";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("secured/ping")
    @Operation(summary = "Secured ping endpoint", description = "return secured_pong-string, to test secured API access")
    public String securedPingPong(@AuthenticationPrincipal UserDetailsImpl principal){
        return "secured_pong, hi! " + principal.getUsername();
    }

    @PostMapping("v1/test_post/{id}")
    @Operation(summary = "Echo id", description = "accepts ID and return it in the response")
    public Integer post(@PathVariable("id") Integer id) {
        return id;
    }

}

