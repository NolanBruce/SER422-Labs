<HTML>
<HEAD><TITLE>Weather SOA</TITLE></HEAD>
<BODY>
	<form method="GET" action="./weather" id="weather_form">
		<span id="state_name_lable_weather">State Name:</span>
		<select name="state_names_for_weather" id="state_names_for_weather" title="Please select state from list">
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DC">District of Columbia</option>
			<option value="DE">Delaware</option>
			<option value="FL">Florida</option>
			<option value="GA" selected>Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</select>
		
		<span id="city_name_text" required>City Name</span>
		<input name="city_name_weather" type="text" id="city_name_weather" title="Please enter city" value="Atlanta" style="width:155px;"/>
		
		<span id="hourly_text">Hourly</span>
		<span title="Check for Hourly Update"><input id="hourly_check" type="checkbox" name="hourly_check" /></span>
		
		<span id="ten_day_text">10 Days</span>
		<span title="Check for 10 days update"><input id="Ten_day_check" type="checkbox" name="Ten_day_check" /></span>
		
		<input type="submit" name="get_weather" value="Get Weather" id="get_weather" />
	</form>
	<%
		if(request.getAttribute("error") != null) {
			if(request.getAttribute("error").equals("remote")) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
				out.println("ERROR RETRIEVING WEATHER DATA FROM SERVICE");
			} else if(request.getAttribute("error").equals("json")) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				out.println("No data found for given city. Make sure it's not mispelled and the correct state is selected.");
			}
		} else if(request.getAttribute("data") != null){
			if(request.getAttribute("type").equals("current")) {
				out.println("<table style=\"width:100%\" border=\"black\">");
				//print top row of table
				out.println("<tr>");
				out.println("<th>Weather</th>");
				out.println("<th>Temperature</th>");
				out.println("<th>Feels Like</th>");
				out.println("<th>Relative Humidity</th>");
				out.println("<th>Wind</th>");
				out.println("<th>Wind Direction</th>");
				out.println("</tr>");
				//print data into table
				out.println("<tr>");
				out.println("<th>" + request.getAttribute("weather") + "</th>");
				out.println("<th>" + request.getAttribute("temp") + "</th>");
				out.println("<th>" + request.getAttribute("feel") + "</th>");
				out.println("<th>" + request.getAttribute("humidity") + "</th>");
				out.println("<th>" + request.getAttribute("wind") + "</th>");
				out.println("<th>" + request.getAttribute("direction") + "</th>");
				out.println("</tr>");
				
				out.println("</table>");
			} else if(request.getAttribute("type").equals("hourly")) {
				out.println("<table style=\"width:100%\" border=\"black\">");
				//print top row of table
				out.println("<tr>");
				out.println("<th>Time</th>");
				out.println("<th>Image</th>");
				out.println("<th>Temperature</th>");
				out.println("<th>Feels Like</th>");
				out.println("<th>Relative Humidity</th>");
				out.println("<th>Prediction</th>");
				out.println("</tr>");
				//print data into table
				for(int i=0;i<10;i++) {
					out.println("<tr>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("time")))[i] + "</th>");
					out.println("<th>" + "<img src=\"" + ((String[])((Object[])request.getAttribute("image")))[i] + "\"/>" + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("temp")))[i] + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("feel")))[i] + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("humidity")))[i] + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("prediction")))[i] + "</th>");
					out.println("</tr>");
				}
				out.println("</table>");
			} else if(request.getAttribute("type").equals("daily")) {
				out.println("<table style=\"width:100%\" border=\"black\">");
				//print top row of table
				out.println("<tr>");
				out.println("<th>Day</th>");
				out.println("<th>Image</th>");
				out.println("<th>High</th>");
				out.println("<th>Low</th>");
				out.println("<th>Prediction</th>");
				out.println("</tr>");
				//print data into table
				for(int i=0;i<10;i++) {
					out.println("<tr>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("day")))[i] + "</th>");
					out.println("<th>" + "<img src=\"" + ((String[])((Object[])request.getAttribute("image")))[i] + "\"/>" + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("high")))[i] + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("low")))[i] + "</th>");
					out.println("<th>" + ((String[])((Object[])request.getAttribute("prediction")))[i] + "</th>");
					out.println("</tr>");
				}
				out.println("</table>");
			} 
		}
     %>
     
</BODY>
</HTML>

