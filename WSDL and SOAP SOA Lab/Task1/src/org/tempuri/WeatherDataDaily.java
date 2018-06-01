package org.tempuri;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherDataDaily {
	private JSONArray arr;
	
	public WeatherDataDaily(String json) {
		JSONObject obj = new JSONObject(json);
		obj = obj.getJSONObject("forecast");
		obj = obj.getJSONObject("simpleforecast");
		arr = obj.getJSONArray("forecastday");
	}
	
	public String[] getDays() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("date");
			results[i] = obj.getString("pretty");
		}
		
		return results;
	}
	
	public String[] getImage() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			results[i] = obj.getString("icon_url");
		}
		
		return results;
	}
	
	public String[] getHigh() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("high");
			results[i] = obj.getString("fahrenheit") + " F (" + obj.getString("celsius") + " C)";
		}
		
		return results;
	}
	
	public String[] getLow() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("low");
			results[i] = obj.getString("fahrenheit") + " F (" + obj.getString("celsius") + " C)";
		}
		
		return results;
	}
	
	public String[] getPrediction() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			results[i] = obj.getString("conditions");
		}
		
		return results;
	}
}
