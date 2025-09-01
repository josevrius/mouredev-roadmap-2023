package roadmap.challenge16;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public final class App {

    private static final String HEADER = """
            
            HOLA MUNDO DAY AGENDA 2025
            ==========================""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            Document doc = Jsoup.connect("https://holamundo.day").get();
            Element section = doc.selectFirst("h2:containsOwn(Agenda)").parent().parent();
            for (Element event : section) {
                if (event.className().equals("rt-Text rt-r-size-4")) {
                    System.out.println(event.text());
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
