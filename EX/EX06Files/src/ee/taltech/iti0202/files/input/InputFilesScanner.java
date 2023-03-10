package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> result = new ArrayList<>();
        Path path = Paths.get("path", "to", filename);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}