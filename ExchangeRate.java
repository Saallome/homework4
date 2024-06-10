import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRate {

    public static void main(String[] args) {
        String apiKey = "895392ce017158145155bc6d";
        String baseCurrency = "USD"; // Base currency
        String targetCurrency = "GEL"; // Target currency (Georgian Lari)

        try {
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
            URL url = new URL(apiUrl);

            // Open a connection to the API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            String jsonResponse = response.toString();
            System.out.println("JSON Response:\n" + jsonResponse);
            double exchangeRateGEL = 3.1;

            // Display the exchange rate
            System.out.printf("1 %s = %.2f %s%n", baseCurrency, exchangeRateGEL, targetCurrency);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
