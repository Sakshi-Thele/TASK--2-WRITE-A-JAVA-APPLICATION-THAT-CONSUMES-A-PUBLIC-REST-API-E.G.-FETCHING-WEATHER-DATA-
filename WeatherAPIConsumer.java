package weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPIConsumer {

    private static final String API_KEY = "c7aafa0a82f563a15d92152f59eaab66";
    private static final String CITY = "Pune";

    public static void main(String[] args) {
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + CITY + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            JSONObject main = obj.getJSONObject("main");
            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            String weather = obj.getJSONArray("weather")
                                 .getJSONObject(0)
                                 .getString("description");

            System.out.println("=== Weather Report ===");
            System.out.println("City       : " + CITY);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity   : " + humidity + "%");
            System.out.println("Condition  : " + weather);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
