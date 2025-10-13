package edu.team.carshopbackend.entity;


import jakarta.persistence.*;

@Entity
public class AutoProducent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markID;
    private String name;


//    @OneToOne
//    @JoinColumn(name = "autoMark", nullable = false)
//    private Auto auto;



}
