package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.CotizadorDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestExeption;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    public double cotizar(CotizadorDTO payload) {

        double volumen = payload.getLargoCm()
                * payload.getAnchoCm()
                * payload.getAltoCm();

        if (payload.getPesoKg() > 50) {
            throw new BadRequestExeption("El paquete no puede pesar mas de 50 kg");
        }

        if (payload.getLargoCm() > 150
                || payload.getAnchoCm() > 150
                || payload.getAltoCm() > 150) {

            throw new BadRequestExeption(
                    "Ninguna dimension puede ser mayor a 150 cm"
            );
        }

        if (volumen > 1000000) {
            throw new BadRequestExeption(
                    "El volumen no puede ser mayor a 1,000,000 cm3"
            );
        }

        double costo = 80;

        costo = costo + (payload.getPesoKg() * 12);

        if (volumen > 50000) {
            costo = costo + 100;
        }

        if (payload.getTipoEnvio().equals("EXPRESS")) {
            costo = costo * 1.40;
        }

        if (payload.getTipoEnvio().equals("MISMO_DIA")) {
            costo = costo * 1.70;
        }

        if (payload.getValorDeclarado() > 10000) {
            costo = costo + (payload.getValorDeclarado() * 0.02);
        }

        return costo;
    }
}

