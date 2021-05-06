package it.polito.ezshop.model;

import java.io.Serializable;

public class Customer implements it.polito.ezshop.data.Customer, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1851008845939686219L;
	private String name;
	private Integer customerId;
	private String card;
	private Integer points;
	
	

	public Customer(String name) {
		this.name = name;
		this.card = null;
	}

	@Override
	public String getCustomerName() {
		return name;
	}

	@Override
	public void setCustomerName(String customerName) {
		this.name = customerName;
		
	}

	@Override
	public String getCustomerCard() {
		return card;
	}

	@Override
	public void setCustomerCard(String customerCard) {
		if(customerCard == null) {
			this.card = null;
		}
		else {
			this.card = customerCard;
		}
	}


	@Override
	public Integer getId() {
		return customerId;
	}

	@Override
	public void setId(Integer id) {
		this.customerId = id;
		
	}

	@Override
	public Integer getPoints() {
		return points;
	}

	@Override
	public void setPoints(Integer points) {
		this.points = points;
		
	}

}
