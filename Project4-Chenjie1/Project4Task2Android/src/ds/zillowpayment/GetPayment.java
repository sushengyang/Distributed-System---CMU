package ds.zillowpayment;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/*
 * This class provides capabilities to calculate monthly payment within a fixed 30 years period, given 
 * house price and down payment percentage
 * 
 */
public class GetPayment {
	Payment ip = null;
	

	public void search( Payment ip, String... urls) {
		this.ip = ip;
		new AsyncZillowSearch().execute(urls);
	}

	/*
	 * AsyncTask provides a simple way to use a thread separate from the UI thread in which to do network operations.
	 * doInBackground is run in the helper thread.
	 * onPostExecute is run in the UI thread, allowing for safe UI updates.
	 */
    private class AsyncZillowSearch extends AsyncTask<String, Void, List> {
        protected List doInBackground(String... urls) {
            return search(urls[0], urls[1]);
        }

        protected void onPostExecute(List payment) {
        	ip.paymentReady(payment);
        }

        private List search(String price, String down) {
        		
    	      Document doc = getRemoteXML("http://1-dot-jackproject4task2.appspot.com/project4task2gae?price=" + price + "&downpayment=" + down);
    	      NodeList nl = doc.getElementsByTagName("payment"); 
    	      if (nl.getLength() == 0) {
    	        	return null; // not found
    	       } else {
    	        	Element thirtyFixed = (Element) nl.item(0);
    	        	String loanType = thirtyFixed.getAttribute("loanType");
    	        	String rate = thirtyFixed.getChildNodes().item(0).getTextContent();
    	        	String monthlyPayment = thirtyFixed.getChildNodes().item(1).getTextContent();
    	        	String monthlyInsurance = thirtyFixed.getChildNodes().item(2).getTextContent();
    	        	//String rate = doc.toString();
    	        	List payment = new ArrayList();
    	        	payment.add(loanType);
    	        	payment.add(rate);
    	        	payment.add(monthlyInsurance);
    	        	payment.add(monthlyPayment);
    	        	return payment;
    	        	
    	        } 

        }
        
        /* 
         * Given a url that will request XML, return a Document with that XML, else null
         */
        private Document getRemoteXML(String url) {
        	 try {
	                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	                DocumentBuilder db = dbf.newDocumentBuilder();
	                InputSource is = new InputSource(url);
	                return db.parse(is);
	        } catch (Exception e) {
	        	System.out.print("Yikes, hit the error: "+e);
	        	return null;
	        }
        }
        

    }
}