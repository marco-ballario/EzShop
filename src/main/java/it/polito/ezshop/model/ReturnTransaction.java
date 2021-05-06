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
	private HashMap<Integer, ProductType> returnProducts;
	private SaleTransaction originalTransaction;
	private boolean committed;
	private BalanceOperation payment;
	private double amount;
	
	public ReturnTransaction(Integer returnId, SaleTransaction s) {
		this.returnId = returnId;
		this.originalTransaction = s;
		this.committed=false;
	}
	
	
	
	
	

	
	
}
