package roadmap.challenge10;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class App {

    private static final String HEADER = """
            
            PARÁMETROS URL
            ==============
            URL .......:\s""";

    public static void main(final String[] args) {
        String url = "https://retosdeprogramacion.com?year=2023&challenge=10";

        System.out.println(HEADER + url);
        List<String> params = getParams(url);
        System.out.println("Parámetros : " + params);
    }

    private static List<String> getParams(String url) {
        List<String> params = new ArrayList<>();
        Pattern pattern = Pattern.compile("=[^&]+");
        Matcher matcher = pattern.matcher(url);

        while (matcher.find()) {
            params.add(matcher.group().replace("=", ""));
        }
        return params;
    }
}
