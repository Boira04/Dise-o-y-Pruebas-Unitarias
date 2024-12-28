package data.micromobility;

import data.data.GeographicPointInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JourneyServiceInterface {
    // Class members
    private LocalDateTime initDate;
    private float duration;
    private float distance;
    private float avgSpeed;
    private GeographicPointInterface originPoint;
    private GeographicPointInterface endPoint;
    private LocalDateTime endDate;
    private LocalDateTime endHour;
    private BigDecimal imp;
    private boolean inProgress;

    // Constructor
    public void JourneyService() {
        this.initDate = null;
        this.duration = 0;
        this.distance = 0;
        this.avgSpeed = 0;
        this.originPoint = null;
        this.endPoint = null;
        this.endDate = null;
        this.endHour = null;
        this.imp = null;
        this.inProgress = false;
    }

    // Getter methods
    public LocalDateTime getInitDate() {
        return initDate;
    }

    public float getDuration() {
        return duration;
    }

    public float getDistance() {
        return distance;
    }

    public float getAvgSpeed() {
        return avgSpeed;
    }

    public GeographicPointInterface getOriginPoint() {
        return originPoint;
    }

    public GeographicPointInterface getEndPoint() {
        return endPoint;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getImportCost() {
        return imp;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    // Setter methods
    public void setServiceInit(LocalDateTime initDate,GeographicPointInterface Origin) {
        this.initDate = initDate;
        this.originPoint = Origin;
        this.inProgress = true;

    }

    public void setServiceFinish(LocalDateTime endDate, GeographicPointInterface End, BigDecimal imp, float avSP, float dist, float duration) {
        this.endDate = endDate;
        this.endPoint = End;
        this.imp = imp;
        this.avgSpeed = avSP;
        this.distance = dist;
        this.duration = duration;
        this.inProgress = false;
    }
}
