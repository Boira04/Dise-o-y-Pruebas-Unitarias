package data.services;

import data.data.VehicleIDdoble;
import data.data.VheicleIDInterface;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class QRDecoderTest {

    @Test
    void testGetVehicleIDSuccess() throws CorruptedImgException {
        VheicleIDInterface vehicle = new VehicleIDdoble();
        QRDecoderImpl qrDecoder = new QRDecoderImpl(vehicle);

        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        VheicleIDInterface result = qrDecoder.getVehicleID(mockImage);

        assertNotNull(result, "The returned vehicle should not be null.");
        assertEquals(vehicle, result, "The returned vehicle should match the initialized vehicle.");
    }

    @Test
    void testGetVehicleIDFailureNullImage() {
        VheicleIDInterface vehicle = new VehicleIDdoble();
        QRDecoderImpl qrDecoder = new QRDecoderImpl(vehicle);

        Exception exception = assertThrows(CorruptedImgException.class, () -> qrDecoder.getVehicleID(null),
                "Should throw CorruptedImgException when the image is null.");
        assertEquals("The QR image is null or corrupted.", exception.getMessage());
    }

    @Test
    void testInitializationWithNullVehicle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new QRDecoderImpl(null),
                "Should throw IllegalArgumentException when the vehicle is null.");
        assertEquals("Vehicle cannot be null", exception.getMessage());
    }
}
