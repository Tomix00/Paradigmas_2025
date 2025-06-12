import subscription.Subscription;
import subscription.Subscriptions;
import Request.Requester;
import parser.Parser;
import feed.Feed;

import java.io.FileWriter;
import java.io.IOException;

public class FeedReaderMain_ej1 {
	public static void main(String[] args) {
	
		Subscriptions sub = new Subscriptions("config/subscriptions_ej1.json");
		for (Subscription s: sub.getSubscriptions()){
			Requester request = new Requester(s.getUrl(),s.getUrlType());
			Feed feed = Parser.parse(request.getResponse(), request.getUrl() ,request.getType()); 
			
			if(s.getDownload() != null){
				try(FileWriter fw = new FileWriter(s.getDownload())){
					fw.write(feed.toString());
				}catch(IOException e){
					e.printStackTrace();
				}
			}else{
				feed.prettyPrint();	
			}			
		}
	}
}
