package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        portfolio.addCompany(company1);
        assertEquals(1, portfolio.getCompanies().size());
        assertEquals(0, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());

        company1.buyStock(stock1);
        assertEquals(1, portfolio.getCompanies().size());
        assertEquals(100, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());

        stock1.setCurrentPrice(150);
        assertEquals(50, portfolio.getProfit());

        portfolio.addCompany(company1);
        assertEquals(2, portfolio.getCompanies().size());
        assertEquals(100, portfolio.getMoneyInvested());
        assertEquals(0, portfolio.getProfit());
    }

}
