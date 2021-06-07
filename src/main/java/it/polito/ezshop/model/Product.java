package it.polito.ezshop.model;

public class Product {
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
