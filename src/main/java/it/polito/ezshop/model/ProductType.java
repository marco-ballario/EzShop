package it.polito.ezshop.model;

import java.io.Serializable;

public class ProductType implements it.polito.ezshop.data.ProductType, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127825657944190527L;
	private String description, barCode, note;
	private Integer productID, quantity;
	private double pricePerUnit;
	private String location;
	

	public ProductType(String description, String barCode, double pricePerUnit, String note) {
		this.description = description;
		this.barCode = barCode;
		this.note = note;
		this.pricePerUnit = pricePerUnit;
		this.location="";
		this.quantity=0;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String getNote() {
		return this.note;
	}

	@Override
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String getProductDescription() {
		return this.description;
	}

	@Override
	public void setProductDescription(String productDescription) {
		this.description = productDescription;
		
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
	public Double getPricePerUnit() {
		return this.pricePerUnit;
	}

	@Override
	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public Integer getId() {
		return this.productID;
	}

	@Override
	public void setId(Integer id) {
		this.productID = id;	
	}

	@Override
	public String toString() {
		return "ProductType [description=" + description + ", barCode=" + barCode + ", note=" + note + ", productID="
				+ productID + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit + ", location=" + location
				+ "]";
	}

	
	

	
}
