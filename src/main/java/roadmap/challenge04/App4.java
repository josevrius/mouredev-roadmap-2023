package roadmap.challenge04;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class App4 {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String HEADER = """
            
            PRIMO, FIBONACCI Y PAR
            ======================""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            int number = enterNumber();
            showResult(number);
        } catch (Exception e) {
            System.out.println("Error ....: " + e.getMessage());
        }
    }

    private static int enterNumber() {
        System.out.print("Número ...: ");
        String number = SCN.nextLine();

        if (!number.matches("\\d+")) {
            throw new InputMismatchException("Introduzca un número entero positivo");
        }
        return Integer.parseInt(number);
    }

    private static void showResult(int number) {
        System.out.println("Resultado : "
                + (isPrime(number) ? "Es primo, " : "No es primo, ")
                + (isFibonacci(number) ? "es fibonacci y " : "no es fibonacci y ")
                + (isEven(number) ? "es par" : "es impar"));
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFibonacci(long number) {
        long exp1 = 5 * number * number + 4;
        long exp2 = 5 * number * number - 4;
        return isPerfectSquare(exp1) || isPerfectSquare(exp2);
    }

    private static boolean isPerfectSquare(long number) {
        long squareRoot = (long) Math.sqrt(number);
        return squareRoot * squareRoot == number;
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
