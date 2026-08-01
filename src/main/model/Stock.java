package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a stock that has its current price and the price when it is bought.
public class Stock implements Writable {
    int currentPrice;
    int priceWhenBought;

    // REQUIRES: price >= 0
    // EFFECTS: Creates a stock with currentPrice and priceWhenBought to be at given price
    public Stock(int price) {
        currentPrice = price;
        priceWhenBought = price;
    }

    public Stock(int price, int current) {
        currentPrice = current;
        priceWhenBought = price;
    }
    
    
    public int getCurrentPrice() {
        return this.currentPrice; 
    }

    public int getPriceWhenBought() {
        return this.priceWhenBought; 
    }

    public void setCurrentPrice(int price) {
        this.currentPrice = price;
    }

    // EFFECTS: returns the profit by calculating the difference between currentPrice and priceWhenBought
    public int getProfit() {
        return (getCurrentPrice() - getPriceWhenBought());
    }

    // EFFECTS: converts the stock to a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("currentPrice", this.currentPrice);
        json.put("priceWhenBought", this.priceWhenBought);

        return json;
    }



}
