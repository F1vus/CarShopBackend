package edu.team.carshopbackend.entity;

import edu.team.carshopbackend.entity.enums.CarState;
import edu.team.carshopbackend.entity.enums.converter.CarStateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private CarProducent producent;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;

    @Column(name = "mileage")
    private Long mileage;

    @Column(name = "car_status")
    @Convert(converter = CarStateConverter.class)
    private CarState carState;

    @ManyToOne
    @JoinColumn(name = "petrol_type_id", referencedColumnName = "id")
    private Petrol petrolType;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;

    @Column(name = "power")
    private Integer power;

    @Column(name = "manufacture_year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "users_profiles_id", referencedColumnName = "id")
    private Profile owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;
}
