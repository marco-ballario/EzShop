package it.polito.ezshop.model;

import java.io.Serializable;
import java.util.HashMap;

import it.polito.ezshop.data.SaleTransaction;

public class ReturnTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4414797098282122636L;
	private Integer returnId;
	private HashMap<ProductType, Integer> returnProducts;
	private it.polito.ezshop.model.SaleTransaction originalTransaction;
	private boolean committed;
	private BalanceOperation payment;
	private double amount;

	
	public ReturnTransaction(Integer returnId, it.polito.ezshop.model.SaleTransaction s) {
		this.returnId = returnId;
		this.originalTransaction = s;
		this.committed=false;
		this.amount = 0.0;
	}

	public Integer getReturnId() {
		return returnId;
	}

	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}

	public HashMap<ProductType, Integer> getReturnProducts() {
		return returnProducts;
	}

	public void setReturnProducts(HashMap<ProductType, Integer> returnProducts) {
		this.returnProducts = returnProducts;
	}

	public it.polito.ezshop.model.SaleTransaction getOriginalTransaction() {
		return originalTransaction;
	}

	public void setOriginalTransaction(it.polito.ezshop.model.SaleTransaction originalTransaction) {
		this.originalTransaction = originalTransaction;
	}

	public boolean isCommitted() {
		return committed;
	}

	public void setCommitted(boolean committed) {
		this.committed = committed;
	}

	public BalanceOperation getPayment() {
		return payment;
	}

	public void setPayment(BalanceOperation payment) {
		this.payment = payment;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void addProduct(ProductType product, int amount) {
		this.returnProducts.put(product, this.returnProducts.get(product) + amount);
		this.amount += product.getPricePerUnit() * amount;
		// how to keep track of each product quantity here ?
	}
	
	
	

	
	
}
