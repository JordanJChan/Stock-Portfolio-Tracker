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
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        try {
            Portfolio portfolio = reader.read();
            assertEquals(400, portfolio.getMoneyInvested());
            assertEquals(0, portfolio.getProfit());

            ArrayList<Company> companies = portfolio.getCompanies();
            Company company1 = companies.get(0);
            assertEquals(1, companies.size());
            checkCompany("Tesla", company1.getStocks(), 400, company1);

            checkStock(200, 200, company1.getStocks().get(0));
            checkStock(200, 200, company1.getStocks().get(1));


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
