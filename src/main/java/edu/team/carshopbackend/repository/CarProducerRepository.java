package edu.team.carshopbackend.repository;

import edu.team.carshopbackend.entity.CarProducent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarProducerRepository extends JpaRepository<CarProducent, Long> {
}
