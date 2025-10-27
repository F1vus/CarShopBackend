package model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reprezentuje samochód w systemie")
public class Car {

    @Schema(description = "Unikalne ID samochodu", example = "1")
    private Long id;

    @Schema(description = "Marka samochodu", example = "Toyota")
    private String brand;

    @Schema(description = "Model samochodu", example = "Corolla")
    private String model;

    @Schema(description = "Rok produkcji samochodu", example = "2023")
    private int year;

    public Car(Long id, String brand, String model, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
