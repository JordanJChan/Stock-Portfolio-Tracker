package persistence;

import model.Company;
import model.Stock;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkCompany(String name, ArrayList<Stock> listOfStock, int totalMoneyInvested, Company company) {
        assertEquals(name, company.getName());
        assertEquals(totalMoneyInvested, company.getTotalMoneyInvested());
        assertEquals(listOfStock.size(), company.getStocks().size());

    }

    protected void checkStock(int currentPrice, int priceWhenBought, Stock stock) {
        assertEquals(currentPrice, stock.getCurrentPrice());
        assertEquals(priceWhenBought, stock.getPriceWhenBought());
        
    }

}
