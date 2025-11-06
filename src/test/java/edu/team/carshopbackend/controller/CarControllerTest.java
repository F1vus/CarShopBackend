package edu.team.carshopbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @MockitoBean
    private JwtCore jwtCore;

    @MockitoBean
    private CarMapper carMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateCarSuccessfully() throws Exception {
        CarDTO requestDto = new CarDTO();
        requestDto.setName("Audi");

        Car entity = new Car();
        entity.setName("Audi");

        Car savedEntity = new Car();
        savedEntity.setId(1L);
        savedEntity.setName("Audi");

        CarDTO responseDto = new CarDTO();
        responseDto.setId(1L);
        responseDto.setName("Audi");

        Mockito.when(carMapper.mapFrom(requestDto)).thenReturn(entity);
        Mockito.when(carService.createProduct(entity)).thenReturn(savedEntity);
        Mockito.when(carMapper.mapTo(savedEntity)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Audi")));
    }

//    @Test
//    void shouldUpdateCarSuccessfully() throws Exception {
//        Long id = 1L;
//
//        CarDTO requestDto = new CarDTO();
//        requestDto.setName("Updated Car");
//
//        Car entity = new Car();
//        entity.setId(id);
//        entity.setName("Updated Car");
//
//        Car updatedEntity = new Car();
//        updatedEntity.setId(id);
//        updatedEntity.setName("Updated Car");
//
//        CarDTO responseDto = new CarDTO();
//        responseDto.setId(id);
//        responseDto.setName("Updated Car");
//
//        Mockito.when(carService.isExists(id)).thenReturn(true);
//        Mockito.when(carMapper.mapFrom(requestDto)).thenReturn(entity);
//        Mockito.when(carService.carUpdate(id, entity)).thenReturn(updatedEntity);
//        Mockito.when(carMapper.mapTo(updatedEntity)).thenReturn(responseDto);
//
//        mockMvc.perform(patch("/api/v1/cars/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Updated Car")));
//    }

    @Test
    void shouldDeleteCarSuccessfully() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(carService).deleteCarById(id);

        mockMvc.perform(delete("/api/v1/cars/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(carService, Mockito.times(1)).deleteCarById(id);
    }
}
