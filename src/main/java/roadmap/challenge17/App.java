package roadmap.challenge17;

import java.util.ArrayList;
import java.util.List;

public final class App {

    private static final String HEADER = """
            
            ANÁLISIS DE TEXTO
            =================
            """;

    public static void main(String[] args) {
        String text = """
                En un lugar de la Mancha, de cuyo nombre no quiero acordarme,
                no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero,
                adarga antigua, rocín flaco y galgo corredor.
                Una olla de algo más vaca que carnero...""";

        System.out.println(HEADER + text);
        analyzeText(text);
    }

    private static void analyzeText(String text) {
        String normText = normalizeText(text);

        int wordsCount = 0;
        int wordsLengthMean = 0;
        int sentencesCount = 0;
        int maxLength = 0;
        List<String> longestWords = new ArrayList<>();

        for (String word : normText.split(" ")) {
            if (word.equals(".")) {
                sentencesCount++;
            } else {
                if (!word.isEmpty()) {
                    wordsCount++;
                    wordsLengthMean += word.length();
                    if (word.length() > maxLength) {
                        maxLength = word.length();
                        longestWords.clear();
                        longestWords.add(word);
                    } else if (word.length() == maxLength) {
                        longestWords.add(word);
                    }
                }
            }
        }
        if (wordsCount > 0) {
            wordsLengthMean /= wordsCount;
        }
        System.out.println();
        System.out.println("Palabras ...............: " + wordsCount);
        System.out.println("Longitud media .........: " + wordsLengthMean);
        System.out.println("Oraciones ..............: " + sentencesCount);
        System.out.println("Palabra(s) más larga(s) : " + longestWords);
    }

    private static String normalizeText(String text) {
        String normText = text.strip().toLowerCase()
                .replaceAll("\\d", "")
                .replaceAll("[^a-zñáéíóúü.]", " ")
                .replaceAll("\\.+", " . ")
                .replaceAll("\\s+", " ")
                .replaceAll("\\n", " ")
                .strip();

        if (normText.charAt(normText.length() - 1) != '.') {
            normText += " .";
        }
        return normText;
    }
}
