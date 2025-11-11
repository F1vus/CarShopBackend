package edu.team.carshopbackend.repository;

import edu.team.carshopbackend.entity.Petrol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetrolRepository extends JpaRepository<Petrol, Long> {
}
