# My Personal Project

## Overview
My project will be a simple **Stock Portfolio Tracker** that allows the user to keep track of all the stocks they own. They can add companies to their portfolio along with the number of stocks and price they brought it at. The user can see how much money they are earning/losing and buy/sell stocks. Additionally, the user can manually update the current price of the stock to see their current earning/losses. The people who use this program can be people who own stocks and would like another way to keep track of it. It could also be for people who are interested in the stock market, so they could use this program as a simulation to practice investing. The reason why this project interests me is because I would like to invest money into the stock market in the future when I start making money. The program allows me to learn how to invest by acting as a simulation to the stock market with my own portfolio. 

## User Stories
- As a user, I want to add a company that I own stocks in to my portfolio.
- As a user, I want to view the list of companies I own shares in.
- As a user, I want to add some shares to a company.
- As a user, I want to sell some shares from a company.
- As a user, I want to view the shares in a company that I own. 
- As a user, I want to update the current stock price of a company to calculate how much money I am earning or losing.
- As a user, I want to remove a company from my portfolio.
- As a user, I want to have the option to save the companies and stocks in the portfolio in a file.
- As a user, I want to have the option to load the companies and stocks from the file.

# Instructions for End User
- You can view the panel that displays the Xs that have already been added to the Y by looking at the big white space on the screen.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by entering the name of the company, number of shares, and price then click "Add Company" to add it to portfolio
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by entering the name of the company to remove then click "Remove Company" button
- You can locate my visual component by clicking the button that says "Display Image" and it will pop up in a new window.
- You can save the state of my application by clicking the button that says "Save Data"
- You can reload the state of my application by clicking the button that says "Load Data"



# Phase 4: Task 2
* Sun Mar 29 15:20:56 PDT 2026
* Tesla added to portfolio with some shares.
* Sun Mar 29 15:21:04 PDT 2026
* Nvidia added to portfolio with some shares.
* Sun Mar 29 15:21:15 PDT 2026
* More shares were added to Tesla
* Sun Mar 29 15:21:21 PDT 2026
* Nvidia was removed from portfolio.

# Phase 4: Task 3
One change I would make to my design is that I would remove the `Company` class to reduce coupling and simplify the design. This is because I can add more fields to the `Stock` class to include the company's information instead of having a separate class for it. It would reduce the hierarchy from `Portfolio` containing `Company` containing `Stock` to just `Portfolio` containing `Stock` which makes the design less complicated. I would need less for loops to display the data because I do not have to loop over each company when looping through the data in the `Portfolio` class. I only need to loop over the stocks in this new design. Thus, I would get rid of the `Company` class and add more fields to the `Stock` class to reduce coupling and simplify the class hierarchy. 

Another change I would make to my design is to have more GUI classes. This would increase cohesion where I can have one class that controls the main functions of the GUI and another that creates the buttons and text boxes. It makes it so the class is responsible for one task which makes it easier to maintain and understand how it works. 
