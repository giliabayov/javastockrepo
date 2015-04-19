package com.mta.javacourse;
import java.util.Date; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock {
	private String symbol ;
	private float ask;
	private float bid;
	private Date date ;
	private String stockDetails; 
	
	
	
	
	public Stock(String symbol, float ask, float bid, Date date){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
	}
	
	//Getters&Setters//
	public void setSymbol (String symbol1){
		this.symbol = symbol1;
	}
	public String getSymbol (){
	return this.symbol;
	}
	
	public void setAsk (float ask1){
		this.ask = ask1;
	}
	public float getAsk (){
	return this.ask;
	}
	
	
	public void setBid (float bid1){
		this.bid = bid1;
	}
	public float getBid (){
	return this.bid;
	}
	
	public void setDate (Date date1){
		this.date = date1;
	}
	public Date getDate (){
	return this.date;
	}
	
	public String getHtmlDescription(){
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = df.format(getDate());
	
		this.stockDetails = "<b>Stock symbol </b>: "+getSymbol()+" <b>Stock ask </b>: "+getAsk()+" <b>Stock bid </b>: "+getBid()+ " <b>Stock date </b>:" +dateStr +"<br>";
		return this.stockDetails;
	}
	
	
}

