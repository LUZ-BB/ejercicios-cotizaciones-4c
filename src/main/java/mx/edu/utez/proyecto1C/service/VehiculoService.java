package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RequestVehiculoDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestExeption;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    public static double cotizar(RequestVehiculoDTO payload) {

        double costoDiario = 0;

        switch (payload.getTipoVehiculo()) {

            case "COMPACTO":
                costoDiario = 550;
                break;

            case "SEDAN":
                costoDiario = 700;
                break;

            case "SUV":
                costoDiario = 950;
                break;

            case "CAMIONETA":
                costoDiario = 1200;
                break;

            default:
                throw new BadRequestExeption("El tipo de vehículo no es valido");
        }

        if (payload.getEdadConductor() < 18) {
            throw new BadRequestExeption(
                    "El conductor debe tener al menos 18 años");
        }

        if (payload.getDiasRenta() > 30) {
            throw new BadRequestExeption(
                    "La renta no puede superar 30 dias");
        }

        if (payload.getKilometrosEstimados() > 5000) {
            throw new BadRequestExeption(
                    "Los kilometros estimados no pueden superar 5000");
        }

        if (payload.getTipoVehiculo().equals("CAMIONETA")
                && payload.getEdadConductor() < 25) {

            throw new BadRequestExeption(
                    "No se puede rentar una camioneta a un conductor menor de 25 años");
        }

        double costoRenta =
                costoDiario * payload.getDiasRenta();

        int kilometrosIncluidos =
                payload.getDiasRenta() * 100;

        double costoKilometros = 0;

        if (payload.getKilometrosEstimados() > kilometrosIncluidos) {

            int kilometrosExtra =
                    payload.getKilometrosEstimados() - kilometrosIncluidos;

            costoKilometros = kilometrosExtra * 4;
        }

        double cargoEdad = 0;

        if (payload.getEdadConductor() >= 18
                && payload.getEdadConductor() <= 24) {

            cargoEdad =
                    (costoRenta + costoKilometros) * 0.15;
        }

        double seguro = 0;

        if (payload.isSeguroCompleto()) {

            seguro =
                    payload.getDiasRenta() * 180;
        }

        double descuento = 0;

        if (payload.getDiasRenta() >= 7) {

            descuento = costoRenta * 0.10;
        }

        double total =
                costoRenta
                        + costoKilometros
                        + cargoEdad
                        + seguro
                        - descuento;

        return total;
    }
}