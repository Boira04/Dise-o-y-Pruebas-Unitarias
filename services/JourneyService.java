package data.services;
import java.util.Date;

public class JourneyService {
    // Class members
    private Date initDate;
    private Date initHour;
    private double duration;
    private double distance;
    private double avgSpeed;
    private String originPoint;
    private String endPoint;
    private Date endDate;
    private Date endHour;
    private double importCost;
    private int serviceID;
    private boolean inProgress;

    // Constructor
    public JourneyService(Date initDate, Date initHour, double duration, double distance, double avgSpeed, String originPoint, String endPoint, Date endDate, Date endHour, double importCost, int serviceID, boolean inProgress) {
        this.initDate = initDate;
        this.initHour = initHour;
        this.duration = duration;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
        this.originPoint = originPoint;
        this.endPoint = endPoint;
        this.endDate = endDate;
        this.endHour = endHour;
        this.importCost = importCost;
        this.serviceID = serviceID;
        this.inProgress = inProgress;
    }

    // Getter methods
    public Date getInitDate() {
        return initDate;
    }

    public Date getInitHour() {
        return initHour;
    }

    public double getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public String getOriginPoint() {
        return originPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getEndHour() {
        return endHour;
    }

    public double getImportCost() {
        return importCost;
    }

    public int getServiceID() {
        return serviceID;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    // Setter methods
    public void setServiceInit() {
        this.initDate = initDate;
        this.initHour = initHour;
        this.inProgress = true;
    }
    public void setServiceFinish() {
        this.endDate = endDate;
        this.endHour = endHour;
        this.inProgress = false;
    }
}
