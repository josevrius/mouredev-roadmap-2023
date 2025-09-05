package roadmap.challenge08;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public final class App8 {

    private static final String HEADER = """
            
            HETEROGRAMA, ISOGRAMA Y PANGRAMA
            ================================""";

    public static void main(String[] args) {
        String text1 = "Centrifugado";
        String text2 = "Anna";
        String text3 = "Jovencillo emponzoñado de whisky: ¡qué figurota exhibe!";

        System.out.println(HEADER);
        System.out.println("Texto 1 es un Heterograma : " + (isHeterogram(text1) ? "SI" : "NO"));
        System.out.println("Texto 2 es un Isograma ...: " + (isIsogram(text2) ? "SI" : "NO"));
        System.out.println("Texto 3 es un Pangrama ...: " +  (isPangram(text3) ? "SI" : "NO"));

    }

    private static boolean isHeterogram(String text) {
        HashSet<Character> set = new HashSet<>();
        String normText = normalizeText(text);

        for (char c : normText.toCharArray()) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIsogram(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        String normText = normalizeText(text);

        for (char c : normText.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int matcherValue = map.values().iterator().next();
        for (int value : map.values()) {
            if (value != matcherValue) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPangram(String text) {
        HashSet<Character> set = new HashSet<>();
        String normText = normalizeText(text);

        for (char c : normText.toCharArray()) {
            set.add(c);
        }
        return set.size() == 27;
    }

    private static String normalizeText(String text) {
        final Pattern DIACRITICS = Pattern.compile("\\p{M}");
        final Pattern NOT_ALLOWED_CHARS = Pattern.compile("[^A-Z\001]+");

        String normalized = text.strip().toUpperCase().replace('Ñ', '\001');
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = DIACRITICS.matcher(normalized).replaceAll("");
        normalized = NOT_ALLOWED_CHARS.matcher(normalized).replaceAll("");
        normalized = normalized.replace('\001', 'Ñ');

        return normalized;
    }
}
