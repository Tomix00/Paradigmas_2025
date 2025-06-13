package parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import feed.Article;
import feed.Feed;

/* Esta clase implementa el parser de feed de tipo localhost (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class AtomParser extends Parser {


    public Feed parseAtom(String localhostFeed,String url, String type){
        Feed feed = new Feed(url,type);
        
        /*COMPLETAR EJ3 */

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(localhostFeed.getBytes()); //problema <--
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList itemNodes = document.getElementsByTagName("entry");
            
            for(int i=0 ; i<itemNodes.getLength() ; i++){
                String title=null, description=null, pubDate=null , link=null;
                Node itemNode = itemNodes.item(i);
                NodeList ItemChildNodes = itemNode.getChildNodes();
                for (int j = 0; j < ItemChildNodes.getLength(); j++){
                    Node childItemNode = ItemChildNodes.item(j);
                    if (childItemNode.getNodeName().equals("title")){
                        title = childItemNode.getTextContent();
                    }else if (childItemNode.getNodeName().equals("summary")){
                        description = childItemNode.getTextContent();
                    }else if (childItemNode.getNodeName().equals("updated")){
                        pubDate = childItemNode.getTextContent();
                    }else if (childItemNode.getNodeName().equals("link")){
                        link = childItemNode.getTextContent();
                    }    
                }
                Article article = new Article(title,description,pubDate,link);
                feed.addArticle(article);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return feed;
    }

}
