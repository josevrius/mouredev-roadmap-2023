package roadmap.challenge06;

import java.util.*;
import java.util.regex.Pattern;

public final class App {

    private static final Scanner SCN = new Scanner(System.in);
    private static final Random RND = new Random();

    private static final String HEADER = """
            
            EL SOMBRERO SELECCIONADOR
            =========================
            Hola, soy el Sombrero Seleccionador.
            Tendrás que responder cinco preguntas para ayudarme a descubrir tu casa de Hogwarts.
            
            """;

    private static final String FOOTER = """
            
            DECISIÓN DEL SOMBRERO
            =====================""";

    private static final String QUESTION_1 = """
            
            ¿CÓMO TE DEFINIRÍAS?
            ====================
            [1] Valiente
            [2] Leal
            [3] Sabio
            [4] Ambicioso
            
            Opción :\s""";

    private static final String QUESTION_2 = """
            
            ¿CUÁL ES TU CLASE FAVORITA?
            ===========================
            [1] Vuelo
            [2] Pociones
            [3] Defensa contra las artes oscuras
            [4] Animales fantásticos
            
            Opción :\s""";

    private static final String QUESTION_3 = """
            
            ¿DÓNDE PASARÍAS MAS TIEMPO?
            ===========================
            [1] Invernadero
            [2] Biblioteca
            [3] Sala común
            [4] Explorando
            
            Opción :\s""";

    private static final String QUESTION_4 = """
            
            ¿CUÁL ES TU COLOR FAVORITO?
            ===========================
            [1] Rojo
            [2] Azul
            [3] Verde
            [4] Amarillo
            
            Opción :\s""";

    private static final String QUESTION_5 = """
            
            ¿CUÁL ES TU MASCOTA?
            ====================
            [1] Sapo
            [2] Lechuza
            [3] Gato
            [4] Serpiente
            
            Opción :\s""";

    private enum House {GRYFFINDOR, SLYTHERIN, HUFFLEPUFF, RAVENCLAW}

    private static final Map<House, Integer> AFFINITY_LEVEL = new HashMap<>();
    private static final Map<String, House[]> QUIZ = setQuiz();

    public static void main(String[] args) {
        System.out.print(HEADER);
        pauseApp("Pulsa ENTER para comenzar ");
        launchQuiz();
        showHouse();
    }

    private static void launchQuiz() {
        for (String question : QUIZ.keySet()) {
            String option = enterOption(question);
            House related = QUIZ.get(question)[Integer.parseInt(option) - 1];
            AFFINITY_LEVEL.put(related, AFFINITY_LEVEL.getOrDefault(related, 0) + 1);
        }
    }

    private static Map<String, House[]> setQuiz() {
        LinkedHashMap<String, House[]> quiz = new LinkedHashMap<>();
        quiz.put(QUESTION_1, new House[]{House.GRYFFINDOR, House.HUFFLEPUFF, House.RAVENCLAW, House.SLYTHERIN});
        quiz.put(QUESTION_2, new House[]{House.GRYFFINDOR, House.RAVENCLAW, House.SLYTHERIN, House.HUFFLEPUFF});
        quiz.put(QUESTION_3, new House[]{House.HUFFLEPUFF, House.RAVENCLAW, House.SLYTHERIN, House.GRYFFINDOR});
        quiz.put(QUESTION_4, new House[]{House.GRYFFINDOR, House.RAVENCLAW, House.SLYTHERIN, House.HUFFLEPUFF});
        quiz.put(QUESTION_5, new House[]{House.RAVENCLAW, House.GRYFFINDOR, House.HUFFLEPUFF, House.SLYTHERIN});

        return Collections.unmodifiableMap(quiz);
    }

    private static String enterOption(String question) {
        String input;
        boolean isValid = false;
        Pattern validOptions = Pattern.compile("[1-4]");

        do {
            System.out.print(question);
            input = SCN.nextLine();
            if (validOptions.matcher(input).matches()) {
                isValid = true;
                pauseApp("Respuesta guardada ");
            } else {
                pauseApp("Escoge una opción válida ");
            }
        } while (!isValid);

        return input;
    }

    private static void showHouse() {
        int maxValue = Collections.max(AFFINITY_LEVEL.values());
        List<House> affinityHouses = new ArrayList<>();

        for (House house : AFFINITY_LEVEL.keySet()) {
            if (AFFINITY_LEVEL.get(house) == maxValue) {
                affinityHouses.add(house);
            }
        }

        System.out.println(FOOTER);
        if (affinityHouses.size() == 1) {
            House house = affinityHouses.getFirst();
            System.out.println("Lo tengo claro, " + house + "!");
        } else {
            House house = affinityHouses.get(RND.nextInt(affinityHouses.size()));
            System.out.println("Una decisión difícil, " + house + "!");
        }
    }

    private static void pauseApp(String msg) {
        System.out.print(msg);
        SCN.nextLine();
    }
}
