package roadmap.challenge13;

public final class App {

    private static final String HEADER = """
            
            CONVERSIÓN NUMÉRICA
            ===================""";

    public static void main(String[] args) {
        int number = 699;
        String octal = decimalToOctal(number);
        String hexad = decimalToHexadecimal(number);

        System.out.println(HEADER);
        System.out.println("Decimal ....: " + number);
        System.out.println("Octal ......: " + octal);
        System.out.println("Hexadecimal : " + hexad);
    }

    private static String decimalToOctal(int number) {
        StringBuilder result = new StringBuilder();

        do {
            int remainder = number % 8;
            number /= 8;
            result.append(remainder);
        } while (number != 0);

        return result.reverse().toString();
    }

    private static String decimalToHexadecimal(int number) {
        final String HEX_VALUES = "0123456789ABCDEF";

        StringBuilder result = new StringBuilder();

        do {
            int remainder = number % 16;
            number /= 16;
            result.append(HEX_VALUES.charAt(remainder));
        } while (number != 0);

        return result.reverse().toString();
    }
}
