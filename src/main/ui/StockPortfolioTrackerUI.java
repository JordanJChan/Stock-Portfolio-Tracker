package ui;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.*;
import persistence.*;

public class StockPortfolioTrackerUI extends JFrame {
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
    private Portfolio portfolio;

    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public StockPortfolioTrackerUI() {
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        setTitle("Stock Portfolio Tracker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel addCompanyPanel = new JPanel();
        addCompanyPanel.setBackground(Color.green);
        addCompanyPanel.setBounds(0, 0, WIDTH, 100);

        JTextField companyNameInputField = new JTextField(15);
        JTextField numberOfStocksField = new JTextField(5);
        JTextField stockPriceField = new JTextField(5);
        JButton buttonToSubmitCompany = new JButton("Add Company");

        JButton buttonToSaveData = new JButton("Save Data");
        JButton buttonToLoadData = new JButton("Load Data");

        JLabel companyNameLabel = new JLabel("Company Name:");
        JLabel numberOfStocksLabel = new JLabel("Number of Shares:");
        JLabel priceLabel = new JLabel("Price:");
        //companyNameLabel.setBounds(50, 50, 200, 30); 

        


        buttonToSubmitCompany.addActionListener(e -> {
            String companyName = companyNameInputField.getText();
            int numberOfStocks = Integer.parseInt(numberOfStocksField.getText());
            int price = Integer.parseInt(stockPriceField.getText());
            addCompany(companyName, numberOfStocks, price);
            System.out.println("You entered: " + companyName);
            System.out.println("You entered: " + numberOfStocks);
            System.out.println("You entered: " + price);
        });

        buttonToSaveData.addActionListener(e -> {
            saveData();
        });

        buttonToLoadData.addActionListener(e -> {
            loadData();
        });









        addCompanyPanel.add(companyNameLabel);
        addCompanyPanel.add(companyNameInputField);
        addCompanyPanel.add(numberOfStocksLabel);
        addCompanyPanel.add(numberOfStocksField);
        addCompanyPanel.add(priceLabel);
        addCompanyPanel.add(stockPriceField);
        addCompanyPanel.add(buttonToSubmitCompany);
        addCompanyPanel.add(buttonToSaveData); 
        addCompanyPanel.add(buttonToLoadData);

        add(addCompanyPanel);
        setVisible(true);


    }

    public void addCompany(String name, int numberOfShares, int price) {
        portfolio.addCompany(new Company(name));
        for (Company company : portfolio.getCompanies()) {
            if (company.getName().equals(name)) {
                for (int i = 0; i < numberOfShares; i++) {
                    company.buyStock(new Stock(price));
                }
            }
        }
        System.out.println("yay");
    }

    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println("Successfully saved portfolio.");
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem saving the portfolio");
        }
    }

    public void loadData() {
        try {
            portfolio = jsonReader.read();
            System.out.println("Successfully loaded the portfolio.");
        } catch (IOException e) {
            System.out.println("There was a problem loading the portfolio.");
        }
    }



    // starts the application
	public static void main(String[] args) {
		new StockPortfolioTrackerUI();
	}

}
