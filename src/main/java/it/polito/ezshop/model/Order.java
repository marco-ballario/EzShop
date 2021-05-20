package it.polito.ezshop.model;

import java.io.Serializable;

public class Order implements it.polito.ezshop.data.Order, Serializable{
	
	
	private static final long serialVersionUID = -2649617442756178065L;
	private String productCode;
	private int quantity;
	private double pricePerUnit;
	private Integer orderId;
	private String state;
	private BalanceOperation payment;
	
	

	public Order(String product, int quantity, double pricePerUnit) {
		this.productCode = product;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public Integer getBalanceId() {
		return payment.getBalanceId();
	}

	@Override
	public void setBalanceId(Integer balanceId) {
		this.payment.setBalanceId(balanceId);
		
	}

	@Override
	public String getProductCode() {
		return this.productCode;
	}

	@Override
	public void setProductCode(String productCode) {
		this.productCode = productCode;
		
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
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}

	@Override
	public String getStatus() {
		
		return state;
	}

	@Override
	public void setStatus(String status) {
		this.state = status;
		
	}

	@Override
	public Integer getOrderId() {
		
		return this.orderId;
	}

	@Override
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
		
	}

}
