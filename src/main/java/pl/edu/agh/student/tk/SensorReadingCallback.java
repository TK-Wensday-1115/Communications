package pl.edu.agh.student.tk;

@FunctionalInterface
public interface SensorReadingCallback {
    void receive(SensorReading reading);
}
