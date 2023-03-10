package ee.taltech.iti0202.files.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> result = new ArrayList<>();
        Path path = Paths.get("path", "to", filename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                result.add(line);
                if (line == null) break;
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}