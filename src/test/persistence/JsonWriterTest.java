package persistence;

import model.Portfolio;
import model.Company;
import model.Stock;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;



@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {
    
    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyPortfolio() {
        try {
            Portfolio portfolio = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPortfolio.json");
            portfolio = reader.read();
            assertEquals(0, portfolio.getMoneyInvested());
            assertEquals(0, portfolio.getProfit());
            assertEquals(0, portfolio.getCompanies().size());

        } catch (IOException e) {
            fail("Should not have failed");
        }
    }

    @Test
    public void testWriterGeneralPortfolio() {
        try {
            Portfolio portfolio = new Portfolio();
            Company company1 = new Company("Tesla");
            Company company2 = new Company("Nvidia");
            assertTrue(portfolio.addCompany(company1));
            assertFalse(portfolio.addCompany(company1));
            assertTrue(portfolio.addCompany(company2));
            company1.buyStock(new Stock(200));
            company1.buyStock(new Stock(200));
            company1.buyStock(new Stock(200));
            assertEquals(0, company1.sellStock(2));
            company1.setNewStockPrice(300);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.json");
            portfolio = reader.read();
            assertEquals(400, portfolio.getMoneyInvested());
            assertEquals(200, portfolio.getProfit());
            assertEquals(2, portfolio.getCompanies().size());

            company1 = portfolio.getCompanies().get(0);
            company2 = portfolio.getCompanies().get(1);
            checkCompany("Tesla", company1.getStocks(), 400, company1);
            checkStock(300, 200, company1.getStocks().get(0));
            checkStock(300, 200, company1.getStocks().get(1));
            checkCompany("Nvidia", company2.getStocks(), 0, company2);

        } catch (IOException e) {
            fail("Exception should not have occurred");
        }
    }

}
