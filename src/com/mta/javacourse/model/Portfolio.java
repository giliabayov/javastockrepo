package com.mta.javacourse.model;
import org.algo.dto.PortfolioTotalStatus;
import org.algo.exception.PortfolioException;
import org.algo.model.*;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;

/**
 * Represent a portfolio of stocks
 * @author Gili
 *
 */

public class Portfolio implements PortfolioInterface {

	final static private int MAX_PORTFOLIO_SIZE = 5;
	final static private int ALL = -1;

	public enum ALGO_RECOMMENDATION{
		BUY, SELL, REMOVE, HOLD; 
	}

	private String title;
	private StockInterface[] stocks;
	private int portfolioSize;
	private float balance = 0;

	/**
	 * The class c'tor
	 */

	public Portfolio() {
		this.title = null;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0; 
		this.balance = 0 ;
	}
	/**
	 *  The class constructor with string
	 * @param portName -the portfolio title
	 */
	public Portfolio(String portName) {
		this.title = portName;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0; 
		this.balance = 0 ;
	}

	/**
	 * Copy constructor by stock array
	 * @param stockArray - the array we want to copy
	 */

	public Portfolio(StockInterface[] stockArray) {
		this.title = new String("temp title");
		this.portfolioSize = stockArray.length;
		this.balance = 0;
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i<this.portfolioSize; i++){
			this.stocks[i]= new Stock ((Stock)stockArray[i]);
		}
	}

	/**
	 *  Copy constructor by portfolio
	 * @param port-the original portfolio
	 */

	public Portfolio(Portfolio port) {
		this(port.getTitle());
		this.portfolioSize = port.getPortfolioSize(); 

		for(int i =0; i < port.portfolioSize ; i++){
			this.stocks[i] = new Stock((Stock)port.getStocks()[i]);
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
	public StockInterface[] getStocks(){
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

	public static int getMaxSize() {

		return MAX_PORTFOLIO_SIZE;
	}

	/**
	 * Method that add a new stock to the stock's array
	 * @param newStock is the stock we want to add
	 */

	public void addStock(Stock newStock) throws StockAlreadyExistsException, PortfolioFullException , ExceptionInInitializerError{

		if(newStock != null && this.portfolioSize < MAX_PORTFOLIO_SIZE){
			for (int i = 0 ; i< this.portfolioSize ; i++){
				if (this.stocks[i].getSymbol().equals(newStock.getSymbol())){
					throw new StockAlreadyExistsException(this.stocks[i].getSymbol());
				}
			}
			this.stocks[portfolioSize] = newStock ; 
			((Stock)this.stocks[portfolioSize]).setStockQuantity(0);
			this.portfolioSize++;
			System.out.println("the stock was add successfuly");
		}

		else if(this.portfolioSize >= MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}
		else{
			throw new ExceptionInInitializerError("the stock is null");
		}
	}



	/**
	 * Method that create a string with the portfolio details
	 * @return  a string with the portfolio details
	 */
	public String getHtmlString(){
		String resStr = new String("<h1>" + this.getTitle() + "</h1>");
		for (int i = 0; i < this.portfolioSize; i++){
			Stock tempS = ((Stock)this.stocks[i]);
			resStr+= tempS.getHtmlDescription() + "<br>";
		}
		resStr += "Total Portfolio Value: "+this.getTotalValue() +"$ "+ " Total Stock Value: " + this.getStocksValue()+"$ "+ " Balance: "+ this.getBalance()+"$";
		return resStr;
	}


	/**
	 * Method that erase stock from the stock's array
	 * @param symbolToErase is the title of the stock we want to erase
	 */
	public void removeStock(String symbolToErase) throws ExceptionInInitializerError,Exception {
		if (symbolToErase == null){
			throw new ExceptionInInitializerError("the symbol of the stock that you want to remove is null");
		}
		else if (stocks[portfolioSize-1].getSymbol().equals(symbolToErase)){
			try {
				this.sellStock(symbolToErase, ALL);
			} catch (Exception e) {
				System.out.println("Stock does not remove");
			}
			stocks[portfolioSize-1] = null;
			portfolioSize--;
		}
		else{
			for (int i = 0; i <this.portfolioSize; i++){
				if (this.stocks[i].getSymbol().equals(symbolToErase)){
					try{
						this.sellStock(symbolToErase, ALL);
					}catch(Exception e){
						System.out.println("Stock doesn not remove");
					}
					this.stocks[i] = this.stocks[portfolioSize-1];
					this.stocks[portfolioSize-1] = null;
					portfolioSize--;
				}
			}
		}
	}



	/**
	 * A method that sell stock
	 * @param symbolToSell - the stock symbol you want sell
	 * @param quantity- the quantity you want to sell 
	 * @return boolean if method succeed or not
	 */
	public void sellStock (String symbolToSell, int quantity) throws Exception, ExceptionInInitializerError {

		float newBalance = 0;
		int newQuantity = 0;
		if (quantity < -1){
			throw new Exception("the quantity is invalid");
		}
		else if (symbolToSell == null){
			throw new ExceptionInInitializerError("Symbol is null");
		}
		else{
			for(int i = 0; i < this.portfolioSize ; i++ ){
				if(symbolToSell.equals(this.stocks[i].getSymbol())){

					if(quantity > ((Stock)this.stocks[i]).getStockQuantity()){
						throw new Exception("Not enough stocks to sell");
					}
					else if(quantity == ALL){
						newBalance = (this.stocks[i].getBid() * ((Stock)this.stocks[i]).getStockQuantity());
						((Stock)this.stocks[i]).setStockQuantity(0);					
						this.updateBalance(newBalance);
					}
					else{
						newBalance = this.stocks[i].getBid()*quantity;
						this.updateBalance(newBalance);
						newQuantity = (((Stock)this.stocks[i]).getStockQuantity() - quantity);
						((Stock)this.stocks[i]).setStockQuantity(newQuantity);	
					}
				}
			}
		}
	}


	/**
	 * Method that update portfolio balance
	 * @param amount - the amount you want to add / reduce
	 */

	public void updateBalance(float amount) throws BalanceException{
		float sum = this.getBalance();
		sum += amount;

		if (sum >= 0){
			this.balance += amount;
		}
		else{
			throw new BalanceException();
		}
	}


	/**
	 * Method that calculate the value of stocks in the portfolio
	 * @return - the value of the stocks
	 */
	public float getStocksValue(){
		float totalValue = 0;
		for (int i = 0 ; i< this.portfolioSize; i++){
			totalValue += this.stocks[i].getBid() * ((Stock)this.stocks[i]).getStockQuantity() ;
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


	public void buyStock(Stock stock, int quantity) throws BalanceException, StockAlreadyExistsException, PortfolioFullException, ExceptionInInitializerError{
		boolean flag = false;
		float price = 0 ;
		int quantityToBuy = 0;
		int i = 0; 
		for(i = 0 ; i <this.portfolioSize ; i++){
			if (this.stocks[i].getSymbol().equals(stock.getSymbol())){
				flag = true;
				if (quantity == ALL){
					price = this.stocks[i].getAsk();
					quantityToBuy = (int) (this.getBalance() / price);
		
					if (quantityToBuy < 1){
						throw new BalanceException();
					}
					else{
						this.updateBalance(-(price * quantityToBuy));
						((Stock)this.stocks[i]).setStockQuantity(((Stock)this.stocks[i]).getStockQuantity() + quantityToBuy);
					}
				}
				else{
					quantityToBuy = quantity;
					price = this.stocks[i].getAsk() * quantityToBuy;
					if (price > this.balance){
						throw new BalanceException();
					}
					else{
						this.updateBalance(-(price));	
						((Stock)this.stocks[i]).setStockQuantity(((Stock)this.stocks[i]).getStockQuantity() + quantityToBuy);

					}
				}
			}

		}
		if(i > MAX_PORTFOLIO_SIZE){
			throw new PortfolioFullException();
		}
		else if (!flag) {
			if (quantity == ALL){
				price = stock.getAsk();
				quantityToBuy = (int) (this.getBalance() / price);
				if (quantityToBuy < 1){
					throw new BalanceException();
				}
				else{
					this.updateBalance(-(price * quantityToBuy));					
					this.addStock(stock);
					stock.setStockQuantity((stock.getStockQuantity() + quantityToBuy));
				}
			}
			else{
				quantityToBuy = quantity;
				price = (stock.getAsk() * quantityToBuy);
				if (price > this.balance){
					throw new BalanceException();

				}
				else{
					this.updateBalance(-(price));
					this.addStock(stock);
					stock.setStockQuantity(stock.getStockQuantity() + quantityToBuy);	
				}
			}
		}

	}


	/**
	 * Method that check if a specific stock exist in the array
	 * @param symbol- the stock's name
	 * @return stockInterface
	 */


	public StockInterface findStock(String symbol) {

		for (int i = 0; i < getPortfolioSize(); i++) {
			if (getStocks()[i].getSymbol().equals(symbol)){
				return this.getStocks()[i];
			}
		}
		return null;
	}

}










