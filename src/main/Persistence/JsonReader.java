package persistence;

import model.Company;
import model.Portfolio;
import model.Stock;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads the portfolio from JSON data in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads portfolio from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Portfolio read() throws IOException {
        return null; // stub

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; //stub
    }

    // EFFECTS: parses portfolio from JSON object and returns it
    private Portfolio parsePortfolio(JSONObject jsonObject) {
        return null; // stub
    }



}
