package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.*;
import persistence.*;

// Represents the stock portfolio tracker app
@ExcludeFromJacocoGeneratedReport
public class StockPortfolioTrackerApp {
    private Portfolio userPortfolio;
    private Scanner input;
    private String userStringInput;
    private int userNumberInput;
    private static final String JSON_STORE = "./data/portfolio.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Constructs the app and runs it
    public StockPortfolioTrackerApp() {
        initialize();
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: Gets user input and processes it
    @SuppressWarnings("methodlength")
    public void runApp() {
        while (true) {
            displayMenu();
            getUserChoice();
            if (userNumberInput == 1) { // Add company to Portfolio
                handleAddingCompany();
            } else if (userNumberInput == 2) { // View companies in portfolio
                displayCompanies();
            } else if (userNumberInput == 3) { // Buy more shares in a company
                handleBuyingShares();
            } else if (userNumberInput == 4) { // Sell shares in a company
                handleSellingShares();
            } else if (userNumberInput == 5) { // Update a company's stock price
                handleUpdateStockPrice();
            } else if (userNumberInput == 6) { // View profits
                showProfit();
            } else if (userNumberInput == 7) { // View stocks in a company
                handleViewCompanyStocks();
            } else if (userNumberInput == 8) { // Save Portfolio
                savePortfolio();
            } else if (userNumberInput == 9) { // Load Portfolio
                loadPortfolio();
            } else if (userNumberInput == 10) { // Quit
                break;
            } else {
                System.out.println("Please enter a number from 1-8");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes the user portfolio and the ability to get input
    public void initialize() {
        userPortfolio = new Portfolio();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("Welcome to Stock Portfolio Tracker.");
    }

    // MODIFIES: this
    // EFFECTS: Asks and gets the user's choice
    public void getUserChoice() {
        System.out.print("Enter your choice number: ");
        userNumberInput = input.nextInt();
        input.nextLine();
    }

    // EFFECTS: Displaly the choices for the user
    public void displayMenu() {
        System.out.println("\n");
        System.out.println("\t1. Add a company into portfolio");
        System.out.println("\t2. View companies in portfolio");
        System.out.println("\t3. Buy more shares in a company");
        System.out.println("\t4. Sell shares in a company");
        System.out.println("\t5. Update a company's stock price");
        System.out.println("\t6. View profits");
        System.out.println("\t7. View stocks in a company");
        System.out.println("\t8. Save portfolio");
        System.out.println("\t9. Load portfolio");
        System.out.println("\t10. Quit");
        System.out.println("\n");
    }

    // EFFECTS: Displays all the companies in the portfolio
    public void displayCompanies() {
        int position = 1;
        for (Company c : userPortfolio.getCompanies()) {
            System.out.println(position + ". " + c.getName() + ". Number of shares: " + c.getNumberOfStocks());
            position++;
        }
    }

    // REQUIRES: numberShares > 0 and price >= 0
    // MODIFIES: company
    // EFFECTS: Buys shares for the company
    public void addShares(Company company, int numberShares, int price) {
        for (int i = 0; i < numberShares; i++) {
            company.buyStock(new Stock(price));
        }
        System.out.println("Successfully bought " + numberShares + " shares");
    }

    // EFFECTS: Displays the shares in a company
    public void displayShares(Company company) {
        System.out.println("The current stock price is " + company.getStocks().get(0).getCurrentPrice());
        int index = 1;
        for (Stock share : company.getStocks()) {
            System.out.println(
                    index + ". A stock bought at $" + share.getPriceWhenBought()
                    + ". A profit of $" + share.getProfit());
            index++;
        }
    }

    // REQUIRES: index >= 0 and index < company.getNumberOfStocks()
    // MODIFIES: company
    // EFFECTS: Sells the share at given index
    public void sellShare(Company company, int index) {
        company.sellStock(index - 1);
        System.out.println("The share is successfully sold.");
    }

    // EFFECTS: Displays the profit from the portfolio
    public void showProfit() {
        for (Company company : userPortfolio.getCompanies()) {
            System.out.println(
                    "You invested $" + company.getTotalMoneyInvested() + " in " + company.getName() 
                    +  ", earning a profit of $"  + company.getProfit());
        }
        System.out.println(
                "Overall, you invested $" + userPortfolio.getMoneyInvested() 
                + " and your profit is $" + userPortfolio.getProfit());
    }

    // MODIFIES: userPortfolio
    // EFFECTS: Gets information for a company and adds it into the portfolio
    public void handleAddingCompany() {
        System.out.print("What is the company's name? ");
        userStringInput = input.nextLine();
        boolean addStatus = userPortfolio.addCompany(new Company(userStringInput));
        if (addStatus) {
            System.out.println(userStringInput + " is successfully added.");
        } else {
            System.out.println(userStringInput + " is already in your portfolio.");
        }
    }

    // MODIFIES: company
    // EFFECTS: Gets the information for a share and buys it
    public void handleBuyingShares() {
        if (userPortfolio.getCompanies().size() == 0) {
            System.out.println("You have not added any companies. Please add a company first.");
        } else {
            displayCompanies();
            System.out.print("Enter the company number you want to buy more shares in: ");
            userNumberInput = input.nextInt();
            input.nextLine();

            System.out.print("How many shares? ");
            int numberOfShares = input.nextInt();
            input.nextLine();

            System.out.print("What is the price of each share: ");
            int price = input.nextInt();
            input.nextLine();

            Company currentCompany = userPortfolio.getCompanies().get(userNumberInput - 1);

            addShares(currentCompany, numberOfShares, price);
            currentCompany.setNewStockPrice(price);

        }
    }

    // MODIFIES: company
    // EFFECTS: Gets which share the user wants to sell and removes it
    public void handleSellingShares() {
        if (userPortfolio.getCompanies().size() == 0) {
            System.out.println("You have not added any companies. Please add a company first.");
        } else {
            if (userPortfolio.getCompanies().size() == 0) {
                System.out.println("You have not added any companies. Please add a company first.");
            } else {
                displayCompanies();
                System.out.print("Enter the company number you want to sell shares in: ");
                userNumberInput = input.nextInt();
                input.nextLine();

                Company currentCompany = userPortfolio.getCompanies().get(userNumberInput - 1);

                if (currentCompany.getNumberOfStocks() == 0) {
                    System.out.println("You have no shares in this company.");
                } else {
                    displayShares(currentCompany);
                    System.out.print("Enter which share you want to sell: ");
                    int shareToSell = input.nextInt();
                    input.nextLine();

                    sellShare(currentCompany, shareToSell);
                }
            }
        }
    }

    // EFFECTS: Updates the stock price
    public void handleUpdateStockPrice() {
        if (userPortfolio.getCompanies().size() == 0) {
            System.out.println("You have not added any companies. Please add a company first.");
        } else {
            displayCompanies();
            System.out.print("Enter the company number you want to update the stock price of: ");
            userNumberInput = input.nextInt();
            input.nextLine();

            Company currentCompany = userPortfolio.getCompanies().get(userNumberInput - 1);
            
            if (currentCompany.getNumberOfStocks() == 0) {
                System.out.println("You have no shares in this company.");
            } else {
                System.out.print("What is the new price? ");
                int price = input.nextInt();
                input.nextLine();

                currentCompany.setNewStockPrice(price);
            }
        }
    }

    // EFFECTS: Displays the selected company's stock information to user
    public void handleViewCompanyStocks() {
        if (userPortfolio.getCompanies().size() == 0) {
            System.out.println("You have not added any companies. Please add a company first.");
        } else {
            displayCompanies();
            System.out.print("Enter the company number you want to view the shares in: ");
            userNumberInput = input.nextInt();
            input.nextLine();

            Company currentCompany = userPortfolio.getCompanies().get(userNumberInput - 1);

            if (currentCompany.getNumberOfStocks() == 0) {
                System.out.println("You have no shares in this company.");
            } else {
                displayShares(currentCompany);
            }
        }
    }

    // EFFECTS: Saves the portfolio into a file
    public void savePortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(userPortfolio);
            jsonWriter.close();
            System.out.println("Successfully saved portfolio.");
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem saving the portfolio");
        }
    }

    // EFFECTS: Loads the portfolio from the file
    public void loadPortfolio() {
        try {
            userPortfolio = jsonReader.read();
            System.out.println("Successfully loaded the portfolio.");
        } catch (IOException e) {
            System.out.println("There was a problem loading the portfolio.");
        }
    }

}
