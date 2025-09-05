package roadmap.challenge07;

public final class App7 {

    private static final String HEADER = """
            
            GENERADOR PSEUDOALEATORIO
            =========================
            Número :\s""";

    public static void main(String[] args) {
        long number = System.currentTimeMillis() % 101;

        System.out.print(HEADER);
        System.out.println(number);
    }
}
