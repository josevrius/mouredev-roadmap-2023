package roadmap.challenge05;

import java.util.ArrayList;
import java.util.List;

public final class App {

    private enum Sign {
        ROCK("🪨"), PAPER("📄"), SCISSOR("✂️"), LIZARD("🦎"), SPOCK("🖖");

        private final String icon;

        Sign(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return icon;
        }

        public boolean winsTo(Sign sign) {
            return this == ROCK && (sign == SCISSOR || sign == LIZARD)
                    || this == PAPER && (sign == ROCK || sign == SPOCK)
                    || this == SCISSOR && (sign == PAPER || sign == LIZARD)
                    || this == LIZARD && (sign == SPOCK || sign == PAPER)
                    || this == SPOCK && (sign == SCISSOR || sign == ROCK);
        }
    }

    private record Round(Sign player1, Sign player2) {
    }

    private record Scores(int player1, int player2) {
    }

    private static final String HEADER = """
            
            PIEDRA, PAPEL, TIJERA, LAGARTO, SPOCK
            =====================================""";

    public static void main(String[] args) {
        System.out.println(HEADER);
        List<Round> rounds = setRounds();
        Scores scores = playGame(rounds);
        showResult(scores);
    }

    private static List<Round> setRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(new Round(Sign.ROCK, Sign.ROCK));
        rounds.add(new Round(Sign.SPOCK, Sign.ROCK));
        rounds.add(new Round(Sign.PAPER, Sign.SCISSOR));
        rounds.add(new Round(Sign.LIZARD, Sign.LIZARD));
        rounds.add(new Round(Sign.LIZARD, Sign.SCISSOR));

        return rounds;
    }

    private static Scores playGame(List<Round> rounds) {
        int p1Score = 0;
        int p2Score = 0;

        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            System.out.printf("Ronda %d : %s vs %s%n", i + 1, round.player1.getIcon(), round.player2.getIcon());
            if (round.player1 != round.player2) {
                if (round.player1.winsTo(round.player2)) {
                    p1Score++;
                } else {
                    p2Score++;
                }
            }
        }
        return new Scores(p1Score, p2Score);
    }

    private static void showResult(Scores score) {
        String result;

        if (score.player1 > score.player2) {
            result = "Jugador 1";
        } else if (score.player1 < score.player2) {
            result = "Jugador 2";
        } else {
            result = "Empate";
        }
        System.out.println("Ganador : " + result);
    }
}
