package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.Stock;

public class Portfolio {
	final static private int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private int portfolioSize;


	public Portfolio() {
		this.title = null;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0; 
	}


	public void addStock(Stock newStock){
		if(newStock != null && this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocks[portfolioSize] = newStock ; 
			this.portfolioSize++;
		}
		else{
			System.out.println("the array is full or stock is null");
		}
		
	}	
	
	public String getHtmlString(){
		String portHtmlCode = "<h1>"+title+ " portfolio"+ "</h1><br>";
		for (int i=0; i<this.portfolioSize; i++) {
			portHtmlCode += this.stocks[i].getHtmlDescription();	
		}
		return portHtmlCode;
	}
	
	//Getters&Setters//
	
	public void setTitle(String newTitle){
		this.title = newTitle;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setStock(Stock[] newStock){
		this.stocks = newStock;
	}
	public Stock[] getStocks(){
		return this.stocks;
	}
	
	public void setPortfolioSize(int size){
		this.portfolioSize = size;
	}
	public int getPortfolioSize(){
		return this.portfolioSize; 
	}
		
		
}
	
	



