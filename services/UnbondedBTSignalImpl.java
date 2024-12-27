package data.services;


import data.data.StationIDInterface;

import java.net.ConnectException;
import java.util.List;

public class UnbondedBTSignalImpl implements UnbondedBTSignal {

    private final StationIDInterface station;
    private final List<String> broadcastHistory;

    public UnbondedBTSignalImpl(StationIDInterface station, List<String> broadcastHistory) {
        if (station == null) {
            throw new IllegalArgumentException("Station cannot be null");
        }
        if (broadcastHistory == null) {
            throw new IllegalArgumentException("Broadcast history cannot be null");
        }
        this.station = station;
        this.broadcastHistory = broadcastHistory;
    }

    @Override
    public void BTbroadcast() throws ConnectException {
        while (true) {
            String message = "Broadcasting station ID: " + station.getID();
            broadcastHistory.add(message);
            System.out.println(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
