package parser;

import org.w3c.dom.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import feed.Article;
import feed.Feed;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class RssParser extends GeneralParser {
    public Feed parseFeed(String xmlString){
        //xmlToDoc() de GeneralParser, se encarga de convertir xml a una interfaz de documento
        //Eliminando ambiguedades y facilitando su lectura
        Document doc = xmlToDoc(xmlString);

        //Obtengo el siteName del tag <title> dentro de <channel>
        Element channel = (Element) doc.getElementsByTagName("channel").item(0);
        NodeList titleNodes = channel.getElementsByTagName("title");
        String siteName = (titleNodes != null && titleNodes.getLength() > 0) ? titleNodes.item(0).getTextContent() : "Sin título";
        Feed feed = new Feed(siteName);  //creo el feed con el siteName

        // Obtiene todos los nodos <item> del documento
        NodeList itemList = doc.getElementsByTagName("item");

        for(int i = 0; i<itemList.getLength(); i++){
            Node item = itemList.item(i);
            if(item.getNodeType() == Node.ELEMENT_NODE){ // Solo procesa si es un nodo de tipo ELEMENTO(title, description, link, etc)
                Element element = (Element) item;
                // Extrae el contenido del tag <title>, <description>, <link>, <pubDate>
                NodeList tList = element.getElementsByTagName("title");
                NodeList dList = element.getElementsByTagName("description");
                NodeList lList = element.getElementsByTagName("link");
                NodeList pList = element.getElementsByTagName("pubDate");
                String title = tList.item(0).getTextContent();
                String description = dList.item(0).getTextContent();
                String  link = lList.item(0).getTextContent();
                String pubDateStr = pList.item(0).getTextContent(); //Guardado como string para formatear a tipo Date
                Date pubDate = new Date();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
                    pubDate = formatter.parse(pubDateStr); // Intenta parsear la fecha del String
                } catch (Exception e) {
                    System.err.println("Error parsing date: " + pubDateStr); // Maneja errores de formato
                }
                // Crea un objeto Article con los datos extraídos y lo agrega al Feed
                Article article = new Article(title, description, pubDate, link);
                feed.addArticle(article);
            }
        }
        return feed;
    }
    
}
