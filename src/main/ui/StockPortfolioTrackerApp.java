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
        userPortfolio = new Portfolio();
        input = new Scanner(System.in);

        boolean running = true;

        System.out.println("Welcome to Stock Portfolio Tracker.");

        while (running) {
            displayMenu();

            System.out.print("Enter your choice number: ");
            userNumberInput = input.nextInt();
            input.nextLine();


            if (userNumberInput == 1) { // Add company to Portfolio
                System.out.print("What is the company's name? ");
                userStringInput = input.nextLine();
                boolean addStatus = userPortfolio.addCompany(new Company(userStringInput));
                if (addStatus) {
                    System.out.println(userStringInput + " is successfully added.");
                } else {
                    System.out.println(userStringInput + " is already in your portfolio.");
                }
            } else if (userNumberInput == 2) { // View companies in portfolio
                displayCompanies();
            } else if (userNumberInput == 3) { // Buy more shares in a company
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

                    addShares(userPortfolio.getCompanies().get(userNumberInput-1), numberOfShares, price);
                }
            } else if (userNumberInput == 4) { // Sell shares in a company
                if (userPortfolio.getCompanies().size() == 0) {
                    System.out.println("You have not added any companies. Please add a company first.");
                } else {
                    displayCompanies();
                    System.out.print("Enter the company number you want to sell shares in: ");
                    userNumberInput = input.nextInt();
                    input.nextLine();

                    Company currentCompany = userPortfolio.getCompanies().get(userNumberInput-1);

                    if (currentCompany.getNumberOfStocks() == 0) {
                        System.out.println("You have no shares in this company.");
                    } else {
                        displayShares(currentCompany);
                        int shareToSell = input.nextInt();
                        input.nextLine();

                        sellShare(currentCompany, shareToSell);
                    }


                }
            } else if (userNumberInput == 5) { // Update a company's stock price

            } else if (userNumberInput == 6) { // View profits

            } else if (userNumberInput == 7) { // Quit
                System.out.println("Done");
                running = false;
            } else {
                System.out.println("Please enter a number from 1-7");
            }
        }
    }

    public void displayMenu() {
        System.out.println("\n");
        System.out.println("\t1. Add a company into portfolio");
        System.out.println("\t2. View companies in portfolio");
        System.out.println("\t3. Buy more shares in a company");
        System.out.println("\t4. Sell shares in a company");
        System.out.println("\t5. Update a company's stock price");
        System.out.println("\t6. View profits");
        System.out.println("\t7. Quit");
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
        for (int i=0; i<numberShares; i++) {
            company.buyStock(new Stock(price));
        }
    }

    public void displayShares(Company company) {
        int index = 1;
        for (Stock share : company.getStocks()) {
            System.out.println(index + ". A stock bought at $" + share.getPriceWhenBought() + ". A profit of $" + share.getProfit());
            index++;
        }
    }

    public void sellShare(Company company, int index) {
        company.sellStock(index-1);
    }

}
