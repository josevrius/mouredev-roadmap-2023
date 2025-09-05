package roadmap.challenge22;

public final class App22 {

    private static final String HEADER = """
            
            CIFRADO CÉSAR
            =============""";

    private static final String ALPHABET = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private static final String DIACRITICS = "ÁÉÍÓÚÜ";
    private static final String DIGITS = "0123456789";

    private enum Method {
        ENCRYPT, DECRYPT
    }

    public static void main(String[] args) {
        String originalText = "¡El ataque comenzará las 20:45!";

        String encryptedText = processText(originalText, Method.ENCRYPT, 3);
        String decryptedText = processText(encryptedText, Method.DECRYPT, 3);

        System.out.println(HEADER);
        System.out.println("Texto .....: " + originalText);
        System.out.println("Cifrado ...: " + encryptedText);
        System.out.println("Descifrado : " + decryptedText);
    }

    private static String processText(String text, Method method, int offset) {
        StringBuilder result = new StringBuilder();

        if (method == Method.DECRYPT) {
            offset = -offset;
        }
        for (char c : text.toUpperCase().toCharArray()) {
            if (ALPHABET.indexOf(c) >= 0) {
                result.append(shift(ALPHABET, c, offset));
            } else if (DIACRITICS.indexOf(c) >= 0) {
                result.append(shift(DIACRITICS, c, offset));
            } else if (DIGITS.indexOf(c) >= 0) {
                result.append(shift(DIGITS, c, offset));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static char shift(String pool, char c, int offset) {
        int index = pool.indexOf(c);
        return pool.charAt((index + offset + pool.length()) % pool.length());
    }
}
