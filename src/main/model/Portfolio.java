package model;

import java.util.ArrayList;

// A portfolio with a list of companies the user owns share in, the amount of money invested
// and the profit they are making
public class Portfolio {
    ArrayList<Company> listOfCompanies;
    int moneyInvested;
    int profit;

    // EFFECTS: Creates a portfolio with an empty list of company, no money invested, and no profit.
    public Portfolio() {
        listOfCompanies = new ArrayList<Company>();
        moneyInvested = 0;
        profit = 0;
    }

    // EFFECTS: Adds a company to listOfCompanies if it is not inside
    public void addCompany(Company company) {

    }

    // EFFECTS: Returns the list of companies with the companies in the order they were added in
    public ArrayList<Company> getCompanies() {
        return null; // stub
    }

    // EFFECTS: Calculates the total amount of money invested in the company and returns it
    public int getMoneyInvested() {
        return 0; // stub
    }

    // EFFECTS: Calculates the total profit and returns it
    public int getProfit() {
        return 0; // stub
    }


}
