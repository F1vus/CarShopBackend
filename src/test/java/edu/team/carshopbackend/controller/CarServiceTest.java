package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.repository.CarRepository;
import edu.team.carshopbackend.repository.JwtTokenRepository;
import edu.team.carshopbackend.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @MockitoBean
    private JwtTokenRepository jwtTokenRepository;

    @InjectMocks
    private CarService carService;

    private Car existingCar;
    private Car updateCar;

    @BeforeEach
    void setUp() {
        existingCar = new Car();
        existingCar.setId(1L);
        existingCar.setName("Old Car");
        existingCar.setPrice((long) 10000.0);

        updateCar = new Car();
        updateCar.setName("Updated Car");
        updateCar.setPrice((long) 15000.0);
    }

    @Test
    void testUpdateProductSuccessfully() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        when(carRepository.save(any(Car.class))).thenAnswer(inv -> inv.getArgument(0));

        Car updated = carService.carUpdate(1L, updateCar);

        assertNotNull(updated);
        assertEquals("Updated Car", updated.getName());
        assertEquals((long) 15000.0, updated.getPrice());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void testUpdateProductThrowsWhenNotFound() {
        when(carRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> carService.carUpdate(999L, updateCar));

        assertEquals("Car does not exist with id 999", ex.getMessage());
        verify(carRepository, times(1)).findById(999L);
        verify(carRepository, never()).save(any());
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).deleteById(1L);

        carService.deleteCarById(1L);

        verify(carRepository, times(1)).deleteById(1L);
    }


    @Test
    void testSuggestion() {
        Car car1 = new Car();
        car1.setName("Tojota");

        Car car2 = new Car();
        car2.setName("Toro");

        when(carRepository.findByNameContainingIgnoreCase("to")).thenReturn(List.of(car1, car2));
        List<Car> result = carService.suggestCar("to");
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(carRepository, times(1)).findByNameContainingIgnoreCase("to");

    }

    @Test
    void testSuggestionEmptyList() {
        when(carRepository.findByNameContainingIgnoreCase("to")).thenReturn(Collections.emptyList());
        List<Car> result = carService.suggestCar("to");
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(carRepository, times(1)).findByNameContainingIgnoreCase("to");
    }

    @Test
    void testSuggestionWithSpecialCharacter() {
        Car car1 = new Car();
        car1.setName("@!Tojota");

        Car car2 = new Car();
        car2.setName("-L");

        Car car3 = new Car();
        car3.setName("#");

        Car car4 = new Car();
        car4.setName("$%Tes");

        Car car5 = new Car();
        car5.setName("^Au");

        Car car6 = new Car();
        car6.setName("^Au*&*");

        Car car7 = new Car();
        car7.setName("(BM)");

        String queries = "@#$%^&*()";

        when(carRepository.findByNameContainingIgnoreCase(queries))
                .thenReturn(List.of(car1, car2, car3, car4, car5, car6, car7));
        List<Car> result = carService.suggestCar(queries);
        assertNotNull(result);
        assertEquals(7, result.size());
        verify(carRepository, times(1)).findByNameContainingIgnoreCase(queries);
    }

    @Test
    void testSuggestionCarsIgnoringCase() {
        Car car1 = new Car();
        car1.setName("Tojota");

        Car car2 = new Car();
        car2.setName("TOJO");

        Car car3 = new Car();
        car3.setName("TOro");

        Car car4 = new Car();
        car4.setName("toDO");

        when(carRepository.findByNameContainingIgnoreCase("to"))
                .thenReturn(List.of(car1, car2, car3, car4));
        List<Car> result = carService.suggestCar("to");

        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.contains(car1));
        assertTrue(result.contains(car2));
        assertTrue(result.contains(car3));
        assertTrue(result.contains(car4));

        verify(carRepository, times(1)).findByNameContainingIgnoreCase("to");
    }
}
