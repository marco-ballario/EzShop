package it.polito.ezshop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import it.polito.ezshop.data.BalanceOperation;

public class AccountBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9164632041456229108L;
	private static AccountBook istance=null;
	private double balance;
	private LinkedList<BalanceOperation> operationList = new LinkedList<BalanceOperation>();
	private int balanceId=1;
	
	
	private AccountBook() {
		this.balance = 0;
	}
	
	public static AccountBook getIstance() {
	    if(istance==null)
	      istance = new AccountBook();
	    return istance;
	  }
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LinkedList<BalanceOperation> getOperationList() {
		return operationList;
	}
	public void setOperationList(LinkedList<BalanceOperation> operationList) {
		this.operationList = operationList;
	}

	public boolean insertBalanceOperation(BalanceOperation bo, double toBeAdded) {
		if(toBeAdded + this.balance < 0) {
			return false;
		}
		bo.setBalanceId(balanceId);
		balanceId ++;
		bo.setDate(LocalDate.now());
		if(toBeAdded >= 0) {
			bo.setType("CREDIT");
		}
		else {
			bo.setType("DEBIT");
		}
		bo.setMoney(toBeAdded);
		this.balance = this.balance + toBeAdded;

		this.operationList.add(bo);
		return true;
		
	}

	
	
}
