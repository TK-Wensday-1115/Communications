package pl.edu.agh.student.smialek.tk.communications.client;

import com.mashape.unirest.http.HttpResponse;

public class ClientException extends RuntimeException {
    private final HttpResponse response;

    public ClientException(HttpResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return String.format("HTTP error: %d %s", response.getStatus(), response.getStatusText());
    }
}
