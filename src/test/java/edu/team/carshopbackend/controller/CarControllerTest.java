package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @MockitoBean
    private JwtCore jwtCore;

//    @Test
//    void shouldReturnListOfCars() throws Exception {
//        Car car = new Car();
//        car.setId(1L);
//        car.setName("TestCar");
//
//        when(carService.getAllProducts()).thenReturn(List.of(car));
//
//        mockMvc.perform(get("/api/v1/cars")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("TestCar"));
//    }
//
//    @Test
//    void shouldReturnCarById() throws Exception {
//        Car car = new Car();
//        car.setId(10L);
//        car.setName("BMW");
//
//        when(carService.getProductById(10L)).thenReturn(Optional.of(car));
//
//        mockMvc.perform(get("/api/v1/cars/10")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("BMW"));
//    }

    @Test
    void shouldReturnErrorWhenCarNotFound() throws Exception {
        Mockito.when(carService.getProductById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/cars/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
