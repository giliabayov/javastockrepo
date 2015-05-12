package com.mta.javacourse.model;
/**
 * Represent a portfolio of stocks
 * @author Gili
 *
 */


public class Portfolio {
	public enum ALGO_RECOMMENDATION{
		BUY, SELL, REMOVE, HOLD; 
	}
	final static private int MAX_PORTFOLIO_SIZE = 5;
	final static private int ALL = -1;
	private String title;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance = 0;

/**
 *  The class constractor
 * @param portName -the protfolio title
 */
	
	public Portfolio(String portName) {
		this.title = portName;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0; 
		this.balance = 0 ;
	}

/**
 *  The copy constractor
 * @param port-the original portfolio
 */
	
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

	public float getBalance(){
		return this.balance;
	}


	/**
	 * Method that add a new stock to the stock's array
	 * @param newStock is the stock we want to add
	 */
	public void addStock(Stock newStock){
		boolean flag = true;
		if(newStock != null && this.portfolioSize < MAX_PORTFOLIO_SIZE){
			for (int i = 0 ; i< this.portfolioSize ; i++){
				if (this.stocks[i].getSymbol().equals(newStock.getSymbol())){
					flag = false;
					return;
				}
			}
			if (flag){
				this.stocks[portfolioSize] = newStock ; 
				this.stocks[portfolioSize].setStockQuantity(0);
				this.portfolioSize++;
			}
		}
		else if(this.portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("Can’t add new stock, portfolio can have only"+MAX_PORTFOLIO_SIZE+ "stocks");
		}
		else{
			System.out.println("the stock is null");
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
		resStr += "Total Portfolio Value: "+this.getTotalValue() +"$ "+ " Total Stock Value: " + this.getStocksValue()+"$ "+ " Balance: "+ this.getBalance()+"$";
		return resStr;
	}


	/**
	 * Method that erase stock from the stock's array
	 * @param symbolToErase is the title of the stock we want to erase
	 */
	public boolean removeStock(String symbolToErase){
		boolean result = false; 
		if (stocks[portfolioSize-1].getSymbol().equals(symbolToErase)){
			result = this.sellStock(symbolToErase, ALL);
			if (result){
				stocks[portfolioSize-1] = null;
				portfolioSize--;
			}
			else{
				System.out.println("stock doesn't remove");
			}

		}
		else{
			for (int i = 0; i <this.portfolioSize; i++){
				if (this.stocks[i].getSymbol().equals(symbolToErase)){
					result = this.sellStock(symbolToErase, ALL);
					if (result){
						this.stocks[i] = this.stocks[portfolioSize-1];
						this.stocks[portfolioSize-1] = null;
						portfolioSize--;
					}
					else{
						System.out.println("stock doesn't remove");
					}
				}
			}
		}
		return result;
	}
	/**
	 * A method that sell stock
	 * @param symbolToSell - the stock symbol you want sell
	 * @param quantity- the quantity you want to sell 
	 * @return boolean if method succeed or not
	 */
	public boolean sellStock (String symbolToSell, int quantity){
		boolean result = true;
		float newBalance = 0;
		int newQuantity = 0;
		if (quantity < -1){
			System.out.println("the quantity is invalid");
		}
		else if (symbolToSell == null){
			System.out.println("Symbol is null");
		}
		else{
			for(int i = 0; i < this.portfolioSize ; i++ ){
				if(symbolToSell.equals(this.stocks[i].getSymbol())){

					if(quantity > this.stocks[i].getStockQuantity()){
						System.out.println("Not enough stocks to sell");
						return result = false;
					}
					else if(quantity == ALL){
						newBalance = (this.stocks[i].getBid() * this.stocks[i].getStockQuantity());
						this.stocks[i].setStockQuantity(0);					
						this.updateBalance(newBalance);
					}
					else{
						newBalance = this.stocks[i].getBid()*quantity;
						this.updateBalance(newBalance);
						newQuantity = (this.stocks[i].getStockQuantity() - quantity);
						this.stocks[i].setStockQuantity(newQuantity);	
					}
				}
			}
		}
		return result;
	}

	/**
	 * Method that update portfolio balance
	 * @param amount - the amount you want to add / reduce
	 */

	public void updateBalance(float amount){
		float sum = this.getBalance();
		sum += amount;

		if (sum > 0){
			this.balance += amount;
		}
		else{
			System.out.println("the balance is negative- enter new amount");
		}
	}

	/**
	 * Method that calculate the value of stocks in the portfolio
	 * @return - the value of the stocks
	 */
	public float getStocksValue(){
		float totalValue = 0;
		for (int i = 0 ; i< this.portfolioSize; i++){
			totalValue += this.stocks[i].getBid() * this.stocks[i].getStockQuantity() ;
		}
		return totalValue;	
	}

	/**
	 *  Sum the all stocks value and portfolio’s balance
	 * @return the total amount of the sum
	 */

	public float getTotalValue(){
		float res = 0; 
		res = this.getStocksValue() + this.getBalance();
		return res;
	}

	/**
	 * Buy a new stock or quantity from an existing stock and add's it to the portfolio
	 * @param stock - the stock chosen to be bought
	 * @param quantity - Number of stocks to buy
	 * @return indicating operation success/fail
	 */
	public boolean buyStock(Stock stock, int quantity){

		boolean res = false;
		float price = 0 ;
		int quantityToBuy = 0;
		int i = 0; 
		for(i = 0 ; i <this.portfolioSize ; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())){
				if (quantity == ALL){
					price = this.stocks[i].getAsk();
					quantityToBuy = (int) (this.getBalance() / price);
					if (quantityToBuy < 1){
						System.out.println("Not enough balance to complete purchase");
						return res;
					}
					else{
						price += (price * quantityToBuy);
						this.updateBalance(-(price));
						this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity() + quantityToBuy);
						res = true;
						return res;
					}
				}
				else{
					quantityToBuy = quantity;
					price = this.stocks[i].getAsk() * quantityToBuy;
					if (price > this.balance){
						System.out.println("Not enough balance to complete purchase");
						return res;
					}
					else{
						this.updateBalance(-(price));						
						this.stocks[i].setStockQuantity((this.stocks[i].getStockQuantity() + quantityToBuy));
						return res = true;
					}
				}
			}

		}
		if(i == MAX_PORTFOLIO_SIZE){
			System.out.println("the is not enough space in the array");
		}
		else {
			if (quantity == ALL){
				price = stock.getAsk();
				quantityToBuy = (int) (this.getBalance() / price);
				if (quantityToBuy < 1){
					System.out.println("Not enough balance to complete purchase");
					return res;
				}
				else{
					price += (price * quantityToBuy);
					this.updateBalance(-(price));					
					this.addStock(stock);
					stock.setStockQuantity((stock.getStockQuantity() + quantityToBuy));
					res = true;
					return res;
				}
			}
			else{
				quantityToBuy = quantity;
				price = (stock.getAsk() * quantityToBuy);
				if (price > this.balance){
					System.out.println("Not enough balance to complete purchase");
					return res;
				}
				else{
					this.updateBalance(-(price));					
					this.addStock(stock);
					stock.setStockQuantity(stock.getStockQuantity() + quantityToBuy);
					res = true;
					return res;
				}
			}
		}
		return false;
	}

}









