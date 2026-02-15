package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompany {
    private Company company;
    private Stock stock1;
    private Stock stock2;

    @BeforeEach
    void runBefore() {
        company = new Company("C1");
        stock1 = new Stock(100);
        stock2 = new Stock(50);
    }

    @Test
    void testConstructor() {
        assertEquals("C1", company.getName());
        assertEquals(0, company.getNumberOfStocks());
        assertEquals(0, company.getTotalMoneyInvested());
    }

    @Test
    void testBuyStock() {
        company.buyStock(stock1);
        assertEquals(1, company.getNumberOfStocks());
        assertEquals(100, company.getTotalMoneyInvested());

        company.buyStock(stock2);
        assertEquals(2, company.getNumberOfStocks());
        assertEquals(150, company.getTotalMoneyInvested());
    }

    @Test
    void testSellStock() {
        company.buyStock(stock1);
        company.buyStock(stock2);
        stock1.setCurrentPrice(110);
        assertEquals(10, company.sellStock(0));
        assertEquals(1, company.getNumberOfStocks());

        stock2.setCurrentPrice(25);
        assertEquals(-25, company.sellStock(0));
        assertEquals(0, company.getNumberOfStocks());
    }
}
