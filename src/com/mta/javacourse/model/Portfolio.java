package com.mta.javacourse.model;
/**
 * Represent a portfolio of stocks
 * @author Gili
 *
 */
public class Portfolio {
	final static private int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private int portfolioSize;


	public Portfolio(String portName) {
		this.title = portName;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0; 
	}


	public Portfolio(Portfolio port) {
		this(port.getTitle());
		this.portfolioSize = port.getPortfolioSize(); 

		for(int i =0; i < port.portfolioSize ; i++){
			this.stocks[i] = new Stock(port.getStocks()[i]);
		}	
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

	
	/**
	 * Method that add a new stock to the stock's array
	 * @param newStock is the stock we want to add
	 */
	public void addStock(Stock newStock){
		if(newStock != null && this.portfolioSize < MAX_PORTFOLIO_SIZE){
			this.stocks[portfolioSize] = newStock ; 
			this.portfolioSize++;
		}
		else{
			System.out.println("the array is full or stock is null");
		}
	}	


	/**
	 * Method that create a string with the portfolio details
	 * @return  a string with the portfolio details
	 */
	public String getHtmlString(){
		String resStr = new String("<h1>" + this.getTitle() + "</h1>");
		for (int i = 0; i < this.portfolioSize; i++){
			Stock tempS = this.stocks[i];
			resStr+= tempS.getHtmlDescription() + "<br>";
		}
		return resStr;

	}

	
	 /**
	  * Method that erase stock from the stock's array
	  * @param symbolToErase is the title of the stock we want to erase
	  */
	public void removeStock(String symbolToErase){
		if (stocks[portfolioSize-1].getSymbol().equals(symbolToErase)){
			stocks[portfolioSize-1] = null;
			portfolioSize--;
		}
		else{
			for (int i = 0; i <this.portfolioSize; i++){
				if (this.stocks[i].getSymbol().equals(symbolToErase)){
					this.stocks[i] = this.stocks[portfolioSize-1];
					this.stocks[portfolioSize-1] = null;
					portfolioSize--;
				}
			}
		}
	}
}









