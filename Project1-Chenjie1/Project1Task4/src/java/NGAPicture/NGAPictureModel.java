package NGAPicture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NGAPictureModel {

    private String pictureTag;
    private String pictureURLDesk;
    private String pictureTitle;
    private String pictureID;
    private String pictureURLMob;

    public void doNGASearch(String searchTag) {
        pictureTag = searchTag;
        
        String response = "";
        try {
            // Create a URL for the desired page            
            URL url = new URL("https://images.nga.gov/en/search/show_advanced_search_page/?service=search&action=do_advanced_search&language=en&form_name=default&all_words=&exact_phrase=&exclude_words=&artist_last_name="
                    + searchTag);

            // Create an HttpURLConnection.  This is useful for setting headers
            // and for getting the path of the resource that is returned (which 
            // may be different than the URL above if redirected).
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            
        }

        // find the picture URL to scrape
        int startassets = response.indexOf("src=\"/assets");

        // only start looking after the quote before http
        int endassets = response.indexOf("\"", startassets + 5);
        // +1 to include the quote
        int startid = response.indexOf("assetid=\"");
        int endid = response.indexOf("\"",startid+9);
        
        pictureID = response.substring(startid+9, endid);
        pictureURLMob =response.substring(startassets, startassets+5) + "https://images.nga.gov/" + response.substring(startassets+6, endassets+1);
        pictureURLDesk="src=\"" + "https://images.nga.gov/?service=asset&action=show_preview&asset=" + pictureID+ "\"";
        int startTitle = response.indexOf("alt=\"\" title=\"");
        int endTitle =response.indexOf("\"", startTitle + 14);
        pictureTitle = response.substring(startTitle+14, endTitle);
     
    }

    public String interestingPictureSize(String picsize) {
        if (!picsize.equals("mobile"))
            //pictureURL= pictureURL.replaceAll("/7/", "/1/");
            return pictureURLDesk;
        else
            //pictureURL= pictureURL.replaceAll("/1/", "/7/");
            return pictureURLMob;
    }

    public String getPictureTag() {
        return (pictureTag);
    }
    
    public String getPictureTitle(){
        return pictureTitle;
    }
}
