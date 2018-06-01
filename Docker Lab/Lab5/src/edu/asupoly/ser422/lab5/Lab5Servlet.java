/*
 * Lab5Servlet.java
 *
 * Copyright:  2008 Kevin A. Gary All Rights Reserved
 *
 */
package edu.asupoly.ser422.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Kevin Gary
 *
 */
@SuppressWarnings("serial")
public class Lab5Servlet extends HttpServlet {
	public String gradeURL;
	public String letterURL;

	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		gradeURL = sc.getInitParameter("gradeURL");
		letterURL = sc.getInitParameter("letterURL");
		System.out.println("gradeURL: " + gradeURL);
		System.out.println("letterURL: " + letterURL);

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		double grade = getGrade(req, res);
		String letter = mapToLetter(req, res, Double.toString(grade));
		StringBuffer pageBuf = new StringBuffer();
		pageBuf.append("Grade: " + Double.toString(grade) +"<BR>");
		pageBuf.append("Letter: " + letter +"<BR>");

		res.setContentType("text/html");
		res.getWriter().write(pageBuf.toString());
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public double getGrade(HttpServletRequest req, HttpServletResponse res) {
		String grade = "";
		String urlString = gradeURL;
		String year = req.getParameter("year");
		String subject = req.getParameter("subject");
		urlString = urlString.concat("?year="+year);
		urlString = urlString.concat("&subject="+subject);
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  //connecting to url
			conn.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  //stream to resource
			String str;
			while ((str = in.readLine()) != null)   //reading data
			   grade += str+"\n";//process the response and save it in some string or so
			in.close();  //closing stream
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Double(grade).doubleValue();
	}

	public String mapToLetter(HttpServletRequest req, HttpServletResponse res, String grade) {
		String letter = "";
		String urlString = letterURL;
		urlString = urlString.concat("?grade="+grade);
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  //connecting to url
			conn.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  //stream to resource
			String str;
			while ((str = in.readLine()) != null)   //reading data
			   letter += str+"\n";//process the response and save it in some string or so
			in.close();  //closing stream
		} catch (Exception e) {
			e.printStackTrace();
		}

		return letter;
	}
}
