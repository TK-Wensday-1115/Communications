@FunctionalInterface
public interface SensorReadingCallback {
    void receive(SensorReading reading);
}
