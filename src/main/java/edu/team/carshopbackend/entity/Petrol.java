package edu.team.carshopbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "petrols")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Petrol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long petrolID;

    @Column(name = "name")
    private String name;
}
