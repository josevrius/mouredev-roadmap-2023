package roadmap.challenge32;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class App32 {

    private static final Scanner SCN = new Scanner(System.in);

    private static final Path TEXT = Path.of("./src/main/resources/text.txt");

    private static final String HEADER = """
            
            TEXT WRITER
            ===========""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            writeText();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static String enterString() {
        System.out.print("> ");
        return SCN.nextLine().strip();
    }

    private static String enterOption() {
        Pattern validInput = Pattern.compile("continue|delete");
        String option;
        boolean isValid;

        do {
            option = enterString().toLowerCase();
            isValid = validInput.matcher(option).matches();
        } while (!isValid);

        return option;
    }

    private static void writeText() throws IOException {
        checkExistingText();

        while (true) {
            String line = enterString();
            if (line.equalsIgnoreCase("exit")) break;
            Files.writeString(TEXT, line + System.lineSeparator(), StandardOpenOption.APPEND);
        }
    }

    private static void checkExistingText() throws IOException {
        if (Files.exists(TEXT)) {
            System.out.println("Se ha encontrado un texto");
            System.out.println("Escribe \"continue\" para continuar o \"delete\" para comenzar de nuevo");

            String option = enterOption();
            System.out.println();
            if (option.equals("continue")) {
                try (Stream<String> lines = Files.lines(TEXT)) {
                    lines.forEach(System.out::println);
                }
            } else {
                Files.writeString(TEXT, "", StandardOpenOption.TRUNCATE_EXISTING);
            }
        } else {
            Files.createFile(TEXT);
        }
    }
}
