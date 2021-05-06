package it.polito.ezshop.model;

import java.io.Serializable;

public class Order implements it.polito.ezshop.data.Order, Serializable{
	
	private String productCode;
	private int quantity;
	private double pricePerUnit;
	private Integer orderId;
	private String state;
	private BalanceOperation payment;
	
	

	public Order(String product, int quantity, double pricePerUnit) {
		super();
		this.productCode = product;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public Integer getBalanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBalanceId(Integer balanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProductCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProductCode(String productCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPricePerUnit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPricePerUnit(double pricePerUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQuantity(int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getOrderId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		
	}

}
