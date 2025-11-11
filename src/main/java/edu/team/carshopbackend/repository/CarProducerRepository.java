package edu.team.carshopbackend.repository;

import edu.team.carshopbackend.entity.CarProducent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarProducerRepository extends JpaRepository<Long, CarProducent> {
}
