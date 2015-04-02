package com.mta.javacourse;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.lang.Math;
 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GiliNewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			
	
	int radius = 50;
	double areaResult= Math.pow(radius,2) * Math.PI;
	String line1 = new String("calculation 1: Area of circle with radius " +radius+" is: " +areaResult+ " square­cm" + "<br>");
	resp.getWriter().println(line1);		
	
 	
    float angle = 30;
    float hypotenuse = 50;
    double opposite = hypotenuse * Math.sin(Math.toRadians(angle));
    String line2 = new String("calculation 2: Length of opposite where angle is 30 degrees and Hypotenuse length is 50 cm is: " +opposite+ " cm" + "<br>");
    resp.getWriter().println(line2);
    
    
    int base = 20;
    int exp = 13;
    long powerResult = (int)Math.pow(base,exp);
    String line3 = new String("calculation 3: Power of 20 with exp of 13 is: " +powerResult );
    resp.getWriter().println(line3) ;
	}

} 

