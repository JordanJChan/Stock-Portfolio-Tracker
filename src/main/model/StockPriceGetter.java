package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

// Class for getting the real time stock price of a company. Uses the singleton design pattern to ensure that only one instance of the class is created.
public class StockPriceGetter {
    private static StockPriceGetter getter;

    private StockPriceGetter() {
        // Probably nothing here
    }

    public static StockPriceGetter getInstance() {
        if (getter == null) {
            getter = new StockPriceGetter();
        }
        return getter;
    }

    public int getPrice(String symbol) throws Exception {
        String apiKey = "1HR4C2CUWEDTWP03";

        String url =
            "https://www.alphavantage.co/query?function=GLOBAL_QUOTE"
            + "&symbol=" + symbol
            + "&apikey=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        Thread.sleep(1000);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        
        String json = response.body();

        JSONObject obj = new JSONObject(json);

        if (obj.has("Information")) {
            Thread.sleep(1000);
        }

        JSONObject quote = obj.getJSONObject("Global Quote");

        
        String priceString = quote.getString("05. price");
        double price = Double.parseDouble(priceString);
        
        int priceInt = (int) price;
        
        return priceInt;
    }

}
