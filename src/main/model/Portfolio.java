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
        boolean inside = false;
        for (Company c : listOfCompanies) {
            if (c.getName().equals(company.getName())) {
                inside = true;
                break;
            }
        }

        if (!inside) {
            listOfCompanies.add(company);
        }

    }

    // EFFECTS: Returns the list of companies with the companies in the order they were added in
    public ArrayList<Company> getCompanies() {
        return this.listOfCompanies; 
    }

    // EFFECTS: Calculates the total amount of money invested in the company and returns it
    public int getMoneyInvested() {
        int total = 0;

        for (Company company : listOfCompanies) {
            total += company.getTotalMoneyInvested();
        }

        return total; 
    }

    // EFFECTS: Calculates the total profit and returns it
    public int getProfit() {
        int totalProfit = 0;

        for (Company company : listOfCompanies) {
            totalProfit += company.getProfit();
        }

        return totalProfit; 
    }


}
