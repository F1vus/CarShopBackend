package model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Reprezentuje samochód w systemie")
@Getter
@Setter
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
