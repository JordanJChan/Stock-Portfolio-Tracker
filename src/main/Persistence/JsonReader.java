package persistence;

import model.Company;
import model.Portfolio;
import model.Stock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads the portfolio from JSON data in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads portfolio from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Portfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePortfolio(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        Portfolio portfolio = new Portfolio();
        addCompanies(portfolio, jsonObject);

        return portfolio; 
    }

    // MODIFIES: portfolio
    // EFFECTS: parses companies from JSON object and adds them to portfolio
    private void addCompanies(Portfolio portfolio, JSONObject jsonObject) {
        JSONArray companiesArray = jsonObject.getJSONArray("listOfCompanies");
        for (Object obj : companiesArray) {
            JSONObject companyJson = (JSONObject) obj;
            addCompany(portfolio, companyJson);
        }
    }

    // MODIFIES: portfolio
    // EFFECTS: parses through company from JSON object, creates company and adds stocks to it, then add to portfolio
    private void addCompany(Portfolio portfolio, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int totalMoneyInvested = jsonObject.getInt("totalMoneyInvested");

        Company company = new Company(name);
        company.setTotalMoneyInvested(totalMoneyInvested);

        addStocks(company, jsonObject);

        portfolio.addCompany(company);
        

    }

    // MODIFIES: company
    // EFFECTS: parses though stocks from JSON object and adds it to company
    private void addStocks(Company company, JSONObject jsonObject) {
        JSONArray stockArray = jsonObject.getJSONArray("listOfStock");

        for (Object obj : stockArray) {
            JSONObject stockJson = (JSONObject) obj;
            addStock(company, stockJson);
        }
    }

    // MODIFIES: company
    // EFFECTS: creates a new stock and adds it to company
    private void addStock(Company company, JSONObject jsonObject) {
        int currentPrice = jsonObject.getInt("currentPrice");
        int priceWhenBought = jsonObject.getInt("priceWhenBought");

        Stock stock = new Stock(priceWhenBought);
        stock.setCurrentPrice(currentPrice);

        company.buyStock(stock);

    }





}
