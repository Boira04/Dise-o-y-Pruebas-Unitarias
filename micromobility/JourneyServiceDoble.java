package data.micromobility;

import data.data.GeographicPointInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JourneyServiceDoble implements JourneyServiceInterface {
    private int contInit;
    private int contFin;
    @Override
    public void JourneyService() {

    }

    @Override
    public LocalDateTime getInitDate() {
        return null;
    }

    @Override
    public float getDuration() {
        return 0;
    }

    @Override
    public float getDistance() {
        return 0;
    }

    @Override
    public float getAvgSpeed() {
        return 0;
    }

    @Override
    public GeographicPointInterface getOriginPoint() {
        return null;
    }

    @Override
    public GeographicPointInterface getEndPoint() {
        return null;
    }

    @Override
    public LocalDateTime getEndDate() {
        return null;
    }

    @Override
    public BigDecimal getImportCost() {
        return null;
    }

    @Override
    public boolean isInProgress() {
        return false;
    }

    @Override
    public void setServiceInit(LocalDateTime initDate, GeographicPointInterface Origin) {
        contInit++;
    }

    @Override
    public void setServiceFinish(LocalDateTime endDate, GeographicPointInterface End, BigDecimal imp, float avSP, float dist, float duration) {
        contFin++;
    }

    public int getContInit() {
        return contInit;
    }

    public int getContFin() {
        return contFin;
    }
}
