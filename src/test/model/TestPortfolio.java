package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestPortfolio {
    private Portfolio portfolio;
    private Company company1;
    private Company company2;
    private Stock stock1;

    @BeforeEach
    void runBefore() {
        portfolio = new Portfolio();
        company1 = new Company("C1");
        company2 = new Company("C2");

        stock1 = new Stock(100);
    }

    @Test
    void testConstructor() {
        assertEquals(0, portfolio.getCompanies().size());
        assertEquals(0, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());
    }

    @Test
    void testAddCompany() {
        assertTrue(portfolio.addCompany(company1));
        assertEquals(1, portfolio.getCompanies().size());
        assertEquals(0, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());

        assertFalse(portfolio.addCompany(company1));
        assertEquals(1, portfolio.getCompanies().size());
        assertEquals(0, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());

        company1.buyStock(stock1);
        assertEquals(1, portfolio.getCompanies().size());
        assertEquals(100, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());

        stock1.setCurrentPrice(150);
        assertEquals(50, portfolio.getProfit());

        assertTrue(portfolio.addCompany(company2));
        assertEquals(2, portfolio.getCompanies().size());
        assertEquals(100, portfolio.getMoneyInvested());
        assertEquals(50, portfolio.getProfit());
    }

    @Test
    void testGetSummaryText() {
        company1.buyStock(stock1);
        stock1.setCurrentPrice(150);
        portfolio.addCompany(company1);

        String summary = portfolio.getSummaryText();

        assertTrue(summary.contains("Portfolio Summary"));
        assertTrue(summary.contains("Companies: 1"));
        assertTrue(summary.contains("Total Shares: 1"));
        assertTrue(summary.contains("Invested: $100"));
        assertTrue(summary.contains("Profit: $50"));
    }

}
