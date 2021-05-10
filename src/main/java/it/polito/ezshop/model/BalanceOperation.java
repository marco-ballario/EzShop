package it.polito.ezshop.model;

import java.io.Serializable;
import java.time.LocalDate;

public class BalanceOperation implements it.polito.ezshop.data.BalanceOperation, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6909291975684258125L;
	private Integer balanceId;
	private LocalDate date;
	private String type;
	private double money;
	
	

	@Override
	public int getBalanceId() {
		return balanceId;
	}
	@Override
	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public double getMoney() {
		return money;
	}
	@Override
	public void setMoney(double money) {
		this.money = money;
	}
	
	

}
