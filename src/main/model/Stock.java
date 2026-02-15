package model;

// Represents a stock that has its current price and the price when it is bought.
public class Stock {
    int currentPrice;
    int priceWhenBought;

    // REQUIRES: price >= 0
    // EFFECTS: Creates a stock with currentPrice and priceWhenBought to be at given price
    public Stock(int price) {
        currentPrice = price;
        priceWhenBought = price;
    }

    public int getCurrentPrice() {
        return 0; // stub
    }

    public int getPriceWhenBought() {
        return 0; // stub
    }

    public void setCurrentPrice(int price) {

    }

    public int getProfit() {
        return 0; // stub
    }



}
