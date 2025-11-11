package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.repository.CarProducerRepository;
import edu.team.carshopbackend.repository.ColorRepository;
import edu.team.carshopbackend.repository.PetrolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lookups")
@RequiredArgsConstructor
public class LookupController {

    private final CarProducerRepository carProducerRepository;
    private final ColorRepository colorRepository;
    private final PetrolRepository petrolRepository;

    @GetMapping("/metadata")
    public Map<String, Object> getMetadata() {
        Map<String, Object> data = new HashMap<>();
        data.put("producers", carProducerRepository.findAll());
        data.put("colors", colorRepository.findAll());
        data.put("petrols", petrolRepository.findAll());
        return data;
    }

}
