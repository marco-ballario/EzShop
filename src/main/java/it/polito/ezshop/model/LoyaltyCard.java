package it.polito.ezshop.model;

import java.io.Serializable;

public class LoyaltyCard  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1820432779123913921L;
	private it.polito.ezshop.data.Customer customer;
	private Integer points;
	private String code;
	
	
	
	public LoyaltyCard() {
		this.points=0;
	}
	
	public it.polito.ezshop.data.Customer getCustomer() {
		return customer;
	}
	public void setCustomer(it.polito.ezshop.data.Customer c) {
		this.customer = c;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
