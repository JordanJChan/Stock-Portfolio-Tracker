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


            if (userNumberInput == 1) {
                
            } else if (userNumberInput == 2) {

            } else if (userNumberInput == 3) {

            } else if (userNumberInput == 4) {

            } else if (userNumberInput == 5) {

            } else if (userNumberInput == 6) {

            } else if (userNumberInput == 7) {
                System.out.println("Done");
                running = false;
            } else {
                System.out.println("Please enter a number from 1-7");
            }
        }
    }

    public void displayMenu() {
        System.out.println("1. Add a company into portfolio");
        System.out.println("2. View companies in portfolio");
        System.out.println("3. Buy more shares in a company");
        System.out.println("4. Sell shares in a company");
        System.out.println("5. Update a company's stock price");
        System.out.println("6. View profits");
        System.out.println("7. Quit");
    }

}
