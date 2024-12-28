package data.services;

import data.data.VehicleIDInterface;
import data.data.VehicleIDdoble;

import java.awt.image.BufferedImage;

public class QRDecoderDoble implements QRDecoder {

    private VehicleIDInterface vehicleToReturn;

    public QRDecoderDoble() {
        this.vehicleToReturn = new VehicleIDdoble();
    }


    public void setVehicleToReturn(VehicleIDInterface vehicle) {
        this.vehicleToReturn = vehicle;
    }


    @Override
    public VehicleIDInterface getVehicleID(BufferedImage QRImg) throws CorruptedImgException {
        return vehicleToReturn;
    }
}