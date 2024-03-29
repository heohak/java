package ee.taltech.iti0202.files.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilesWriter {

    /**
     *
     * @param lines
     * @param filename
     * @return boolean
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        if (filename == null) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
