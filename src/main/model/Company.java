package model;
import persistence.Writable;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

// Represents a company with a name, list of stocks the user owns, and amount of money invested in it
public class Company implements Writable{
    String name;
    ArrayList<Stock> listOfStock;
    int totalMoneyInvested;

    // EFFECTS: Creates a company with a name, empty list of stocks, 
    //           and no money invested in it.
    public Company(String name) {
        this.name = name;
        listOfStock = new ArrayList<Stock>();
        totalMoneyInvested = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a stock into the listOfStock
    public void buyStock(Stock stock) {
        listOfStock.add(stock);
    }

    // REQUIRES: index >= 0 and index < getNumberOfStocks()
    // MODIFIES: this
    // EFFECTS: Removes the stock at index in listOfStock and returns profit from it
    public int sellStock(int index) {
        Stock removeStock = listOfStock.get(0);
        listOfStock.remove(index);

        return removeStock.getProfit();
    }

    public String getName() {
        return this.name; 
    }

    public ArrayList<Stock> getStocks() {
        return listOfStock;
    }

    // EFFECTS: returns the number of stocks that is in the company
    public int getNumberOfStocks() {
        return this.listOfStock.size(); 
    }

    // MODIFIES: this
    // EFFECTS: Calculates the total amount of money invested and returns it
    public int getTotalMoneyInvested() {
        int total = 0;

        for (Stock stock : listOfStock) {
            total += stock.priceWhenBought;
        }
        this.totalMoneyInvested = total;

        return this.totalMoneyInvested; 
    }

    // EFFECTS: Calculates and returns the profit from stocks in the
    public int getProfit() {
        int profit = 0;

        for (Stock stock : listOfStock) {
            profit += stock.getProfit();
        }

        return profit; 
    }

    // REQUIRES: newPrice >= 0
    // MODIFIES: this
    // EFFECTS: Sets all the shares price to be at newPrice
    public void setNewStockPrice(int newPrice) {
        for (Stock stock : listOfStock) {
            stock.setCurrentPrice(newPrice);
        }
    }

    @Override
    public JSONObject toJson() {
        return null; // stub

    }

    // EFFECTS: retrusn the stocks in the company as a JSON array
    public JSONArray stocksToJson() {
        return null;
    }
    

    

}
