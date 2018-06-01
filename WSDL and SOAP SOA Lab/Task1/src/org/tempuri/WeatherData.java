package org.tempuri;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherData {
	private JSONObject obj;
	
	public WeatherData(String json) {
		obj = new JSONObject(json);
	}
	
	public String getWeather() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("weather");
	}
	
	public String getTemp() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("temperature_string");
	}
	
	public String getFeel() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("feelslike_string");
	}
	
	public String getRelativeHumidity() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("relative_humidity");
	}
	
	public String getWind() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("wind_string");
	}
	
	public String getWindDirection() {
		JSONObject weather = obj.getJSONObject("current_observation");
		return weather.getString("wind_dir");
	}
}
