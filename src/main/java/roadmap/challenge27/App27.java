package roadmap.challenge27;

import java.util.ArrayList;
import java.util.List;

public final class App27 {

    private static final String HEADER = """
            
            CARÁCTER INFILTRADO
            ===================""";

    public static void main(String[] args) {
        System.out.println(HEADER);
        String text1 = "Hola, Don Pepito";
        String text2 = "Hole, Don Pepita";
        System.out.println("Texto 1 ....: " + text1);
        System.out.println("Texto 2 ....: " + text2);

        try {
            List<Character> unequalChars = findUnequalChars(text1, text2);
            System.out.println("Infiltrados : " + unequalChars);
        } catch (Exception e) {
            System.err.println("Error ......: " + e.getMessage());
        }
    }

    private static List<Character> findUnequalChars(String text, String anotherText) {
        if (text.length() != anotherText.length()) {
            throw new IllegalArgumentException("Los textos no coinciden en longitud");
        }

        List<Character> result = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != anotherText.charAt(i)) {
                result.add(anotherText.charAt(i));
            }
        }
        return result;
    }
}
