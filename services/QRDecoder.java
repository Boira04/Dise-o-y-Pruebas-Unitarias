package data.services;

import data.data.VehicleIDInterface;

import java.awt.image.BufferedImage;

public interface QRDecoder { // Decodes QR codes from an image
    VehicleIDInterface getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}
