package data.services.smartfeatures;

import data.data.StationIDdoble;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnbondedBTSignalTest {

    @Test
    void testBTbroadcast() throws InterruptedException {
        StationIDdoble station = new StationIDdoble();

        List<String> broadcastHistory = new ArrayList<>();

        UnbondedBTSignalImpl btSignal = new UnbondedBTSignalImpl(station, broadcastHistory);

        Thread broadcastThread = new Thread(() -> {
            try {
                btSignal.BTbroadcast();
            } catch (ConnectException e) {
                fail("ConnectException should not occur during normal execution");
            }
        });

        broadcastThread.start();

        Thread.sleep(3000);

        broadcastThread.interrupt();

        broadcastThread.join();
        assertFalse(broadcastThread.isAlive(), "The broadcast thread should be stopped.");

        assertFalse(broadcastHistory.isEmpty(), "Broadcast history should not be empty.");
        assertTrue(broadcastHistory.size() >= 2, "At least two messages should have been broadcasted.");
        assertEquals("Broadcasting station ID: 1", broadcastHistory.get(0), "The first broadcast message should be correct.");
    }

    @Test
    void testInitializationWithNullStation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnbondedBTSignalImpl(null, new ArrayList<>()),
                "Should throw IllegalArgumentException when the station is null.");
        assertEquals("Station cannot be null", exception.getMessage());
    }

    @Test
    void testInitializationWithNullBroadcastHistory() {
        StationIDdoble station = new StationIDdoble();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new UnbondedBTSignalImpl(station, null),
                "Should throw IllegalArgumentException when the broadcast history is null.");
        assertEquals("Broadcast history cannot be null", exception.getMessage());
    }
}

