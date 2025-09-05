package roadmap.challenge15;

public final class App15 {

    private static final String HEADER = """
            
            LA ESCALERA
            ===========""";

    public static void main(String[] args) {
        System.out.println(HEADER);
        drawStair(5);
    }

    private static void drawStair(int steps) {
        if (steps > 0) {
            System.out.println(" ".repeat(steps * 2) + "__");
            for (int i = 1; i <= steps; i++) {
                System.out.print(" ".repeat((steps * 2) - (i * 2)));
                System.out.println("_|");
            }
        } else if (steps < 0) {
            int absSteps = Math.abs(steps);
            System.out.println("__");
            for (int i = absSteps; i >= 1; i--) {
                System.out.print(" ".repeat((absSteps * 2) - (i * 2) + 2));
                System.out.println("|_");
            }
        } else {
            System.out.println("__");
        }
    }
}
