package roadmap.challenge02;

public final class App {

    private enum Player {P1, P2}

    private static final String[] EARLY_SCORES = {"Love", "15", "30", "40"};

    private static final String HEADER = """
            
            PARTIDO DE TENIS
            ================""";

    public static void main(String[] args) {
        Player[] sequence = {Player.P2, Player.P2, Player.P1, Player.P1, Player.P2, Player.P1, null, Player.P2, Player.P2};

        try {
            System.out.println(HEADER);
            showGame(sequence);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void showGame(Player[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new NullPointerException("La secuencia de puntos no es válida");
        }

        int p1Score = 0;
        int p2Score = 0;
        boolean endGame = false;
        int i = 0;

        do {
            if (sequence[i] != null) {
                switch (sequence[i]) {
                    case P1 -> p1Score++;
                    case P2 -> p2Score++;
                    default -> throw new IllegalArgumentException("Jugador no permitido (" + sequence[i] + ")");
                }
                if (p1Score >= 3 && p1Score == p2Score) {
                    System.out.println("Deuce");
                } else if (p1Score > 3 && p1Score == p2Score + 1) {
                    System.out.println("Ventaja P1");
                } else if (p2Score > 3 && p2Score == p1Score + 1) {
                    System.out.println("Ventaja P2");
                } else if (p1Score > 3 && p1Score >= p2Score + 2) {
                    System.out.println("Ha ganado P1");
                    endGame = true;
                } else if (p2Score > 3 && p2Score >= p1Score + 2) {
                    System.out.println("Ha ganado P2");
                    endGame = true;
                } else {
                    System.out.println(EARLY_SCORES[p1Score] + " - " + EARLY_SCORES[p2Score]);
                }
            }
            i++;
            if (!endGame && i == sequence.length) {
                System.out.println("El juego termina sin ganador");
                endGame = true;
            }
        } while (!endGame);
    }
}
