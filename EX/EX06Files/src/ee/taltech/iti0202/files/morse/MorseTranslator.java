package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private Map<String, String> morseCodes = new HashMap<>();

    /**
     * @param lines
     * @return Map
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            String character = parts[0].toLowerCase();
            String morseCode = parts[1];
            morseCodes.put(character, morseCode);
        }
        return morseCodes;
    }

    /**
     * @param lines
     * @return List
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> morseLines = new ArrayList<>();
        for (String line : lines) {
            String morseLine = translateLineToMorse(line);
            morseLines.add(morseLine);
        }
        return morseLines;
    }

    /**
     * @param lines
     * @return List
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> textLines = new ArrayList<>();
        for (String line : lines) {
            String textLine = translateLineFromMorse(line);
            textLines.add(textLine);
        }
        return textLines;
    }

    /**
     * @param line
     * @return String
     */
    private String translateLineToMorse(String line) {
        StringBuilder morseLine = new StringBuilder();
        for (char c : line.toLowerCase().toCharArray()) {
            if (c == ' ') {
                morseLine.append("\t");
            } else {
                String morseCode = morseCodes.get(Character.toString(c));
                morseLine.append(morseCode).append(" ");
            }
        }
        return morseLine.toString().trim();

    }


    /**
     * @param line
     * @return String
     */
    private String translateLineFromMorse(String line) {
        StringBuilder textLine = new StringBuilder();
        String[] morseWords = line.split("\t"); // split by tab to get individual words
        for (String morseWord : morseWords) {
            String[] morseLetters = morseWord.split(" "); // split by space to get individual letters
            for (String morseLetter : morseLetters) {
                String character = null;
                for (Map.Entry<String, String> entry : morseCodes.entrySet()) {
                    if (entry.getValue().equals(morseLetter)) {
                        character = entry.getKey();
                        break;
                    }
                }
                if (character != null) {
                    textLine.append(character);
                }
            }
            textLine.append(" "); // add space between words
        }
        return textLine.toString().trim(); // trim leading/trailing whitespace
    }
}