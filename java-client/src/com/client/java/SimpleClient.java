package com.client.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SimpleClient {

	public static void main(String[] args) {

		String url = "http://localhost:8080/topics";
		
		try {
			sendGet(url);
			
			sendPost(url);
			
			sendGet(url);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void sendGet(String urlStr) throws IOException, ParseException{
		
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
			StringBuilder jsonStr = new StringBuilder();
			
			while((output = br.readLine())!=null){
				jsonStr.append(output);
			}
			
			conn.disconnect();
	
			JSONParser parser = new JSONParser();
			System.out.println("JSON response: \n"+jsonStr.toString());
			
			Object obj = parser.parse(jsonStr.toString());
			
			JSONArray jsonArr = (JSONArray) obj;
			
			String id = (String) ((JSONObject)jsonArr.get(0)).get("id");
			String name = (String) ((JSONObject)jsonArr.get(0)).get("name");
			String description = (String) ((JSONObject)jsonArr.get(0)).get("description");

			Topic topic = new Topic(id, name, description);
			
			System.out.println(topic.toString());
	}
	
	
	public static void sendPost(String urlStr) throws IOException{
		
		URL url = new URL(urlStr);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", 3);
        jsonObj.put("name", "MCS");
        jsonObj.put("description", "MCS is core");

		
		wr.writeBytes(jsonObj.toJSONString());
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + jsonObj.toJSONString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
	}

}
