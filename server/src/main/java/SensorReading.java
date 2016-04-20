import java.time.Instant;

public class SensorReading {
    private final String sensorName;
    private final Instant timestamp;
    private final String value;
    private final String color;

    SensorReading(String sensorName, Instant timestamp, String value, String color) {
        this.sensorName = sensorName;
        this.timestamp = timestamp;
        this.value = value;
        this.color = color;
    }

    public String getSensorName() {
        return sensorName;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }
}
