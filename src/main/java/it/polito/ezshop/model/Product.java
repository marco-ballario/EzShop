package it.polito.ezshop.model;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7213361125315629523L;
	private Long RFID;
	private ProductType productType;
	
	
	public Product(Long rFID, ProductType productType) {
		RFID = rFID;
		this.productType = productType;
	}
	
	public Long getRFID() {
		return RFID;
	}
	public void setRFID(Long rFID) {
		RFID = rFID;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	

}
