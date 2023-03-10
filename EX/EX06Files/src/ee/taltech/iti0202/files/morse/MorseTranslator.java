package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private Map<String, String> morseCodes = new HashMap<>();

    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split("\\s+", 2);
            morseCodes.put(parts[0].toLowerCase(), parts[1].toLowerCase());
        }
        return morseCodes;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            String translatedLine = translateLineToMorse(line);
            result.add(translatedLine);
        }
        return result;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            String translatedLine = translateLineFromMorse(line);
            result.add(translatedLine.toLowerCase());
        }
        return result;
    }

    public String translateLineToMorse(String line) {
        StringBuilder morseBuilder = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            String letter = Character.toString(line.charAt(i)).toLowerCase();
            String code = morseCodes.get(letter);
            if (code != null) {
                morseBuilder.append(code).append(" ");
            } else {
                morseBuilder.append(" ");
            }
        }
        return morseBuilder.toString().trim().replaceAll(" ", "   ");
    }



    private String translateLineFromMorse(String line) {
        StringBuilder sb = new StringBuilder();
        String[] morseWords = line.split("\t");
        for (String morseWord : morseWords) {
            String[] morseLetters = morseWord.split(" ");
            for (String morseLetter : morseLetters) {
                String letter = morseCodes.get(morseLetter);
                if (letter != null) {
                    sb.append(letter);
                }
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}