package roadmap.challenge29;

import java.text.NumberFormat;

public final class App29 {

    private static final String HEADER = """
            
            EL ÁBACO
            ============""";

    public static void main(String[] args) {
        String[] abacus = {
                "O---OOOOOOOO",
                "OOO---OOOOOO",
                "---OOOOOOOOO",
                "OO---OOOOOOO",
                "OOOOOOO---OO",
                "OOOOOOOOO---",
                "---OOOOOOOOO"
        };

        System.out.println(HEADER);
        show(abacus);
        try {
            int number = getNumber(abacus);
            System.out.println("Resultado .: " + NumberFormat.getNumberInstance().format(number));
        } catch (Exception e) {
            System.err.println("Error .....: " + e.getMessage());
        }
    }

    private static void show(String[] abacus) {
        for (String rod : abacus) {
            System.out.println(rod);
        }
        System.out.println("============");
    }

    private static int getNumber(String[] abacus) {
        if (abacus.length != 7) {
            throw new IllegalArgumentException("Tamaño del ábaco incorrecto");
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < abacus.length; i++) {
            String rod = abacus[i];
            if (!rod.matches("O{0,9}---O{0,9}") || rod.length() != 12) {
                throw new IllegalArgumentException(String.format("Formato incorrecto en la varilla %d ", i + 1));
            }
            result.append(rod.indexOf('-'));
        }
        return Integer.parseInt(result.toString());
    }
}
