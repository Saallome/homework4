import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {
    private static final String API_KEY = "a7a655939f0bf7d6627c904414c7763d"; // Replace with your API key
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        String city = "Tbilisi";
        double temperature = getTemperature(city);
        System.out.println("Temperature in " + city + ": " + temperature + "°C");
    }

    private static double getTemperature(String city) {
        try {
            URL url = new URL(API_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            String jsonResponse = response.toString();
            int start = jsonResponse.indexOf("\"temp\":");
            if (start == -1) {
                throw new RuntimeException("Temperature data not found in API response");
            }
            int end = jsonResponse.indexOf(",", start);
            if (end == -1) {
                end = jsonResponse.indexOf("}", start);
            }
            String tempStr = jsonResponse.substring(start + 7, end);
            double temperature = Double.parseDouble(tempStr);

            return temperature;
        } catch (IOException e) {
            e.printStackTrace();
            return Double.NaN; // Return NaN or throw exception based on your error handling strategy
        }
    }
}
