package ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class StockPriceFetcher {

    public static void main(String[] args) throws Exception {

        String apiKey = "1HR4C2CUWEDTWP03";
        String symbol = "AMZN";

        String url =
            "https://www.alphavantage.co/query?function=GLOBAL_QUOTE"
            + "&symbol=" + symbol
            + "&apikey=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        String json = response.body();

        JSONObject obj = new JSONObject(json);

        JSONObject quote = obj.getJSONObject("Global Quote");

        String price = quote.getString("05. price");

        System.out.println(symbol + " price: $" + price);
    }
}