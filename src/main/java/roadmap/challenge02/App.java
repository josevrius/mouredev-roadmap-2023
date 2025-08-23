package roadmap.challenge02;

import java.util.Map;

public final class App {

    private enum Player {
        P1, P2
    }

    private static final Map<Integer, String> EARLY_SCORES = Map.of(
            0, "Love",
            1, "15",
            2, "30",
            3, "40"
    );

    private static final String HEADER = """
            
            PARTIDO DE TENIS
            ================""";

    public static void main(String[] args) {
        Player[] sequencePoints = {Player.P2, Player.P2, Player.P1, Player.P1, Player.P2, Player.P1, null, Player.P2, Player.P2};

        try {
            System.out.println(HEADER);
            showGame(sequencePoints);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void showGame(Player[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new NullPointerException("La secuencia de puntos no es válida");
        }

        int p1Count = 0;
        int p2Count = 0;
        boolean endGame = false;
        int i = 0;

        do {
            if (sequence[i] != null) {
                switch (sequence[i]) {
                    case P1 -> p1Count++;
                    case P2 -> p2Count++;
                    default -> throw new IllegalArgumentException("Jugador no permitido");
                }
                if (p1Count >= 3 && p1Count == p2Count) {
                    System.out.println("Deuce");
                } else if (p1Count > 3 && p1Count == p2Count + 1) {
                    System.out.println("Ventaja P1");
                } else if (p2Count > 3 && p2Count == p1Count + 1) {
                    System.out.println("Ventaja P2");
                } else if (p1Count > 3 && p1Count >= p2Count + 2) {
                    System.out.println("Ha ganado P1");
                    endGame = true;
                } else if (p2Count > 3 && p2Count >= p1Count + 2) {
                    System.out.println("Ha ganado P2");
                    endGame = true;
                } else {
                    System.out.println(EARLY_SCORES.get(p1Count) + " - " + EARLY_SCORES.get(p2Count));
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
