package model;

import java.util.ArrayList;

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

    }

    // REQUIRES: index >= 0 and index < getNumberOfStocks()
    // MODIFIES: this
    // EFFECTS: Removes the stock at index in listOfStock and returns profit from it
    public int sellStock(int index) {
        return 0;
    }

    public String getName() {
        return ""; // stub
    }

    public ArrayList<Stock> getStocks() {
        return null; // stub
    }

    // EFFECTS: returns the number of stocks that is in the company
    public int getNumberOfStocks() {
        return 0; // stub
    }

    // EFFECTS: Calculates the total amount of money invested and returns it
    public int getTotalMoneyInvested() {
        return 0; // stub
    }
    

    

}
