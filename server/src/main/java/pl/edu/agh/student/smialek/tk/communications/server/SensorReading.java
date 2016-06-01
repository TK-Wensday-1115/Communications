package pl.edu.agh.student.smialek.tk.communications.server;

import java.util.Date;

public class SensorReading {
    private final String sensorName;
    private final Date timestamp;
    private final String value;
    private final String color;

    SensorReading(String sensorName, Date timestamp, String value, String color) {
        this.sensorName = sensorName;
        this.timestamp = timestamp;
        this.value = value;
        this.color = color;
    }

    public String getSensorName() {
        return sensorName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }
}
