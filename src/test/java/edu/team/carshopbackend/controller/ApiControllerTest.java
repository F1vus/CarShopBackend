package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.repository.JwtTokenRepository;
import edu.team.carshopbackend.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc(addFilters = false)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtCore jwtCore;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtTokenRepository jwtTokenRepository;

    @Test
    void shouldReturnPong() throws Exception {
        mockMvc.perform(get("/api/v1/ping")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("pong!"));
    }

    @Test
    void shouldReturnIntegerAfterPostRequest() throws Exception {
        mockMvc.perform(post("/api/v1/test_post/1")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
