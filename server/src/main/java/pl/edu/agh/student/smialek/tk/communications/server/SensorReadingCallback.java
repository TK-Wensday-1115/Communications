package pl.edu.agh.student.smialek.tk.communications.server;

@FunctionalInterface
public interface SensorReadingCallback {
    void receive(SensorReading reading);
}
