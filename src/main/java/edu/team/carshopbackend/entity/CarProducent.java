package edu.team.carshopbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="car_producent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarProducent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long markID;

    @Column(name = "name")
    private String name;

}


