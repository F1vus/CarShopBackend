package edu.team.carshopbackend.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String mark;
    private Long price;
    private String description;
//    private String color
    private Long mileage;
    private String auto_status;
//    private String petrol_type;
    private int engine_capacity;
    private int power;



    // mark
    @OneToOne
    @JoinColumn(name = "mark_id", referencedColumnName = "markID")
    private AutoProducent mark;

    //color
    @OneToOne
    @JoinColumn(name = "color_id", referencedColumnName = "colorID")
    private Color color;

    //petrol_type
    @OneToOne
    @JoinColumn(name = "petrol_type_id", referencedColumnName = "petrolID")
    private Petrol petrolType;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMileage() {
        return mileage;
    }
    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }
    public String getAuto_status() {
        return auto_status;
    }
    public void setAuto_status(String auto_status) {
        this.auto_status = auto_status;
    }
    public int getEngine_capacity() {
        return engine_capacity;
    }
    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public AutoProducent getMark() {
        return mark;
    }

    public void setMark(AutoProducent mark) {
        this.mark = mark;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Petrol getPetrolType() {
        return petrolType;
    }
    public void setPetrolType(Petrol petrolType) {
        this.petrolType = petrolType;
    }










}
