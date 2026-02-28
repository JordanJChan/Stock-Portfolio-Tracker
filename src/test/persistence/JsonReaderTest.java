package persistence;

import model.Portfolio;
import model.Company;
import model.Stock;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest{
    
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/FileDoesNotExist.json");
        try {
            Portfolio p = reader.read();
            fail("File does not exist, so it shoud not have pass");
        } catch (IOException e) {

        }
    }

    @Test
    public void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        try {
            Portfolio p = reader.read();
            assertEquals(0, p.getMoneyInvested());
            assertEquals(0, p.getProfit());
            assertEquals(0, p.getCompanies().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        
    }



}
