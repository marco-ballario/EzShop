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
	private LoyaltyCard lc;
	

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
		if(lc==null) {
			return 0;
		}
		return lc.getPoints();
	}

	@Override
	public void setPoints(Integer points) {
		if(this.lc != null) {
			this.lc.setPoints(points);
		}
	}

	public LoyaltyCard getLoyaltyCard() {
		return lc;
	}

	public void setLoyaltyCard(LoyaltyCard lc) {
		this.setCustomerCard(lc.getCode());
		this.lc = lc;
	}
	
	
	

}
