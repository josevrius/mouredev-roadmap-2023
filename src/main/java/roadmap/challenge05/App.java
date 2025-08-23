package roadmap.challenge05;

import java.util.ArrayList;
import java.util.List;

public final class App {

    private enum Sign {
        PIEDRA("🪨"), PAPEL("📄"), TIJERA("✂️"), LAGARTO("🦎"), SPOCK("🖖");

        private final String label;

        Sign(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private record Round(Sign player1, Sign player2) {
    }

    private static final String HEADER = """
            
            PIEDRA, PAPEL, TIJERA, LAGARTO, SPOCK
            =====================================""";

    public static void main(String[] args) {
        System.out.println(HEADER);
        List<Round> rounds = setRounds();
        playGame(rounds);
    }

    private static List<Round> setRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round(Sign.PIEDRA, Sign.PIEDRA));
        rounds.add(new Round(Sign.TIJERA, Sign.PAPEL));
        rounds.add(new Round(Sign.PAPEL, Sign.TIJERA));
        rounds.add(new Round(Sign.LAGARTO, Sign.LAGARTO));
        rounds.add(new Round(Sign.LAGARTO, Sign.TIJERA));

        return rounds;
    }

    private static void playGame(List<Round> rounds) {
        int p1Score = 0;
        int p2Score = 0;

        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            System.out.printf("Ronda %d : %s vs %s%n", i + 1, round.player1.getLabel(), round.player2.getLabel());
            if (round.player1 != round.player2) {
                if (winsPlayer1(round.player1, round.player2)) {
                    p1Score++;
                } else {
                    p2Score++;
                }
            }
        }
        if (p1Score > p2Score) {
            System.out.println("Gana ...: Jugador 1");
        } else if (p1Score < p2Score) {
            System.out.println("Gana ...: Jugador 2");
        } else {
            System.out.println("Gana ...: Empate");
        }
    }

    private static boolean winsPlayer1(Sign player1, Sign player2) {
        return switch (player1) {
            case PIEDRA -> player2 == Sign.TIJERA || player2 == Sign.LAGARTO;
            case PAPEL -> player2 == Sign.PIEDRA || player2 == Sign.SPOCK;
            case TIJERA -> player2 == Sign.PAPEL || player2 == Sign.LAGARTO;
            case LAGARTO -> player2 == Sign.SPOCK || player2 == Sign.PAPEL;
            case SPOCK -> player2 == Sign.TIJERA || player2 == Sign.PIEDRA;
        };
    }
}
