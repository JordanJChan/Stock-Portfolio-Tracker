package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Company;
import model.Event;
import model.EventLog;
import model.Portfolio;
import model.Stock;
import persistence.JsonReader;
import persistence.JsonWriter;

// Image source: https://en.meming.world/wiki/File:Stonks_meme_4.jpg/

// Represents the application's main window frame
@ExcludeFromJacocoGeneratedReport
public class StockPortfolioTrackerUI extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/portfolio.json";

    private Portfolio portfolio;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel summaryPanel;
    private JPanel formPanel;
    private JPanel portfolioDataPanel;
    private JPanel contentPanel;
    private JLabel summaryLabel;
    private JLabel statusLabel;
    private JTextField companyNameInputField;
    private JTextField numberOfStocksField;
    private JTextField stockPriceField;
    private JTextField companyToRemoveField;
    private JButton buttonToSubmitCompany;
    private JButton buttonToSaveData;
    private JButton buttonToLoadData;
    private JButton buttonToRemoveCompany;
    private JButton buttonToUpdatePrices;
    private JButton buttonToRefreshView;
    private JButton buttonToDisplayImage;
    private JButton buttonToClearForm;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JScrollPane mainScrollPane;

    // EFFECTS: Sets up the interface and displays it
    public StockPortfolioTrackerUI() {
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Stock Portfolio Tracker");
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(244, 247, 250));
        setLayout(new BorderLayout(12, 12));

        makeInterface();
        handleButtonToAddCompany();
        handleButtonToRemoveCompany();
        handleButtonToSaveData();
        handleButtonToLoadData();
        handleButtonToDisplayImage();
        handleButtonToUpdatePrices();
        handleButtonToRefreshView();
        handleButtonToClearForm();
        handleUserClosingWindow();
        addPanels();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Creates the panels, buttons, text fields, and labels
    public void makeInterface() {
        summaryPanel = new JPanel();
        summaryPanel.setBackground(new Color(250, 252, 255));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 221, 236)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        summaryLabel = new JLabel("Portfolio Summary");
        summaryLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        summaryLabel.setForeground(new Color(31, 81, 142));
        summaryLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        statusLabel = new JLabel("Add a company to start tracking your investments.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(86, 101, 122));
        statusLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(248, 250, 252));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(188, 204, 223)),
                        "Quick Actions", TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 12), new Color(61, 92, 124)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        companyNameInputField = new JTextField(18);
        numberOfStocksField = new JTextField(8);
        stockPriceField = new JTextField(8);
        companyToRemoveField = new JTextField(18);

        buttonToSubmitCompany = new JButton("Add Company");
        buttonToSaveData = new JButton("Save Data");
        buttonToLoadData = new JButton("Load Data");
        buttonToRemoveCompany = new JButton("Remove Company");
        buttonToUpdatePrices = new JButton("Update Prices");
        buttonToRefreshView = new JButton("Refresh View");
        buttonToDisplayImage = new JButton("Show Meme");
        buttonToClearForm = new JButton("Clear");

        portfolioDataPanel = new JPanel(new BorderLayout(8, 8));
        portfolioDataPanel.setBackground(new Color(244, 247, 250));
        portfolioDataPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(244, 247, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        textArea.setText(displayPortfolio());
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        mainScrollPane = new JScrollPane(contentPanel);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    // EFFECTS: adds objects into the panels
    public void addPanels() {
        summaryPanel.add(summaryLabel);
        summaryPanel.add(statusLabel);
        summaryPanel.add(new JLabel(" "));

        int row = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        addLabelAndField(formPanel, gbc, row++, "Company", companyNameInputField);
        addLabelAndField(formPanel, gbc, row++, "Shares", numberOfStocksField);
        addLabelAndField(formPanel, gbc, row++, "Price", stockPriceField);

        JPanel actionButtons = new JPanel();
        actionButtons.setBackground(new Color(248, 250, 252));
        actionButtons.add(buttonToSubmitCompany);
        actionButtons.add(buttonToClearForm);
        actionButtons.add(buttonToRefreshView);
        formPanel.add(actionButtons, gbcGridBagConstraints(gbc, row++, 3));

        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Remove Company:"), gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(4, 64, 4, 4);
        formPanel.add(companyToRemoveField, gbc);
        gbc.insets = new Insets(4, 4, 4, 4);
        row++;
        JPanel removeButtons = new JPanel();
        removeButtons.setBackground(new Color(248, 250, 252));
        removeButtons.add(buttonToRemoveCompany);
        removeButtons.add(buttonToSaveData);
        removeButtons.add(buttonToLoadData);
        removeButtons.add(buttonToUpdatePrices);
        removeButtons.add(buttonToDisplayImage);
        formPanel.add(removeButtons, gbcGridBagConstraints(gbc, row++, 3));

        contentPanel.add(summaryPanel);
        contentPanel.add(formPanel);
        contentPanel.add(portfolioDataPanel);
        portfolioDataPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainScrollPane, BorderLayout.CENTER);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText + ":"), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private GridBagConstraints gbcGridBagConstraints(GridBagConstraints gbc, int row, int gridWidth) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = gridWidth;
        return gbc;
    }

    // MODIFIES: this
    // EFFECTS: Handles when the user presses the button to add a company
    public void handleButtonToAddCompany() {
        buttonToSubmitCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String companyName = companyNameInputField.getText().trim();
                    int numberOfStocks = Integer.parseInt(numberOfStocksField.getText().trim());
                    int price = Integer.parseInt(stockPriceField.getText().trim());
                    if (companyName.isEmpty()) {
                        showStatus("Please enter a company name.", new Color(180, 50, 50));
                        return;
                    }
                    if (numberOfStocks <= 0 || price < 0) {
                        showStatus("Shares must be positive and price cannot be negative.", new Color(180, 50, 50));
                        return;
                    }
                    addCompany(companyName, numberOfStocks, price);
                    clearInputs();
                    showStatus("Added " + companyName + " to your portfolio.", new Color(0, 120, 60));
                    refreshDisplay();
                } catch (NumberFormatException exception) {
                    showStatus("Please enter valid numeric values for shares and price.", new Color(180, 50, 50));
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds a company, and the shares into the portfolio
    public void addCompany(String name, int numberOfShares, int price) {
        Company companyToAdd = new Company(name);
        portfolio.addCompany(companyToAdd);
        for (Company company : portfolio.getCompanies()) {
            if (company.getName().equals(name)) {
                int currentRealPrice = company.getRealCurrentPrice();
                for (int i = 0; i < numberOfShares; i++) {
                    company.buyStock(new Stock(price, currentRealPrice));
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a company when user presses button
    public void handleButtonToRemoveCompany() {
        buttonToRemoveCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyRemoving = companyToRemoveField.getText().trim();
                if (companyRemoving.isEmpty()) {
                    showStatus("Enter a company name to remove.", new Color(180, 50, 50));
                    return;
                }
                portfolio.removeCompany(companyRemoving);
                companyToRemoveField.setText("");
                showStatus("Removed " + companyRemoving + " from the portfolio.", new Color(0, 120, 60));
                refreshDisplay();
            }
        });
    }

    // EFFECTS: Saves user data when they press the button
    public void handleButtonToSaveData() {
        buttonToSaveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
                showStatus("Portfolio saved successfully.", new Color(0, 120, 60));
                refreshDisplay();
            }
        });
    }

    // EFFECTS: Loads user data when they press the button
    public void handleButtonToLoadData() {
        buttonToLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
                showStatus("Portfolio loaded successfully.", new Color(0, 120, 60));
                refreshDisplay();
            }
        });
    }

    // EFFECTS: Updates prices for all companies to current market value
    public void handleButtonToUpdatePrices() {
        buttonToUpdatePrices.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Company company : portfolio.getCompanies()) {
                    company.updatePriceToCurrent();
                }
                showStatus("Latest prices have been requested.", new Color(0, 120, 60));
                refreshDisplay();
            }
        });
    }

    // EFFECTS: Refreshes the portfolio view
    public void handleButtonToRefreshView() {
        buttonToRefreshView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDisplay();
            }
        });
    }

    // EFFECTS: Clears the input fields
    public void handleButtonToClearForm() {
        buttonToClearForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearInputs();
                showStatus("Fields cleared.", new Color(70, 90, 110));
            }
        });
    }

    // EFFECTS: returns the string of all the items in the portfolio
    public String displayPortfolio() {
        StringBuilder portfolioInfo = new StringBuilder();
        portfolioInfo.append(portfolio.getSummaryText()).append("\n\n");

        if (portfolio.getCompanies().isEmpty()) {
            portfolioInfo.append("No companies added yet. Start by adding one above or press 'Load Data' if you have saved data.");
            return portfolioInfo.toString();
        }

        for (Company company : portfolio.getCompanies()) {
            portfolioInfo.append(company.getName()).append(" (")
                    .append(company.getNumberOfStocks()).append(" shares)\n");
            for (Stock stock : company.getStocks()) {
                portfolioInfo.append("  - Bought at $")
                        .append(stock.getPriceWhenBought())
                        .append(" | Current: $")
                        .append(stock.getCurrentPrice())
                        .append(" | Profit: $")
                        .append(stock.getProfit())
                        .append("\n");
            }
            portfolioInfo.append("\n");
        }

        return portfolioInfo.toString();
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
            showStatus("Unable to save portfolio.", new Color(180, 50, 50));
        }
    }

    // EFFECTS: Loads data from the portfolio
    public void loadData() {
        try {
            portfolio = jsonReader.read();
        } catch (IOException e) {
            showStatus("Unable to load portfolio.", new Color(180, 50, 50));
        }
    }

    private void refreshDisplay() {
        textArea.setText(displayPortfolio());
        summaryLabel.setText(portfolio.getSummaryText().split("\\n")[0]);
        statusLabel.setText("Updated portfolio view.");
        statusLabel.setForeground(new Color(70, 90, 110));
    }

    private void clearInputs() {
        companyNameInputField.setText("");
        numberOfStocksField.setText("");
        stockPriceField.setText("");
        companyToRemoveField.setText("");
    }

    private void showStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    // EFFECTS: Prints Events from EventLog after user closes the window
    public void handleUserClosingWindow() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
            }
        });
    }

    // EFFECTS: Starts the application
    public static void main(String[] args) {
        new StockPortfolioTrackerUI();
    }

}
