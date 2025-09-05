package roadmap.challenge01;

import java.text.Normalizer;
import java.util.Map;
import java.util.regex.Pattern;

public final class App1 {

    private static final Map<Character, String> LEET_ALPHABET = Map.ofEntries(
            Map.entry('A', "4"), Map.entry('B', "I3"), Map.entry('C', "["),
            Map.entry('D', ")"), Map.entry('E', "3"), Map.entry('F', "|="),
            Map.entry('G', "6"), Map.entry('H', "#"), Map.entry('I', "1"),
            Map.entry('J', "_|"), Map.entry('K', ">|"), Map.entry('L', "|_"),
            Map.entry('M', "/\\/\\"), Map.entry('N', "^/"), Map.entry('O', "0"),
            Map.entry('P', "|*"), Map.entry('Q', "(_,)"), Map.entry('R', "I2"),
            Map.entry('S', "5"), Map.entry('T', "7"), Map.entry('U', "(_)"),
            Map.entry('V', "\\/"), Map.entry('W', "\\/\\/"), Map.entry('X', "><"),
            Map.entry('Y', "`/"), Map.entry('Z', "2"), Map.entry('1', "L"),
            Map.entry('2', "R"), Map.entry('3', "E"), Map.entry('4', "A"),
            Map.entry('5', "S"), Map.entry('6', "b"), Map.entry('7', "T"),
            Map.entry('8', "B"), Map.entry('9', "g"), Map.entry('0', "o")
    );

    private static final String HEADER = """
            
            LENGUAJE HACKER
            ===============""";

    public static void main(String[] args) {
        String originalText = "If you can read this, you really need to get a life!";
        String encodedText = encodeToLeet(originalText);

        System.out.println(HEADER);
        System.out.println("Texto : " + originalText);
        System.out.println("Leet .: " + encodedText);
    }

    private static String encodeToLeet(String text) {
        StringBuilder result = new StringBuilder();
        String normalizedText = normalize(text);

        for (char i : normalizedText.toCharArray()) {
            result.append(LEET_ALPHABET.getOrDefault(Character.toUpperCase(i), String.valueOf(i)));
        }
        return result.toString();
    }

    private static String normalize(String text) {
        final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        String normalized = text.replace('Ñ', '\001').replace('ñ', '\002');
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = DIACRITICS.matcher(normalized).replaceAll("");
        normalized = normalized.replace('\001', 'Ñ').replace('\002', 'ñ');

        return normalized;
    }
}