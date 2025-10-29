package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.repository.CarRepository;
import edu.team.carshopbackend.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        car1 = new Car();
        car1.setId(1L);
        car1.setName("Car 1");

        car2 = new Car();
        car2.setId(2L);
        car2.setName("Car 2");
    }

    @Test
    void testCreateProduct() {
        when(carRepository.save(car1)).thenReturn(car1);

        Car savedCar = carService.createProduct(car1);

        assertNotNull(savedCar);
        assertEquals("Car 1", savedCar.getName());
        verify(carRepository, times(1)).save(car1);
    }

    @Test
    void testGetAllProducts() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<Car> cars = carService.getAllProducts();

        assertEquals(2, cars.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testGetProductByIdFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        Optional<Car> result = carService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Car 1", result.get().getName());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        when(carRepository.findById(3L)).thenReturn(Optional.empty());

        Optional<Car> result = carService.getProductById(3L);

        assertFalse(result.isPresent());
        verify(carRepository, times(1)).findById(3L);
    }
}
