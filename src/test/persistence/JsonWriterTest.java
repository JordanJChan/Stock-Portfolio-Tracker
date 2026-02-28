package persistence;

import model.Portfolio;
import model.Company;
import model.Stock;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

}
