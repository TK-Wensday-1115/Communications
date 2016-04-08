package pl.edu.agh.student.tk;

public class Runner {
    public static void main(String[] args) {
        CommunicationsServer.registerCallback(reading ->
                System.out.format("%s: %f\n", reading.getSensorName(), reading.getValue())
        );
        CommunicationsServer.start(8080);
        System.out.println("Server started");
    }
}
