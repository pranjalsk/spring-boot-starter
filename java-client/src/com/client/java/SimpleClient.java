package com.client.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleClient {

	public static void main(String[] args) {

		String url = "http://localhost:8080/topics";
		
		connectRemote(url);
		
	}
	
	
	public static void connectRemote(String urlStr){
		
		try {
			URL url = new URL(urlStr);
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed : HTTP error code: "+ conn.getResponseCode());
			}
			
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while((output = br.readLine())!=null){
				System.out.println(output);
			}
			
			conn.disconnect();
	
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
		
		
	}
	

}
