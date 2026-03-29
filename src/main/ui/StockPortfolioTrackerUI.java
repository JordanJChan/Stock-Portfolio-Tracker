package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.*;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.*;
import persistence.*;

// Image source: https://en.meming.world/wiki/File:Stonks_meme_4.jpg/

// Represents the application's main window frame
@ExcludeFromJacocoGeneratedReport
public class StockPortfolioTrackerUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Portfolio portfolio;
    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel addCompanyPanel;
    private JTextField companyNameInputField;
    private JTextField numberOfStocksField;
    private JTextField stockPriceField;
    private JButton buttonToSubmitCompany;
    private JButton buttonToSaveData;
    private JButton buttonToLoadData;
    private JLabel companyNameLabel;
    private JLabel numberOfStocksLabel;
    private JLabel priceLabel;
    private JLabel companyToRemoveLabel;
    private JTextField companyToRemoveField;
    private JButton buttonToRemoveCompany;
    private JButton buttonToDisplayImage;
    private JPanel portfolioDataPanel;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    // EFFECTS: Sets up the interface and displays it
    public StockPortfolioTrackerUI() {
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Stock Portfolio Tracker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        
        makeInterface();
        
        handleButtonToAddCompany();
        handleButtonToRemoveCompany();
        handleButtonToSaveData();
        handleButtonToLoadData();
        handleButtonToDisplayImage();
        

        handleUserClosingWindow();

        addPanels();

        add(addCompanyPanel);
        add(portfolioDataPanel);
        setVisible(true);


    }

    // EFFECTS: Creates the panels, buttons, text fields, and labels
    public void makeInterface() {
        addCompanyPanel = new JPanel();
        addCompanyPanel.setBackground(Color.green);
        addCompanyPanel.setBounds(0, 0, WIDTH, 100);
        companyNameInputField = new JTextField(15);
        numberOfStocksField = new JTextField(5);
        stockPriceField = new JTextField(5);
        buttonToSubmitCompany = new JButton("Add Company");

        buttonToSaveData = new JButton("Save Data");
        buttonToLoadData = new JButton("Load Data");

        companyNameLabel = new JLabel("Company Name:");
        numberOfStocksLabel = new JLabel("Number of Shares:");
        priceLabel = new JLabel("Price:");

        companyToRemoveLabel = new JLabel("Name of Company to Remove:");
        companyToRemoveField = new JTextField(15);
        buttonToRemoveCompany = new JButton("Remove Company");

        buttonToDisplayImage = new JButton("Display Image");

        portfolioDataPanel = new JPanel();
        portfolioDataPanel.setBounds(0, 100, WIDTH, HEIGHT);
        portfolioDataPanel.setLayout(new BorderLayout());

        textArea = new JTextArea(10, 80);
        scrollPane = new JScrollPane(textArea);
        textArea.setText(displayPortfolio());
        scrollPane.setBounds(0, 50, 700, 500);
    }

    // EFFECTS: adds objects into the panels
    public void addPanels() {
        addCompanyPanel.add(companyNameLabel);
        addCompanyPanel.add(companyNameInputField);
        addCompanyPanel.add(numberOfStocksLabel);
        addCompanyPanel.add(numberOfStocksField);
        addCompanyPanel.add(priceLabel);
        addCompanyPanel.add(stockPriceField);
        addCompanyPanel.add(buttonToSubmitCompany);
        addCompanyPanel.add(companyToRemoveLabel);
        addCompanyPanel.add(companyToRemoveField);
        addCompanyPanel.add(buttonToRemoveCompany);
        addCompanyPanel.add(buttonToSaveData); 
        addCompanyPanel.add(buttonToLoadData);
        addCompanyPanel.add(buttonToDisplayImage);
        portfolioDataPanel.add(scrollPane, java.awt.BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: Handles when the user presses the button to add a company
    public void handleButtonToAddCompany() {
        buttonToSubmitCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyName = companyNameInputField.getText();
                int numberOfStocks = Integer.parseInt(numberOfStocksField.getText());
                int price = Integer.parseInt(stockPriceField.getText());
                addCompany(companyName, numberOfStocks, price);
                textArea.setText(displayPortfolio());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds a company, and the shares into the portfolio
    public void addCompany(String name, int numberOfShares, int price) {
        portfolio.addCompany(new Company(name));
        for (Company company : portfolio.getCompanies()) {
            if (company.getName().equals(name)) {
                for (int i = 0; i < numberOfShares; i++) {
                    company.buyStock(new Stock(price));
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a company when user presses button
    public void handleButtonToRemoveCompany() {
        buttonToRemoveCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyRemoving = companyToRemoveField.getText();
                portfolio.removeCompany(companyRemoving);
                textArea.setText(displayPortfolio());
            }
        });
    }

    // EFFECTS: Saves user data when they press the button
    public void handleButtonToSaveData() {
        buttonToSaveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
                textArea.setText(displayPortfolio());
            }
        });
    }

    // EFFECTS: Loads user data when they press the button
    public void handleButtonToLoadData() {
        buttonToLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
                textArea.setText(displayPortfolio());
            }
        });
    }

    // EFFECTS: returns the string of all the items in the portfolio
    public String displayPortfolio() {
        String portfolioInfo = "Current Portfolio:";
        for (Company company : portfolio.getCompanies()) {
            portfolioInfo = portfolioInfo + "\n" + company.getName();
            for (Stock stock : company.getStocks()) {
                portfolioInfo = portfolioInfo + "\nA stock bought at $" + stock.getPriceWhenBought();
            }
        }

        return portfolioInfo;
    }

    // EFFECTS: Displays image to user
    public void handleButtonToDisplayImage() {
        buttonToDisplayImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame imageFrame = new JFrame("Amazing Visual");
                imageFrame.setSize(WIDTH, HEIGHT);
                ImageIcon visual = new ImageIcon("image/Stonks_meme_4.png");

                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(visual);
                imageFrame.add(imageLabel);

                imageFrame.pack();
                imageFrame.setLocationRelativeTo(null);
                imageFrame.setVisible(true);
            }
        });
    }

    // EFFECTS: Saves data from the portfolio
    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    // EFFECTS: Loads data from the portfolio
    public void loadData() {
        try {
            portfolio = jsonReader.read();
        } catch (IOException e) {
            // pass
        }
    }

    // EFFECTS: Prints Events from EventLog after user closes the window
    public void handleUserClosingWindow() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
            }
        });
    }



    //EFFECTS: Starts the application
    public static void main(String[] args) {
        new StockPortfolioTrackerUI();
    }

}
