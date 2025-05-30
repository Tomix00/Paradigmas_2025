package parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import feed.Feed;
import feed.Article;

/* Esta clase implementa el parser de feed de tipo localhost (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class LocalhostParser extends Parser {


    public Feed parseLocalhost(String localhostFeed,String url, String type){
        Feed feed = new Feed(url,type);
        
	try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream inputStream = new ByteArrayInputStream(localhostFeed.getBytes());
		Document doc = builder.parse(inputStream);
		doc.getDocumentElement().normalize();

		//Element localFeed = (Element) doc.getElementsByTagName("local-feed").item(0);
		//NodeList localFeed = doc.getElementsByTagName("local-feed");

		NodeList itemNodes = doc.getElementsByTagName("article");

		for (int i=0 ; i< itemNodes.getLength() ; i++){
			String title = null, description = null, date = null, link = null;

			Node itemNode = itemNodes.item(i);
			NodeList ItemChildNodes = itemNode.getChildNodes();

			for(int j = 0;j<ItemChildNodes.getLength();j++){
				Node childItemNode = ItemChildNodes.item(j);
				if(childItemNode.getNodeName().equals("title")){
					title = childItemNode.getTextContent();
				}else if(childItemNode.getNodeName().equals("subtitle")){
					description = childItemNode.getTextContent();
				}else if(childItemNode.getNodeName().equals("date")){	
					date = childItemNode.getTextContent();
				}else if(childItemNode.getNodeName().equals("ref")){
					link = childItemNode.getTextContent();
				}
			}
			Article article = new Article(title,description,date,link);
			feed.addArticle(article);
		}

	}catch(Exception e){
		e.printStackTrace();
	}
        
        
        return feed;
    }

}
