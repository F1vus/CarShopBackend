package edu.team.carshopbackend.entity;

import edu.team.carshopbackend.entity.enums.converter.AutoStateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "autos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private AutoProducent mark;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;

    @Column(name = "mileage")
    private Long mileage;

    @Column(name = "auto_status")
    private String auto_status;

    @OneToOne
    @JoinColumn(name = "petrol_type_id", referencedColumnName = "id")
    private Petrol petrolType;

    @Column(name = "engine_capacity")
    private int engine_capacity;

    @Column(name = "power")
    private int power;

    @Column(name = "state")
    @Convert(converter = AutoStateConverter.class)
    private String state;

}
