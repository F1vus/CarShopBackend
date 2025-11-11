package edu.team.carshopbackend.repository;

import edu.team.carshopbackend.entity.Petrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetrolRepository extends JpaRepository<Petrol, Long> {
}
