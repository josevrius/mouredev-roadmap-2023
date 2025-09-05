package roadmap.challenge16;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class App16 {

    private static final String HEADER = """
            
            HOLA MUNDO DAY AGENDA 2025
            ==========================""";

    public static void main(String[] args) {
        try {
            System.out.println(HEADER);
            Document doc = Jsoup.connect("https://holamundo.day").get();
            Element section = doc.selectFirst("h2:containsOwn(Agenda)").parent().parent();
            Elements events = section.getElementsByClass("rt-Text rt-r-size-4");
            for (Element event : events) {
                System.out.println(event.text());
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
