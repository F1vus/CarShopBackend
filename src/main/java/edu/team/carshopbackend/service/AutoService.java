package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.Auto;
import edu.team.carshopbackend.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    private final AutoRepository autoRepository;

    @Autowired
    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public Auto createProduct(Auto auto) {
        return autoRepository.save(auto);
    }

    public List<Auto> getAllProducts() {
        return autoRepository.findAll();
    }

    public Optional<Auto> getProductById(Long id) {
        return autoRepository.findById(id);
    }


}
