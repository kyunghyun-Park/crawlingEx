import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ExchangeList {
    public static void getExchangeList(){

        final String stockList = "https://finance.naver.com/marketindex/exchangeList.naver";
        Connection conn = Jsoup.connect(stockList);

        try {
            Document document = conn.get();
            //String thead = getExchangeHeader(document);
            String tbody = getExchangeBody(document);
            //System.out.println(thead);
            System.out.println(tbody);
        }catch (IOException ignored){

        }
    }

    public static String getExchangeHeader(Document document){
        Elements stockTableBody = document.select("div.section_exchange");
        StringBuilder sb = new StringBuilder();
        for(Element element : stockTableBody){
            for (Element td : element.select("th")){
                sb.append(td.text());
                sb.append(" ");
            }
            break;
        }
        return sb.toString();
    }

    public static String getExchangeBody(Document document){
        Elements stockTableBody = document.select("div.tbl_area table tbody");
        StringBuilder sb = new StringBuilder();
        for(Element element : stockTableBody){

            for (Element td : element.select("td")){
                String text;
                text = td.text();

                sb.append(text);
                sb.append("    ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        getExchangeList();
    }
}
