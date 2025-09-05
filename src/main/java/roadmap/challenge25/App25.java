package roadmap.challenge25;

public final class App25 {

    private static final String HEADER = """
            
            CUENTA ATRÁS
            ============""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            countdown(10, 1);
        } catch (InterruptedException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void countdown(int start, int interval) throws InterruptedException {
        for (int i = start; i >= 0; i--) {
            System.out.println(i);
            Thread.sleep(interval * 1000L);
        }
    }
}
