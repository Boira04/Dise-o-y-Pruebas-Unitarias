package data.services.smartfeatures;


import data.data.VehicleIDInterface;
import data.services.CorruptedImgException;

import java.awt.image.BufferedImage;

public class QRDecoderImpl implements QRDecoder {

    private final VehicleIDInterface vehicle;

    // Constructor que recibe el vehículo que debe devolver
    public QRDecoderImpl(VehicleIDInterface vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        this.vehicle = vehicle;
    }

    @Override
    public VehicleIDInterface getVehicleID(BufferedImage QRImg) throws CorruptedImgException {
        if (QRImg == null) {
            throw new CorruptedImgException("The QR image is null or corrupted.");
        }

        // Devuelve el vehículo configurado en la inicialización
        return vehicle;
    }
}

