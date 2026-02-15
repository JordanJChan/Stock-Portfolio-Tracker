package ui;

import java.util.Scanner;

import model.*;

public class StockPortfolioTrackerApp {
    private Portfolio userPortfolio;
    private Scanner input;
    private String userStringInput;
    private int userNumberInput;

    public StockPortfolioTrackerApp() {
        runApp();
    }

    public void runApp() {
        initialize();
        boolean running = true;
        while (running) {
            displayMenu();

            System.out.print("Enter your choice number: ");
            userNumberInput = input.nextInt();
            input.nextLine();

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
            } else if (userNumberInput == 8) { // Quit
                System.out.println("Thanks for using this program.");
                running = false;
            } else {
                System.out.println("Please enter a number from 1-8");
            }
        }
    }

    public void initialize() {
        userPortfolio = new Portfolio();
        input = new Scanner(System.in);
        System.out.println("Welcome to Stock Portfolio Tracker.");
    }

    public void displayMenu() {
        System.out.println("\n");
        System.out.println("\t1. Add a company into portfolio");
        System.out.println("\t2. View companies in portfolio");
        System.out.println("\t3. Buy more shares in a company");
        System.out.println("\t4. Sell shares in a company");
        System.out.println("\t5. Update a company's stock price");
        System.out.println("\t6. View profits");
        System.out.println("\t7. View stocks in a company");
        System.out.println("\t8. Quit");
        System.out.println("\n");
    }

    public void displayCompanies() {
        int position = 1;
        for (Company c : userPortfolio.getCompanies()) {
            System.out.println(position + ". " + c.getName() + ". Number of shares: " + c.getNumberOfStocks());
            position++;
        }
    }

    public void addShares(Company company, int numberShares, int price) {
        for (int i = 0; i < numberShares; i++) {
            company.buyStock(new Stock(price));
        }
        System.out.println("Successfully bought " + numberShares + " shares" );
    }

    public void displayShares(Company company) {
        System.out.println("The current stock price is " + company.getStocks().get(0).getCurrentPrice());
        int index = 1;
        for (Stock share : company.getStocks()) {
            System.out.println(index + ". A stock bought at $" + share.getPriceWhenBought() + ". A profit of $" + share.getProfit());
            index++;
        }
    }

    public void sellShare(Company company, int index) {
        company.sellStock(index - 1);
        System.out.println("The share is successfully sold.");
    }

    public void showProfit() {
        for (Company company : userPortfolio.getCompanies()) {
            System.out.println("You invested $" + company.getTotalMoneyInvested() + " in " + company.getName() +  ", earning a profit of $" + company.getProfit());
        }
        System.out.println("Overall, you invested $" + userPortfolio.getMoneyInvested() + " and your profit is $" + userPortfolio.getProfit());
    }

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
                    int shareToSell = input.nextInt();
                    input.nextLine();

                    sellShare(currentCompany, shareToSell);
                }
            }
        }
    }

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
                int price = input.nextInt();
                input.nextLine();

                currentCompany.setNewStockPrice(price);
            }
        }
    }

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

}
