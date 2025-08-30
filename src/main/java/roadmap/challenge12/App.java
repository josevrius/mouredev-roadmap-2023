package roadmap.challenge12;

import java.util.*;
import java.util.stream.IntStream;

public final class App {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String HEADER = """
            
            ADIVINA LA PALABRA (Tienes %d intentos)
            ==================
            """;

    private static final String WORD = "mandocleta";
    private static final int MAX_ATTEMPTS = 3;

    private static int percentToHide = 60;

    public static void main(String[] args) {
        List<Integer> hiddenChars = getIndexes();

        int attempt = 1;
        boolean guessOK;
        System.out.printf(HEADER, MAX_ATTEMPTS);
        do {
            showWord(hiddenChars);
            guessOK = guessWord(hiddenChars, attempt);
            if (!guessOK) attempt++;
        } while (!guessOK && attempt <= MAX_ATTEMPTS);

        if (guessOK) {
            hiddenChars.clear();
            showWord(hiddenChars);
            System.out.println();
            System.out.println("Enhorabuena 🎉 Has acertado la palabra!");
        } else {
            showWord(hiddenChars);
            System.out.println();
            System.out.println("Te has quedado sin intentos");
            System.out.println("La palabra oculta era: " + WORD.toUpperCase());
        }
    }

    private static List<Integer> getIndexes() {
        if (percentToHide < 0) percentToHide = 0;
        else if (percentToHide > 60) percentToHide = 60;

        List<Integer> hiddenChars = new ArrayList<>(IntStream.rangeClosed(0, WORD.length() - 1).boxed().toList());
        Collections.shuffle(hiddenChars);

        return new ArrayList<>(hiddenChars.subList(0, WORD.length() * percentToHide / 100).stream().sorted().toList());
    }

    private static void showWord(List<Integer> hiddenChars) {
        System.out.print("Palabra ..: ");

        for (int i = 0; i < WORD.length(); i++) {
            System.out.print(hiddenChars.contains(i) ? "_" : WORD.toUpperCase().charAt(i));
            System.out.print(i < WORD.length() - 1 ? " " : "");
        }
        System.out.println();
    }

    private static boolean guessWord(List<Integer> hiddenChars, int attempt) {
        System.out.printf("Intento %d : ", attempt);
        String input = SCN.nextLine().strip();

        if (input.equalsIgnoreCase(WORD)) {
            return true;
        } else {
            for (int i = 0; i < WORD.length(); i++) {
                if (input.equalsIgnoreCase(String.valueOf(WORD.charAt(i)))) {
                    hiddenChars.remove(Integer.valueOf(i));
                }
            }
        }
        return hiddenChars.isEmpty();
    }
}
