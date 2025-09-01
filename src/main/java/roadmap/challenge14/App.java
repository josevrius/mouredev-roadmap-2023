package roadmap.challenge14;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class App {

    private static final String HEADER = """
            
            AUREBESH TRANSLATOR
            ===================""";

    private static final Map<String, String> ALPHABET = Map.ofEntries(
            Map.entry("A", "AUREK"), Map.entry("B", "BESH"), Map.entry("C", "CRESH"),
            Map.entry("D", "DORN"), Map.entry("E", "ESK"), Map.entry("F", "FORN"),
            Map.entry("G", "GREK"), Map.entry("H", "HERF"), Map.entry("I", "ISK"),
            Map.entry("J", "JENTH"), Map.entry("K", "KRILL"), Map.entry("L", "LETH"),
            Map.entry("M", "MERN"), Map.entry("N", "NERN"), Map.entry("O", "OSK"),
            Map.entry("P", "PETH"), Map.entry("Q", "QEK"), Map.entry("R", "RESH"),
            Map.entry("S", "SENTH"), Map.entry("T", "TRILL"), Map.entry("U", "USK"),
            Map.entry("V", "VEV"), Map.entry("W", "WESK"), Map.entry("X", "XESH"),
            Map.entry("Y", "YIRT"), Map.entry("Z", "ZEREK"), Map.entry("CH", "CHEREK"),
            Map.entry("AE", "ENTH"), Map.entry("EO", "ONITH"), Map.entry("KH", "KRENTH"),
            Map.entry("NG", "NEN"), Map.entry("OO", "ORENTH"), Map.entry("SH", "SEN"),
            Map.entry("TH", "THESH")
    );

    private enum Translator {
        AUREBESH, SPANISH
    }

    public static void main(String[] args) {
        String text = "¡Hola mundo!";
        String aurebesh = translate(text, Translator.AUREBESH);
        String spanish = translate(aurebesh, Translator.SPANISH);

        System.out.println(HEADER);
        System.out.println("Texto ...: " + text);
        System.out.println("Aurebesh : " + aurebesh);
        System.out.println("Español .: " + spanish);
    }

    private static String translate(String text, Translator translator) {
        StringBuilder translatedText = new StringBuilder();
        String normText = normalizeText(text);

        switch (translator) {
            case AUREBESH -> {
                for (int i = 0; i < normText.length(); i++) {
                    String compoundChar = i + 1 < normText.length() ? normText.substring(i, i + 2) : "";
                    String simpleChar = normText.substring(i, i + 1);

                    if (ALPHABET.containsKey(compoundChar)) {
                        translatedText.append(ALPHABET.get(compoundChar));
                        if (i < normText.length() - 1) translatedText.append(" ");
                        i++;
                    } else if (ALPHABET.containsKey(simpleChar)) {
                        translatedText.append(ALPHABET.get(simpleChar));
                        if (i < normText.length() - 1) translatedText.append(" ");
                    } else {
                        translatedText.append(normText.charAt(i));
                    }
                }
            }
            case SPANISH -> {
                Map<String, String> reversedMap = new HashMap<>();
                ALPHABET.forEach((key, value) -> reversedMap.put(value, key));

                for (String word : text.split("\\s")) {
                    translatedText.append(reversedMap.getOrDefault(word, " "));
                }
            }
        }
        return translatedText.toString();
    }

    private static String normalizeText(String text) {
        final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        final Pattern NOT_ALLOWED_CHARS = Pattern.compile("[^A-Z\\d\\s]+");

        String normalized = text.toUpperCase();
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = DIACRITICS.matcher(normalized).replaceAll("");
        normalized = NOT_ALLOWED_CHARS.matcher(normalized).replaceAll("");

        return normalized;
    }
}
