package com.mta.javacourse;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date; 
import java.util.Calendar;

@SuppressWarnings("serial")

public class StockDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 10, 15);
			Date date1= cal.getTime();
			Date date2= cal.getTime();
			Date date3= cal.getTime();
			
			Stock stock1, stock2, stock3;
			stock1= new Stock ("PIH", 13.1f, 12.4f,date1);
			stock2= new Stock("AAL",5.78f,5.5f,date2);
			stock3= new Stock("CAAS",32.2f,31.5f,date3);
			
			resp.getWriter().println(stock1.getHtmlDescription()) ;
			resp.getWriter().println(stock2.getHtmlDescription()) ;
			resp.getWriter().println(stock3.getHtmlDescription()) ;
	}
}

