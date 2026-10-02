package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestExeption;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public static double cotizar(RequestHospedajeDTO payload) {

        double costoNoche = 0;
        int capacidad = 0;

        switch (payload.getTipoHabitacion()) {

            case "INDIVIDUAL":
                costoNoche = 700;
                capacidad = 1;
                break;

            case "DOBLE ":
                costoNoche = 1100;
                capacidad = 2;
                break;

            case "SUITE":
                costoNoche = 1800;
                capacidad = 4;
                break;

            default:
                throw new BadRequestExeption(
                        "El tipo de habitacion no es valido");
        }


        if (payload.getNumeroNoches() > 30) {
            throw new BadRequestExeption(
                    "La reservacion no puede superar 30 noches");
        }

        if (payload.getNumeroHuespedes() > capacidad) {
            throw new BadRequestExeption(
                    "El numero de huespedes supera la capacidad de la habitacion");
        }

        double costoHospedaje =
                costoNoche * payload.getNumeroNoches();

        double descuentoTemporada = 0;
        double cargoTemporada = 0;

        if (payload.getTemporada().equals("BAJA")) {

            descuentoTemporada =
                    costoHospedaje * 0.10;

        } else if (payload.getTemporada().equals("REGULAR")) {

            cargoTemporada = 0;

        } else if (payload.getTemporada().equals("ALTA")) {

            cargoTemporada =
                    costoHospedaje * 0.25;

        } else {

            throw new BadRequestExeption(
                    "La temporada no es valida");
        }

        double desayuno = 0;

        if (payload.isIncluyeDesayuno()) {

            desayuno =
                    payload.getNumeroHuespedes()
                            * payload.getNumeroNoches()
                            * 150;
        }

        double estacionamiento = 0;

        if (payload.isIncluyeEstacionamiento()) {

            estacionamiento =
                    payload.getNumeroNoches() * 100;
        }

        double descuentoLargaEstancia = 0;

        if (payload.getNumeroNoches() >= 7) {

            descuentoLargaEstancia =
                    costoHospedaje * 0.08;
        }

        // Subtotal
        double subtotal =
                costoHospedaje
                        - descuentoTemporada
                        + cargoTemporada
                        + desayuno
                        + estacionamiento
                        - descuentoLargaEstancia;

        double impuesto =
                subtotal * 0.04;

        double total =
                subtotal + impuesto;

        return total;
    }
}