package parser;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.json.JSONArray;

import subscription.*;

public class SubscriptionParser extends GeneralParser{
    
    public Subscription createSubscriptionFromJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);

            JSONObject rssJsonObject = array.getJSONObject(0);
            JSONObject redditJsonObject = array.getJSONObject(1);
            

            //rss singleSubscription
            JSONArray rssParams = rssJsonObject.getJSONArray("urlParams");

            String rssUrl = rssJsonObject.getString("url");
            String rssUrlType = rssJsonObject.getString("urlType");

            SingleSubscription rssSingleSubscription = new SingleSubscription(rssUrl, null, rssUrlType);

            for(int i=0; i<rssParams.length(); i++){
                String param = rssParams.getString(i);
                rssSingleSubscription.setUlrParams(param);
            }

            //reddit singleSubscription
            JSONArray redditParams = redditJsonObject.getJSONArray("urlParams");

            String redditUrl = redditJsonObject.getString("url");
            String redditUrlType = redditJsonObject.getString("urlType");

            SingleSubscription redditSingleSubscription = new SingleSubscription(redditUrl, null, redditUrlType);

            for(int i=0; i<redditParams.length(); i++){
                String param = redditParams.getString(i);
                redditSingleSubscription.setUlrParams(param);
            }

            //list of singleSubscriptions
            Subscription subs = new Subscription(null);
            subs.addSingleSubscription(rssSingleSubscription);
            subs.addSingleSubscription(redditSingleSubscription);


            return subs;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return null;
    }    
}
