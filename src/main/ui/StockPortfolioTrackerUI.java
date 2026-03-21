package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.*;
import persistence.*;

// Image source: https://en.meming.world/wiki/File:Stonks_meme_4.jpg/

@ExcludeFromJacocoGeneratedReport
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

        JLabel companyToRemoveLabel = new JLabel("Name of Company to Remove:");
        JTextField companyToRemoveField = new JTextField(15);
        JButton buttonToRemoveCompany = new JButton("Remove Company");

        JButton buttonToDisplayImage = new JButton("Display Image");

        JPanel portfolioDataPanel = new JPanel();
        portfolioDataPanel.setBackground(Color.lightGray);
        portfolioDataPanel.setBounds(0, 100, WIDTH, HEIGHT);
        portfolioDataPanel.setLayout(new BorderLayout());
        

        JTextArea textArea = new JTextArea(10, 80);
        textArea.setLineWrap(true);              // wrap text to next line
        //textArea.setWrapStyleWord(true);         // wrap at word boundaries
        

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(displayPortfolio());
        scrollPane.setBounds(0, 50, 700, 500);

        
        buttonToSubmitCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyName = companyNameInputField.getText();
                int numberOfStocks = Integer.parseInt(numberOfStocksField.getText());
                int price = Integer.parseInt(stockPriceField.getText());
                addCompany(companyName, numberOfStocks, price);
                textArea.setText(displayPortfolio());
            }
        });


        buttonToRemoveCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyRemoving = companyToRemoveField.getText();
                removeCompany(companyRemoving);
                textArea.setText(displayPortfolio());
            }
        });

        buttonToSaveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
                textArea.setText(displayPortfolio());
            }
        });

        buttonToLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
                textArea.setText(displayPortfolio());
            }
        });

        buttonToDisplayImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame imageFrame = new JFrame("Amazing Visual");
                imageFrame.setSize(WIDTH, HEIGHT);

                String sep = System.getProperty("file.separator");
                // ImageIcon visual = new ImageIcon(System.getProperty("user.dir") + sep + "image" + sep + "Stonks_meme_4.png");
                ImageIcon visual = new ImageIcon("image/Stonks_meme_4.png");

                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(visual);
                imageFrame.add(imageLabel);

                imageFrame.pack();
                imageFrame.setLocationRelativeTo(null);
                imageFrame.setVisible(true);
            }
        });


        

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

        add(addCompanyPanel);
        add(portfolioDataPanel);
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

    public void removeCompany(String name) {
        for (Company company : portfolio.getCompanies()) {
            if (company.getName().equals(name)) {
                portfolio.getCompanies().remove(company);
                break;
            }
        }
    }

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
