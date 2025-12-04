package edu.team.carshopbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.repository.JwtTokenRepository;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerPatchTest {

    @MockitoBean
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private Profile testProfile;

    @WithUserDetails(
            value = "test@example.com",
            userDetailsServiceBeanName = "userService",
            setupBefore = TestExecutionEvent.TEST_EXECUTION
    )

    @BeforeEach
    void setup() {
        profileRepository.deleteAll();
        userRepository.deleteAll();

        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser.setEnabled(true);
        userRepository.save(testUser);

        testProfile = new Profile();
        testProfile.setName("Old Name");
        testProfile.setUser(testUser);
        testProfile.setPhoneNumber("123456789");
        testProfile.setRating(0.0);
        profileRepository.save(testProfile);
    }

    @Test
    @WithUserDetails(
            value = "test@example.com",
            userDetailsServiceBeanName = "userService",
            setupBefore = TestExecutionEvent.TEST_EXECUTION
    )
    void patchProfile_updatesFieldsPartially() throws Exception {
        ProfileDTO dto = new ProfileDTO();
        dto.setName("New Name");
        dto.setPhoneNumber("148987654321");

        mockMvc.perform(patch("/api/v1/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"))
                .andExpect(jsonPath("$.phoneNumber").value("148987654321"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}
