package model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Reprezentuje samochód w systemie")
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Car {

    @Schema(description = "Unikalne ID samochodu", example = "1")
    private Long id;

    @Schema(description = "Marka samochodu", example = "Toyota")
    private String brand;

    @Schema(description = "Model samochodu", example = "Corolla")
    private String model;

    @Schema(description = "Rok produkcji samochodu", example = "2023")
    private int year;

}
