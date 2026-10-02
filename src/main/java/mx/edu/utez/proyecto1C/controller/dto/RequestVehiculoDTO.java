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
public class RequestVehiculoDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @Min(value = 18, message = "El conductor debe tener al menos 18 años")
    private int edadConductor;

    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    private String tipoVehiculo;

    @Min(value = 1, message = "Los dias de renta deben ser mayores a 0")
    private int diasRenta;

    @Min(value = 0, message = "Los kilometros no pueden ser negativos")
    private int kilometrosEstimados;

    private boolean seguroCompleto;
}