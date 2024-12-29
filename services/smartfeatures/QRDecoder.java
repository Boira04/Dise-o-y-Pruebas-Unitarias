package data.services.smartfeatures;

import data.data.VehicleIDInterface;
import data.services.CorruptedImgException;

import java.awt.image.BufferedImage;

public interface QRDecoder { // Decodes QR codes from an image
    VehicleIDInterface getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}
