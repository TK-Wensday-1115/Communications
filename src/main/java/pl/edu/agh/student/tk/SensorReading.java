package pl.edu.agh.student.tk;

import java.util.Date;

public class SensorReading {
    private final String sensorName;
    private final Date timestamp;
    private final float value;

    SensorReading(String sensorName, Date timestamp, float value) {
        this.sensorName = sensorName;
        this.timestamp = timestamp;

        this.value = value;
    }

    public String getSensorName() {
        return sensorName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public float getValue() {
        return value;
    }
}
