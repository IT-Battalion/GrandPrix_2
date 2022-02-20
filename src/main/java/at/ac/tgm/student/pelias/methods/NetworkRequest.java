package at.ac.tgm.student.pelias.methods;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkRequest implements IDelayMethod {
    @Override
    public void execute() {
        try {
            URL url = new URL("https://tgm.ac.at");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
