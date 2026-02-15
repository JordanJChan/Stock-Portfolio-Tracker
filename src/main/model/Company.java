package model;

import java.util.ArrayList;

// Represents a company with a name, list of stocks the user owns, and amount of money invested in it
public class Company {
    String name;
    ArrayList<Stock> listOfStock;
    int totalMoneyInvested;

    // EFFECTS: Creates a company with empty list of stocks, zero for number of stocks
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

    // EFFECTS: Calculates the total amount of money invested and returns it
    public int getTotalMoneyInvested() {
        int total = 0;

        for (Stock stock : listOfStock) {
            total += stock.priceWhenBought;
        }

        return total; 
    }

    // EFFECTS: Calculates and returns the profit from stocks in the
    public int getProfit() {
        int profit = 0;

        for (Stock stock : listOfStock) {
            profit += stock.getProfit();
        }

        return profit; 
    }
    

    

}
