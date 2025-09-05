package roadmap.challenge23;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class Ap23 {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String MENU = """
            
            CONTRA
            ===========
            [1] PLAYER
            [2] PLAYERS
            
            PLAY SELECT :\s""";

    private static final String PLAYERS = """
            -----------
            %sP
            REST %s
            -----------
            """;

    private static final String[] CODE = {"UP", "UP", "DOWN", "DOWN", "LEFT", "RIGHT", "LEFT", "RIGHT", "B", "A"};
    private static int sequence = 0;

    public static void main(String[] args) {
        HashMap<String, Integer> players = selectPlayers();
        System.out.printf(PLAYERS, players.get("players"), players.get("rest"));
    }

    private static HashMap<String, Integer> selectPlayers() {
        HashMap<String, Integer> players = new HashMap<>();
        Pattern validInput = Pattern.compile("[1-2]");

        String input;
        boolean inputOK;
        boolean codeOK = false;

        do {
            System.out.print(MENU);
            input = SCN.nextLine().strip().toUpperCase();
            inputOK = validInput.matcher(input).matches();
            if (!codeOK) {
                codeOK = checkCode(input);
            }
        } while (!inputOK);

        players.put("players", Integer.parseInt(input));
        players.put("rest", codeOK ? 30 : 3);

        return players;
    }

    private static boolean checkCode(String input) {
        if (input.equals(CODE[sequence])) {
            if (sequence < CODE.length - 1) {
                sequence++;
            } else {
                sequence = 0;
                return true;
            }
        } else {
            sequence = 0;
        }
        return false;
    }
}
