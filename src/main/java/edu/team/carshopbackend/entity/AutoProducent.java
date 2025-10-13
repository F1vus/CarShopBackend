package edu.team.carshopbackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="auto_producent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutoProducent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long markID;

    @Column(name = "auto_produces")
    private String name;

}
