package it.polito.ezshop.model;

public class ProductType implements it.polito.ezshop.data.ProductType {

	private String description, barCode, note;
	private Integer productID, quantity;
	private double pricePerUnit;
	

	public ProductType(String description, String barCode, double pricePerUnit, String note) {
		super();
		this.description = description;
		this.barCode = barCode;
		this.note = note;
		this.pricePerUnit = pricePerUnit;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNote(String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProductDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProductDescription(String productDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBarCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBarCode(String barCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getPricePerUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPricePerUnit(Double pricePerUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
