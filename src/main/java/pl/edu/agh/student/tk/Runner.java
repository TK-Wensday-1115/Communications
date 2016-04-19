package pl.edu.agh.student.tk;

import java.time.format.DateTimeFormatter;

public class Runner {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        CommunicationsServer.registerCallback(reading ->
                System.out.format("[%s] %s: <%s> %s\n",
                        dateFormatter.format(reading.getTimestamp()), reading.getSensorName(),
                        reading.getColor(), reading.getValue()
                ));
        CommunicationsServer.start(8080);
        System.out.println("Server started");
    }
}
