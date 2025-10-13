package edu.team.carshopbackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Petrol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petrolID;
    private String name;
}
