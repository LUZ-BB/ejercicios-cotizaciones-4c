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
public class CotizadorDTO {

    @NotBlank(message = "El codigo postal es obligatorio")
    private String codigoPostal;

    @Positive(message = "El peso debe ser mayor a 0")
    private double pesoKg;

    @Positive(message = "El largo debe ser mayor a 0")
    private double largoCm;

    @Positive(message = "El ancho debe ser mayor a 0")
    private double anchoCm;

    @Positive(message = "El alto debe ser mayor a 0")
    private double altoCm;

    @NotBlank(message = "El tipo de envio es obligatorio")
    private String tipoEnvio;

    @Positive(message = "El valor declarado debe ser mayor a 0")
    private double valorDeclarado;
}

