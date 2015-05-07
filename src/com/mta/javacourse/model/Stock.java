package com.mta.javacourse.model;
import java.util.Date; 

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Represent a stock
 * @author Gili
 *
 */

public class Stock {
	private String symbol ;
	private float ask;
	private float bid;
	private Date date ;
	private String stockDetails; 
	private int recommendation;
	private int stockQuantity;
	final static private int BUY = 0;
	final static private int SELL = 1;
	final static private int REMOVE = 2;
	final static private int HOLD = 3;
	

	public Stock() {
		this.symbol = null;
		this.ask = 0;
		this.bid = 0;
		this.date = null;
		this.stockDetails = null;
		this.recommendation = 0;
		this.stockQuantity = 0;
	}
	
	public Stock(String symbol, float ask, float bid, Date date){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.recommendation = 0;
		this.stockQuantity = 0;
	}
	
	public Stock(Stock stock1){
		this(new String (stock1.getSymbol()),stock1.getAsk(),stock1.getBid(), new Date(stock1.getDate().getTime()));
		this.recommendation = stock1.getRecommendation();
		this.stockQuantity = stock1.getStockQuantity();
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
	
	public void setRecommendation(int recomend){
		this.recommendation = recomend;
	}
	public int getRecommendation(){
		return this.recommendation;
	}
	
	public void setStockQuantity(int quantity){
		this.stockQuantity = quantity;
	}
	public int getStockQuantity(){
		return this.stockQuantity;
	}
	
	///Methods
	
	
	/**
	 * Method that create a string with stock's details
	 * @return a string with stock's details
	 */
	public String getHtmlDescription(){
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = df.format(getDate());
	
		this.stockDetails = "<b>Stock symbol </b>: "+getSymbol()+" <b>Stock ask </b>: "+getAsk()+" <b>Stock bid </b>: "+getBid()+ " <b>Stock date </b>:" +dateStr;
		return this.stockDetails;
	}	
}

