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
        StringBuilder sb = new StringBuilder();
        String[] res = line.toLowerCase().split(" ");
        for (String word : res) {
            for (char c : word.toCharArray()) {
                String morseWord = morseCodes.get(String.valueOf(c));
                sb.append(morseWord);
                sb.append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\t");
        }
        return sb.toString().trim();
    }


    /**
     * @param line
     * @return String
     */
    private String translateLineFromMorse(String line) {
        StringBuilder textLine = new StringBuilder();
        String[] morseWords = line.split("\t");
        for (String morseWord : morseWords) {
            String[] morseLetters = morseWord.split(" ");
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
            textLine.append(" ");
        }
        return textLine.toString().trim();
    }

    public static void main(String[] args) {
        MorseTranslator morse1 = new MorseTranslator();
        List<String> list1 = morse1.translateLinesToMorse(List.of("Mis teed", "Ago püüton"));
        System.out.println(list1);
    }


}
