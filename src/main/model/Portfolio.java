package model;

import persistence.Writable;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;


// A portfolio with a list of companies the user owns share in, the amount of money invested
// and the profit they are making
public class Portfolio implements Writable {
    ArrayList<Company> listOfCompanies;
    int moneyInvested;
    int profit;

    // EFFECTS: Creates a portfolio with an empty list of company, no money invested, and no profit.
    public Portfolio() {
        listOfCompanies = new ArrayList<Company>();
        moneyInvested = 0;
        profit = 0;
    }

    // MODIFIES: this
    // EFFECTS: Adds a company to listOfCompanies if it is not inside and returns whether progress is made or not
    public boolean addCompany(Company company) {
        boolean inside = false;
        for (Company c : listOfCompanies) {
            if (c.getName().equals(company.getName())) {
                inside = true;
                break;
            }
        }

        if (!inside) {
            listOfCompanies.add(company);
            EventLog.getInstance().logEvent(new Event(company.getName() + " added to portfolio with some shares."));
        } else {
            EventLog.getInstance().logEvent(new Event("More shares were added to " + company.getName()));
        }

        return (!inside);

    }

    // EFFECTS: Returns the list of companies with the companies in the order they were added in
    public ArrayList<Company> getCompanies() {
        return this.listOfCompanies; 
    }

    // MODIFIES: this
    // EFFECTS: Calculates the total amount of money invested in the company and returns it
    public int getMoneyInvested() {
        int total = 0;

        for (Company company : listOfCompanies) {
            total += company.getTotalMoneyInvested();
        }
        this.moneyInvested = total;
        return this.moneyInvested; 
    }

    // MODIFIES: this
    // EFFECTS: Calculates the total profit and returns it
    public int getProfit() {
        int totalProfit = 0;

        for (Company company : listOfCompanies) {
            totalProfit += company.getProfit();
        }
        this.profit = totalProfit;

        return this.profit; 
    }

    // MODIFIES: this
    // EFFECTS: Removes the company from the portfolio
    public void removeCompany(String name) {
        for (Company company : listOfCompanies) {
            if (company.getName().equals(name)) {
                listOfCompanies.remove(company);
                EventLog.getInstance().logEvent(new Event(name + " was removed from portfolio."));
                break;
            }
        }
    }

    // EFFECTS: Converts the portfolio to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("moneyInvested", this.moneyInvested);
        json.put("profit", this.profit);
        json.put("listOfCompanies", companiesToJson());


        return json; 
    }

    // EFFECTS: returns the companies in the portfolio as a JSON array
    public JSONArray companiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Company company : listOfCompanies) {
            jsonArray.put(company.toJson());
        }

        return jsonArray; 
    }


}
