package roadmap.challenge11;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class App {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String HEADER = """
            
            VIERNES 13
            ==========""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            int m = enterValue("Mes ..: ", "Mes incorrecto", "0*[1-9]|1[0-2]");
            int y = enterValue("Año ..: ", "Año incorrecto", "\\d{1,4}");
            boolean thereIsFriday13 = checkFriday13(y, m);
            System.out.println("💀 ...: " + (thereIsFriday13 ? "Si" : "No") + " hay viernes 13");
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    private static int enterValue(String msgUsr, String msgErr, String regex) {
        Pattern pattern = Pattern.compile(regex);
        System.out.print(msgUsr);
        String input = SCN.nextLine();

        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(msgErr);
        }
        return Integer.parseInt(input);
    }

    private static boolean checkFriday13(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 13);
        return date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
