package roadmap.challenge28;

import java.util.Map;

public final class App28 {

    private static final Map<String, Character> T9_CODE = Map.ofEntries(
            Map.entry("0", ' '),
            Map.entry("1", '.'), Map.entry("11", ','), Map.entry("111", '?'), Map.entry("1111", '!'),
            Map.entry("2", 'A'), Map.entry("22", 'B'), Map.entry("222", 'C'),
            Map.entry("3", 'D'), Map.entry("33", 'E'), Map.entry("333", 'F'),
            Map.entry("4", 'G'), Map.entry("44", 'H'), Map.entry("444", 'I'),
            Map.entry("5", 'J'), Map.entry("55", 'K'), Map.entry("555", 'L'),
            Map.entry("6", 'M'), Map.entry("66", 'N'), Map.entry("666", 'O'),
            Map.entry("7", 'P'), Map.entry("77", 'Q'), Map.entry("777", 'R'), Map.entry("7777", 'S'),
            Map.entry("8", 'T'), Map.entry("88", 'U'), Map.entry("888", 'V'),
            Map.entry("9", 'W'), Map.entry("99", 'X'), Map.entry("999", 'Y'), Map.entry("9999", 'Z')
    );

    private static final String HEADER = """
            
            TECLADO T9
            ==========""";

    public static void main(String[] args) {
        String sequence = "44-666-555-2-0-6-88-66-3-666-1111";
        String text = decode(sequence);

        System.out.println(HEADER);
        System.out.println("Secuencia : " + sequence);
        System.out.println("Texto ....: " + text);
    }

    private static String decode(String sequence) {
        StringBuilder decodedText = new StringBuilder();
        String[] tokens = sequence.split("-");

        for (String token : tokens) {
            if (T9_CODE.containsKey(token)) {
                decodedText.append(T9_CODE.get(token));
            } else {
                decodedText.append('?');
            }
        }
        return decodedText.toString();
    }
}
