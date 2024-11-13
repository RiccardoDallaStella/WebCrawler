import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci il link della pagina da visitare: ");
        String link = sc.nextLine();

        if(!link.contains("https") || !link.contains("http")){
            link = "http://" + link;
        }

        crawl(1, link, new ArrayList<String>());
    }

    public static void crawl(int level, String url, ArrayList<String> visited){

        if(level > 3)
            return;

        Document doc = null;

        try{
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());;
        }

        for(Element link : doc.select("a[href]")){
            String next = link.absUrl("href");
            if(!visited.contains(next)){
                System.out.println("Title: " + doc.title() + "\t" + level);
                System.out.println("Link: " + next);
                visited.add(next);
                crawl(level+1, next, visited);
            }
        }
    }
}
