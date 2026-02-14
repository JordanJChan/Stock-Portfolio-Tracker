package model;

import java.util.ArrayList;

public class Company {
    ArrayList<Stock> listOfStock;
    int totalNumberOfStocks;
    int totalMoneyInvested;

    // EFFECTS: Creates a company with empty list of stocks, zero for number of stocks
    //           and no money invested in it.
    public Company() {
        listOfStock = new ArrayList<Stock>();
        totalNumberOfStocks = 0;
        totalMoneyInvested = 0;
    }

    public void buyStock(Stock stock) {

    }

    public void sellStock(Stock stock) {

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
    
    // EFFECTS: Prints out all the stocks in the company
    public void viewAllStocks() {

    }

    

}
