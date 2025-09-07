package roadmap.challenge31;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class App31 {

    private static final Scanner SCN = new Scanner(System.in);

    private static final String[][] SCREEN = {
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"},
            {"⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜", "⬜"}
    };

    private static final int[][] pieceBlocks = {{0, 0}, {1, 0}, {1, 1}, {1, 2}};
    private static int rotationSequence = 0;

    private enum Move {DOWN, LEFT, RIGHT}

    private static final String HEADER = """
            
            TETRIS
            ======
            [I] Izquierda
            [D] Derecha
            [A] Abajo
            [R] Rotar
            
            [0] Salir
            """;

    public static void main(String[] args) {
        System.out.println(HEADER);
        refreshScreen();

        boolean exit = false;
        do {
            String option = enterOption();
            switch (option) {
                case "I" -> movePiece(Move.LEFT);
                case "D" -> movePiece(Move.RIGHT);
                case "A" -> movePiece(Move.DOWN);
                case "R" -> rotatePiece();
                case "0" -> exit = true;
            }
            if (isFixed()) {
                exit = true;
                System.out.println("Game Over");
            }
        } while (!exit);
    }

    private static String enterOption() {
        Pattern validInput = Pattern.compile("[IDAR0]");
        String option;

        do {
            System.out.print("Opción : ");
            option = SCN.nextLine().strip().toUpperCase();
        } while (!validInput.matcher(option).matches());

        return option;
    }

    private static void movePiece(Move move) {
        if (checkMove(move)) {
            for (int[] block : pieceBlocks) {
                SCREEN[block[0]][block[1]] = "⬜";
                switch (move) {
                    case DOWN -> block[0] = block[0] + 1;
                    case LEFT -> block[1] = block[1] - 1;
                    case RIGHT -> block[1] = block[1] + 1;
                }
            }
        }
        refreshScreen();
    }

    private static void rotatePiece() {
        for (int[] block : pieceBlocks) {
            SCREEN[block[0]][block[1]] = "⬜";
        }
        switch (rotationSequence) {
            case 0 -> {
                if (pieceBlocks[3][0] + 1 >= 10) {
                    for (int[] block : pieceBlocks) {
                        block[0] = block[0] - 1;
                    }
                }
                pieceBlocks[0][1] = pieceBlocks[0][1] + 2;
                pieceBlocks[1][0] = pieceBlocks[1][0] - 1;
                pieceBlocks[1][1] = pieceBlocks[1][1] + 1;
                pieceBlocks[3][0] = pieceBlocks[3][0] + 1;
                pieceBlocks[3][1] = pieceBlocks[3][1] - 1;
                rotationSequence++;
            }
            case 1 -> {
                if (pieceBlocks[3][1] - 1 < 0) {
                    for (int[] block : pieceBlocks) {
                        block[1] = block[1] + 1;
                    }
                }
                pieceBlocks[0][0] = pieceBlocks[0][0] + 2;
                pieceBlocks[1][0] = pieceBlocks[1][0] + 1;
                pieceBlocks[1][1] = pieceBlocks[1][1] + 1;
                pieceBlocks[3][0] = pieceBlocks[3][0] - 1;
                pieceBlocks[3][1] = pieceBlocks[3][1] - 1;
                rotationSequence++;
            }
            case 2 -> {
                pieceBlocks[0][1] = pieceBlocks[0][1] - 2;
                pieceBlocks[1][0] = pieceBlocks[1][0] + 1;
                pieceBlocks[1][1] = pieceBlocks[1][1] - 1;
                pieceBlocks[3][0] = pieceBlocks[3][0] - 1;
                pieceBlocks[3][1] = pieceBlocks[3][1] + 1;
                rotationSequence++;
            }
            case 3 -> {
                if (pieceBlocks[3][1] + 1 >= 10) {
                    for (int[] block : pieceBlocks) {
                        block[1] = block[1] - 1;
                    }
                }
                pieceBlocks[0][0] = pieceBlocks[0][0] - 2;
                pieceBlocks[1][0] = pieceBlocks[1][0] - 1;
                pieceBlocks[1][1] = pieceBlocks[1][1] - 1;
                pieceBlocks[3][0] = pieceBlocks[3][0] + 1;
                pieceBlocks[3][1] = pieceBlocks[3][1] + 1;
                rotationSequence = 0;
            }
        }
        refreshScreen();
    }

    private static void refreshScreen() {
        for (int[] block : pieceBlocks) {
            SCREEN[block[0]][block[1]] = "🔳";
        }
        for (String[] row : SCREEN) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    private static boolean checkMove(Move move) {
        for (int[] block : pieceBlocks) {
            switch (move) {
                case DOWN -> {
                    if (block[0] + 1 > 9) return false;
                }
                case LEFT -> {
                    if (block[1] - 1 < 0) return false;
                }
                case RIGHT -> {
                    if (block[1] + 1 > 9) return false;
                }
            }
        }
        return true;
    }

    private static boolean isFixed() {
        for (int[] block : pieceBlocks) {
            if (block[0] == 9) {
                return true;
            }
        }
        return false;
    }
}
