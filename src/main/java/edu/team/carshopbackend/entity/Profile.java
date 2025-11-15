package edu.team.carshopbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_profiles")
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true, name = "user_id", nullable = false)
    private User user;

    @Column(name = "rating")
    private double rating = 0.0;

    @Column(name = "rating_count")
    private int ratingCount = 0;
}
