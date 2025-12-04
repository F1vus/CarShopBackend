package edu.team.carshopbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Photo;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import edu.team.carshopbackend.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtCore jwtCore;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private CarService carService;

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

        when(carMapper.mapFrom(requestDto)).thenReturn(entity);
        when(carService.createProduct(entity)).thenReturn(savedEntity);
        when(carMapper.mapTo(savedEntity)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Audi")));
    }

    @Test
    void shouldUpdateCarSuccessfully() throws Exception {
        Long id = 1L;

        CarDTO requestDto = new CarDTO();
        requestDto.setName("Updated Car");

        Car entity = new Car();
        entity.setId(id);
        entity.setName("Updated Car");

        Car updatedEntity = new Car();
        updatedEntity.setId(id);
        updatedEntity.setName("Updated Car");

        CarDTO responseDto = new CarDTO();
        responseDto.setId(id);
        responseDto.setName("Updated Car");

        when(carService.isExists(id)).thenReturn(true);
        when(carMapper.mapFrom(Mockito.any(CarDTO.class))).thenReturn(entity);
        when(carService.carUpdate(Mockito.eq(id), Mockito.any(Car.class))).thenReturn(updatedEntity);
        when(carMapper.mapTo(Mockito.any(Car.class))).thenReturn(responseDto);

        mockMvc.perform(patch("/api/v1/cars/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Car")));
    }

    @Test
    void shouldDeleteCarSuccessfully() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(carService).deleteCarById(id);

        mockMvc.perform(delete("/api/v1/cars/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(carService, Mockito.times(1)).deleteCarById(id);
    }

    @Test
    void shouldSuggestCarSuccessfully() throws Exception {
        Car car1 = new Car();
        car1.setName("Toyota");
        car1.setPhotos(List.of(new Photo()));

        Car car2 = new Car();
        car2.setName("Toro");
        car2.setPhotos(List.of(new Photo()));

        when(carService.suggestCar("to")).thenReturn(List.of(car1,car2));
        mockMvc.perform(get("/api/v1/cars/suggestions")
                        .param("query","to")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].name").value("Toyota"))
                .andExpect(jsonPath("$[1].name").value("Toro"));

        verify(carService, Mockito.times(1)).suggestCar("to");
    }



}
