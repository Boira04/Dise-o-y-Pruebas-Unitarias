package data.services;

import data.data.VheicleIDInterface;

import java.awt.image.BufferedImage;

public interface QRDecoder { // Decodes QR codes from an image
    VheicleIDInterface getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}
