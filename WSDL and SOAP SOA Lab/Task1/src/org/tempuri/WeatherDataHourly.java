package org.tempuri;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherDataHourly {
	private JSONArray arr;
	
	public WeatherDataHourly(String json) {
		JSONObject obj = new JSONObject(json);
		arr = obj.getJSONArray("hourly_forecast");
	}
	
	public String[] getHours() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("FCTTIME");
			results[i] = obj.getString("hour") + ":00";
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
	
	public String[] getTemp() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("temp");
			results[i] = obj.getString("english") + " F (" + obj.getString("metric") + " C)";
		}
		
		return results;
	}
	
	public String[] getFeel() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			obj = obj.getJSONObject("feelslike");
			results[i] = obj.getString("english") + " F (" + obj.getString("metric") + " C)";
		}
		
		return results;
	}	
	
	public String[] getRelativeHumidity() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			results[i] = obj.getString("humidity") + "%";
		}
		
		return results;
	}
	
	public String[] getPrediction() {
		String[] results = new String[10];
		for(int i=0; i<10; i++) {
			JSONObject obj = arr.getJSONObject(i);
			results[i] = obj.getString("wx");
		}
		
		return results;
	}
}
