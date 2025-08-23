package roadmap.challenge03;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class App {

    private static final Scanner SCN = new Scanner(System.in);
    private static final SecureRandom RND = new SecureRandom();

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 16;
    private static final String LETTERS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    private static final String HEADER = """
            
            GENERAR CONTRASEÑA
            ==================""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            int length = enterLength();
            boolean upp = enterCategory("Mayúsculas : ");
            boolean low = enterCategory("Minúsculas : ");
            boolean num = enterCategory("Números ...: ");
            boolean sym = enterCategory("Símbolos ..: ");
            String password = generatePassword(length, upp, low, num, sym);
            System.out.println("Contraseña : " + password);
        } catch (Exception e) {
            System.out.println("Error .....: " + e.getMessage());
        }
    }

    private static int enterLength() {
        System.out.print("Longitud ..: ");
        String input = SCN.nextLine().strip();

        if (!input.matches("[8-9]|1[0-6]")) {
            throw new IllegalArgumentException("La longitud debe estar entre " + MIN_LENGTH + " y " + MAX_LENGTH);
        }
        return Integer.parseInt(input);
    }

    private static boolean enterCategory(String msg) {
        System.out.print(msg);
        String input = SCN.nextLine().strip().toUpperCase();

        if (!input.matches("SI|NO")) {
            throw new IllegalArgumentException("Introduzca SI / NO");
        }
        return input.equals("SI");
    }

    private static String generatePassword(int length, boolean uppercase, boolean lowercase, boolean number, boolean symbol) {
        if (!uppercase && !lowercase && !number && !symbol) {
            throw new IllegalArgumentException("Seleccione al menos una categoria");
        }

        StringBuilder password = new StringBuilder();
        List<String> selectedCategories = new ArrayList<>();

        if (uppercase) {
            password.append(getRandomChar(LETTERS));
            selectedCategories.add(LETTERS);
        }
        if (lowercase) {
            password.append(getRandomChar(LETTERS.toLowerCase()));
            selectedCategories.add(LETTERS.toLowerCase());
        }
        if (number) {
            password.append(getRandomChar(NUMBERS));
            selectedCategories.add(NUMBERS);
        }
        if (symbol) {
            password.append(getRandomChar(SYMBOLS));
            selectedCategories.add(SYMBOLS);
        }
        for (int i = selectedCategories.size() + 1; i <= length; i++) {
            String category = selectedCategories.get(RND.nextInt(selectedCategories.size()));
            password.append(getRandomChar(category));
        }
        shuffle(password);

        return password.toString();
    }

    private static char getRandomChar(String category) {
        return category.charAt(RND.nextInt(category.length()));
    }

    private static void shuffle(StringBuilder password) {
        for (int i = password.length() - 1; i > 0; i--) {
            int random = RND.nextInt(i + 1);
            char aux = password.charAt(i);
            password.setCharAt(i, password.charAt(random));
            password.setCharAt(random, aux);
        }
    }
}
