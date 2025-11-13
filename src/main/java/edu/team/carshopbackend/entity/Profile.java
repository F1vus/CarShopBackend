package edu.team.carshopbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true, name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();
}
