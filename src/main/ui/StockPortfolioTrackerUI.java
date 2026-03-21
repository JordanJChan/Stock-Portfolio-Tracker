package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.*;
import persistence.*;

public class StockPortfolioTrackerUI extends JFrame {
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
    private Portfolio portfolio;

    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String IMAGE_SOURCE = "/image/NotStonks.webp";

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
        textArea.setText("This is a very long text. ..More lines... Even more... sdfjs fskl fsjkld fskld fslkdf slkf kl kldsf kldsf ");
        scrollPane.setBounds(0, 50, 700, 500);


        buttonToSubmitCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyName = companyNameInputField.getText();
                int numberOfStocks = Integer.parseInt(numberOfStocksField.getText());
                int price = Integer.parseInt(stockPriceField.getText());
                addCompany(companyName, numberOfStocks, price);
                System.out.println("You entered: " + companyName);
                System.out.println("You entered: " + numberOfStocks);
                System.out.println("You entered: " + price);
            }
        });


        buttonToRemoveCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyRemoving = companyToRemoveField.getText();
                removeCompany(companyRemoving);
            }
        });

        buttonToSaveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        buttonToLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        buttonToDisplayImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame imageFrame = new JFrame("Amazing Visual");
                imageFrame.setSize(WIDTH, HEIGHT);

                String sep = System.getProperty("file.separator");
                ImageIcon visual = new ImageIcon(System.getProperty("user.dir") + sep + "image" + sep + "Stonks_meme_4.png");

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
