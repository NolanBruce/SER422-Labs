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
public class MapToLetterServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = null;
		double grade = new Double(req.getParameter("grade"));
		Lab5Service service = null;
		try {
			service = Lab5Service.getService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (service == null) {
			out = res.getWriter();
			out.println("SERVICE NOT AVAILABLE");
		} else {
			res.setContentType("text/plain");
			out = res.getWriter();

			out.println(service.mapToLetterGrade(grade));
		}

		// some generic setup - our content type and output stream
		out.close();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
