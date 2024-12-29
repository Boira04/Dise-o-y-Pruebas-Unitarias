package data.services.smartfeatures;

import java.net.ConnectException;

public interface UnbondedBTSignal { // Broadcasts the station ID by BT
    void BTbroadcast () throws ConnectException;
}
