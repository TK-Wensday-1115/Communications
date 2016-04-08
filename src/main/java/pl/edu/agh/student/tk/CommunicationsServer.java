package pl.edu.agh.student.tk;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import pl.edu.agh.student.tk.exceptions.AlreadyRunningException;
import pl.edu.agh.student.tk.exceptions.NotRunningException;
import pl.edu.agh.student.tk.exceptions.ServerException;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

// Based on http://crunchify.com/how-to-start-embedded-http-jersey-server-during-java-application-startup/
public class CommunicationsServer {

    private static HttpServer server;
    private static Collection<SensorReadingCallback> callbacks = new ArrayList<>();

    public static void start() {
        if (server != null) {
            throw new AlreadyRunningException();
        }
        try {
            server = createHttpServer();
        } catch (IOException e) {
            throw new ServerException(e);
        }
        server.start();
    }

    public static void stop() {
        if (server == null) {
            throw new NotRunningException();
        }
        server.stop(0);
        server = null;
    }

    private static HttpServer createHttpServer() throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("pl.edu.agh.student.tk");
        return HttpServerFactory.create(getUri(), resourceConfig);
    }

    private static URI getUri() {
        return UriBuilder.fromUri("http://" + getHostName() + "/").port(8080).build();
    }

    private static String getHostName() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    static void broadcastUpdate(String sensorName, ReadingUpdate update) {
        SensorReading reading = new SensorReading(sensorName, new Date(), update.getValue());
        for (SensorReadingCallback callback : callbacks) {
            callback.receive(reading);
        }
    }

    static void registerCallback(SensorReadingCallback callback) {
        callbacks.add(callback);
    }
}
