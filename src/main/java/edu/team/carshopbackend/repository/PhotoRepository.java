package edu.team.carshopbackend.repository;

import edu.team.carshopbackend.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
