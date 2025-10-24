package edu.team.carshopbackend.entity;

import edu.team.carshopbackend.entity.enums.AutoState;
import edu.team.carshopbackend.entity.enums.converter.AutoStateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private CarProducent mark;

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
    @Convert(converter = AutoStateConverter.class)
    private AutoState car_status;

    @ManyToOne
    @JoinColumn(name = "petrol_type_id", referencedColumnName = "id")
    private Petrol petrolType;

    @Column(name = "engine_capacity")
    private int engine_capacity;

    @Column(name = "power")
    private int power;

    @Column(name = "year")
    private int year;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

}
