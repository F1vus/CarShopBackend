package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.repository.CarRepository;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ProfileService profileService;

    private Profile profile;
    private Car car;

    @BeforeEach
    void setup() {
        profile = new Profile();
        profile.setId(1L);

        car = new Car();
        car.setId(1L);
        car.setName("Audi");
    }

    @Test
    void testAddLikedCar() throws NotFoundException {
        when(profileRepository.findById(1L)).thenReturn(java.util.Optional.of(profile));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        profileService.addLikedCar(1L, 1L);

        assertTrue(profile.getLikedCars().contains(car));
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    void testRemoveLikedCar() throws NotFoundException {
        profile.getLikedCars().add(car);
        when(profileRepository.findById(1L)).thenReturn(java.util.Optional.of(profile));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        profileService.removeLikedCar(1L, 1L);

        assertFalse(profile.getLikedCars().contains(car));
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    void testAddLikedCarNotFound() {
        when(profileRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(NotFoundException.class, () -> profileService.addLikedCar(1L, 1L));
    }

    @Test
    void testRemoveLikedCarNotLiked() throws NotFoundException {
        when(profileRepository.findById(1L)).thenReturn(java.util.Optional.of(profile));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));

        assertFalse(profile.getLikedCars().contains(car));

        profileService.removeLikedCar(1L, 1L);

        assertFalse(profile.getLikedCars().contains(car));
    }
}
