package roadmap.challenge09;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class App9 {

    private static final Scanner SCN = new Scanner(System.in);
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static final String HEADER = """
            
            POKÉAPI
            =============
            Nombre ó ID :\s""";

    private static final String SHEET = """
            
            Ficha Técnica
            =============
            ID ....: %s
            Nombre : %s
            Altura : %s m
            Peso ..: %s kg
            """;

    private static final String API_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_URL = API_URL + "pokemon/";

    private record Pokemon(String id, String name, String height, String weight) {
        public void showInfo() {
            System.out.printf(SHEET, id, name.toUpperCase(), height, weight);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.print(HEADER);
            String search = enterSearch();
            Pokemon pokemon = getPokemon(search);
            pokemon.showInfo();
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                System.err.println("Error ......: Sin respuesta del servidor");
            } else {
                System.err.println("Error ......: " + e.getMessage());
            }
        }
    }

    private static String enterSearch() {
        Pattern validChars = Pattern.compile("[a-z\\d\\-]+");
        String input = SCN.nextLine().strip().toLowerCase();

        if (!validChars.matcher(input).matches()) {
            throw new IllegalArgumentException("Caracteres no permitidos");
        }
        return input;
    }

    private static Pokemon getPokemon(String search) throws IOException, InterruptedException {
        String endPoint = POKEMON_URL + search;
        HttpResponse<String> response = getResponse(endPoint);

        if (response.statusCode() != 200) {
            throw new IOException(response.statusCode() + " " + response.body());
        }

        JsonObject data = new Gson().fromJson(response.body(), JsonObject.class);

        return new Pokemon(getId(data), getName(data), getHeight(data), getWeight(data));
    }

    private static String getId(JsonObject data) {
        return data.get("id").getAsString();
    }

    private static String getName(JsonObject data) {
        return data.get("name").getAsString();
    }

    private static String getHeight(JsonObject data) {
        return DecimalFormat.getNumberInstance().format(data.get("height").getAsDouble() / 10);
    }

    private static String getWeight(JsonObject data) {
        return DecimalFormat.getNumberInstance().format(data.get("weight").getAsDouble() / 10);
    }

    private static HttpResponse<String> getResponse(String url) throws IOException, InterruptedException {
        HttpRequest request = setupRequest(url);
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static HttpRequest setupRequest(String url) {
        return HttpRequest
                .newBuilder().uri(URI.create(url))
                .GET()
                .build();
    }
}
