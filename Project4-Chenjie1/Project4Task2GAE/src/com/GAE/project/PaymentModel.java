package com.GAE.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PaymentModel {

	String response;
	
	public void doSearch(String price, String down){
		response = "";
		try {
			URL url = new URL("http://www.zillow.com/webservice/GetMonthlyPayments.htm?zws-id=X1-ZWz1dsbizu9mob_2vy2l&price=" + price + "&down=" + down + "&zip=98104");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String str;
			while ((str = in.readLine()) != null) {
				response += str + "\n";
			}
			in.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getXML(){
		return response;
	}
}
