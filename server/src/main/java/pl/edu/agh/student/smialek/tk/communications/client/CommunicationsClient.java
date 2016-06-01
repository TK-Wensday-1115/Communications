package pl.edu.agh.student.smialek.tk.communications.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import pl.edu.agh.student.smialek.tk.communications.server.ReadingUpdate;

import java.io.IOException;
import java.util.Date;

public class CommunicationsClient {
    private final String url;

    public CommunicationsClient(String host, int port, String sensorName) {
        url = String.format("http://%s:%d/sensor/%s", host, port, sensorName);
    }

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void sendUpdate(String value, String color) {
        try {
            ReadingUpdate readingUpdate = new ReadingUpdate();
            readingUpdate.setTimestamp(new Date().getTime());
            readingUpdate.setValue(value);
            readingUpdate.setColor(color);

            HttpResponse<String> response = Unirest.post(url)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(readingUpdate)
                    .asString();
            if (notSuccessful(response)) {
                throw new ClientException(response);
            }
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean notSuccessful(HttpResponse response) {
        return response.getStatus() / 100 != 2;
    }
}
