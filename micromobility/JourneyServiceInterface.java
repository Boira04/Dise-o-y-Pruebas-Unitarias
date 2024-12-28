package data.micromobility;

import data.data.GeographicPointInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface JourneyServiceInterface{

        public void JourneyService();

        public LocalDateTime getInitDate();

        public float getDuration();

        public float getDistance();

        public float getAvgSpeed();

        public GeographicPointInterface getOriginPoint();

        public GeographicPointInterface getEndPoint();

        public LocalDateTime getEndDate();

        public BigDecimal getImportCost();

        public boolean isInProgress();

        public void setServiceInit(LocalDateTime initDate,GeographicPointInterface Origin);

        public void setServiceFinish(LocalDateTime endDate, GeographicPointInterface End, BigDecimal imp, float avSP, float dist, float duration);
}
