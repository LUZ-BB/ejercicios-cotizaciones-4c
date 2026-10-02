package mx.edu.utez.proyecto1C.controller.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del huesped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitacin es obligatorio")
    private String tipoHabitacion;

    @Min(value = 1, message = "El numero de noches debe ser mayor a 0")
    private int numeroNoches;

    @Min(value = 1, message = "El numero de huespedes debe ser mayor a 0")
    private int numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    private boolean incluyeDesayuno;

    private boolean incluyeEstacionamiento;
}