import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Runner {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withZone(ZoneId.systemDefault());
        CommunicationsServer.registerCallback(reading ->
                System.out.format("[%s] %s: <%s> %s\n",
                        dateFormatter.format(reading.getTimestamp()), reading.getSensorName(),
                        reading.getColor(), reading.getValue()
                ));
        CommunicationsServer.start(8080);
        System.out.println("Server started");
    }
}
