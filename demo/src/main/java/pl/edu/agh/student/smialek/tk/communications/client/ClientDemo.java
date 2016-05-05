package pl.edu.agh.student.smialek.tk.communications.client;

import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpHost;

import java.util.Random;

public class ClientDemo {

    private static final Random random = new Random();

    private static final String[] PHONETIC_ALPHABET = new String[]{
            "Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey",
            "X-ray", "Yankee", "Zulu"
    };

    private static String getRandomWord() {
        return PHONETIC_ALPHABET[random.nextInt(PHONETIC_ALPHABET.length)];
    }

    public static void main(String[] args) {
        // Proxy - ignore this
        configureProxy(args);

        // Actual code - you probably need this
        CommunicationsClient client = new CommunicationsClient("localhost", 8080, getRandomWord());
        client.sendUpdate(getRandomWord(), getRandomWord());
    }

    private static void configureProxy(String[] args) {
        if (args.length == 2 && "--proxy".equals(args[0])) {
            String[] parts = args[1].split(":");
            String host = parts[0];
            int port = Integer.valueOf(parts[1]);
            Unirest.setProxy(new HttpHost(host, port));
        }
    }
}
