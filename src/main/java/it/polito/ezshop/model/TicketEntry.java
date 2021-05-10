package it.polito.ezshop.model;

import java.io.Serializable;

import it.polito.ezshop.data.ProductType;

public class TicketEntry implements it.polito.ezshop.data.TicketEntry, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159303808059742186L;
	private String barCode, productDescription;
	private double discountRate, pricePerUnit;
	private int amount;
	private double price = amount * pricePerUnit;
	
	public TicketEntry(ProductType p, int amount) {
		this.barCode = p.getBarCode();
		this.productDescription = p.getProductDescription();
		this.pricePerUnit = p.getPricePerUnit();
		this.amount = amount;
		this.discountRate = 0;
	}
	
	@Override
	public String getBarCode() {
		return this.barCode;
	}

	@Override
	public void setBarCode(String barCode) {
		this.barCode = barCode;		
	}

	@Override
	public String getProductDescription() {
		return this.productDescription;
	}

	@Override
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public int getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	@Override
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public double getDiscountRate() {
		return this.discountRate;
	}

	@Override
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
