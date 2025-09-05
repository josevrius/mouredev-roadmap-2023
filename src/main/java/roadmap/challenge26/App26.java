package roadmap.challenge26;

import java.util.regex.Pattern;

public final class App26 {

    private static final String HEADER = """
            
            EXPRESIÓN MATEMÁTICA
            ====================""";

    public static void main(String[] args) {
        System.out.println(HEADER);
        String exp1 = "-5 + 4 / 32.5 * 7 - -10";
        String exp2 = "-5 + 4 / 32.5 * 7 -10";
        System.out.println(exp1 + " : " + (isValid(exp1) ? "Correcta" : "Incorrecta"));
        System.out.println(exp2 + " : " + (isValid(exp2) ? "Correcta" : "Incorrecta"));
    }

    private static boolean isValid(String expression) {
        Pattern regex = Pattern.compile("[+-]?\\d+(\\.\\d+)?(\\s[-+*/%]\\s[+-]?\\d+(\\.\\d+)?)+");
        return regex.matcher(expression).matches();
    }
}
