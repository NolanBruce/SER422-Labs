package org.tempuri;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Client extends HttpServlet {
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			IService client = new IServiceProxy();
			String city = request.getParameter("city_name_weather");
			String state = request.getParameter("state_names_for_weather");
			if(request.getParameter("hourly_check") == null && request.getParameter("Ten_day_check") == null) {
				if(city != null && state != null) {
					String weather = client.getWeather(city, state);
					System.out.println(weather);
					WeatherData data = new WeatherData(weather);
					request.setAttribute("type", "current");
					request.setAttribute("data", weather);
					request.setAttribute("weather", data.getWeather());
					request.setAttribute("temp", data.getTemp());
					request.setAttribute("feel", data.getFeel());
					request.setAttribute("humidity", data.getRelativeHumidity());
					request.setAttribute("wind", data.getWind());
					request.setAttribute("direction", data.getWindDirection());
				}
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else if(request.getParameter("hourly_check") != null) {
				if(city != null && state != null) {
					String weather = client.getWeather_hourly(city, state, true);
					System.out.println(weather);
					WeatherDataHourly data = new WeatherDataHourly(weather);
					request.setAttribute("data", weather);
					request.setAttribute("type", "hourly");
					request.setAttribute("time", data.getHours());
					request.setAttribute("image", data.getImage());
					request.setAttribute("temp", data.getTemp());
					request.setAttribute("feel", data.getFeel());
					request.setAttribute("humidity", data.getRelativeHumidity());
					request.setAttribute("prediction", data.getPrediction());
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			} else if(request.getParameter("Ten_day_check") != null) {
				request.setAttribute("type", "daily");
				String weather = client.getWeather_tenDays(city, state, true);
				System.out.println(weather);
				WeatherDataDaily data = new WeatherDataDaily(weather);
				request.setAttribute("data", weather);
				request.setAttribute("day", data.getDays());
				request.setAttribute("image", data.getImage());
				request.setAttribute("high", data.getHigh());
				request.setAttribute("low", data.getLow());
				request.setAttribute("prediction", data.getPrediction());
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			request.setAttribute("error", "remote");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch(JSONException e) {
			e.printStackTrace();
			request.setAttribute("error", "json");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	/*
	public static void main(String[] args) {
		IService client = new IServiceProxy();
		try {
			//WeatherData data = new WeatherData(client.getWeather("Sugar Hill", "GA"));
			JSONObject json = new JSONObject(client.getWeather_tenDays("Atlanta", "GA", true));
			System.out.println(json);
			JSONObject obj = json.getJSONObject("forecast");
			obj = obj.getJSONObject("simpleforecast");
			JSONArray arr = obj.getJSONArray("forecastday");
			System.out.println(arr.get(0));
			WeatherDataDaily daily = new WeatherDataDaily(client.getWeather_tenDays("Atlanta", "GA", true));
			System.out.println(daily.getPrediction()[0]);
			//JSONObject obj = arr.getJSONObject(1);
			//System.out.println(obj.getString("wx"));
			//JSONObject weather = json.getJSONObject("current_observation");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	*/
	
}
