package roadmap.challenge19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class App19 {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String HEADER = """
            
            NÚMEROS PRIMOS GEMELOS
            ======================""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            int range = enterRange();
            List<int[]> twinsList = getPrimeTwins(range);
            showTwins(twinsList);
        } catch (Exception e) {
            System.err.println("Error ..: " + e.getMessage());
        }
    }

    private static int enterRange() {
        Pattern validInput = Pattern.compile("[1-9]\\d*");

        System.out.print("Rango ..: ");
        String input = SCN.nextLine();

        if (!validInput.matcher(input).matches()) {
            throw new IllegalArgumentException("El rango debe ser un número entero positivo");
        }
        return Integer.parseInt(input);
    }

    private static List<int[]> getPrimeTwins(int range) {
        List<int[]> primeTwins = new ArrayList<>();

        for (int i = 2; i <= range - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                primeTwins.add(new int[]{i, i + 2});
            }
        }
        return primeTwins;
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void showTwins(List<int[]> primeTwins) {
        System.out.print("Gemelos : ");

        if (primeTwins.isEmpty()) {
            System.out.println("Sin resultados");
        } else {
            for (int[] twins : primeTwins) {
                System.out.print(Arrays.toString(twins) + " ");
            }
            System.out.println();
        }
    }
}
