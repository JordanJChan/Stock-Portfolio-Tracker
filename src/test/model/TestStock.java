package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStock {
    private Stock stock;

    @BeforeEach
    void runBefore() {
        stock = new Stock(100);
    }

    @Test
    void testConstructor() {
        assertEquals(0, stock.getCurrentPrice());
        assertEquals(0, stock.getPriceWhenBought());
    }

    @Test
    void testGetProfit() {
        stock.setCurrentPrice(150);
        assertEquals(50, stock.getProfit());

        stock.setCurrentPrice(70);
        assertEquals(-30, stock.getProfit());
    }

    
}
