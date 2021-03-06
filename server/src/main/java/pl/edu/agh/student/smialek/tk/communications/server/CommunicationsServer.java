package pl.edu.agh.student.smialek.tk.communications.server;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class CommunicationsServer {

    private static HttpServer server;
    private static URI uri;
    private static final Collection<SensorReadingCallback> callbacks = new HashSet<SensorReadingCallback>();

    private CommunicationsServer() {
        throw new InstantiationError("Don't!");
    }

    public static void start(int port) {
        if (server != null) {
            throw new IllegalStateException("Server already running");
        }
        ResourceConfig resourceConfig = new ResourceConfig(RestResource.class);
        uri = UriBuilder.fromUri("http://localhost/").port(port).build();
        server = JdkHttpServerFactory.createHttpServer(uri, resourceConfig);
    }

    public URI getUri() {
        return uri;
    }

    static void broadcastUpdate(String sensorName, ReadingUpdate update) {
        SensorReading reading = new SensorReading(sensorName, new Date(update.getTimestamp()), update.getValue(), update.getColor());
        for (SensorReadingCallback callback : callbacks) {
            callback.receive(reading);
        }
    }

    public static void registerCallback(SensorReadingCallback callback) {
        callbacks.add(callback);
    }
}
