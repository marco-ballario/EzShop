package it.polito.ezshop.model;

public class Customer implements it.polito.ezshop.data.Customer{
	private String name;
	private Integer customerId;
	private LoyaltyCard card;
	
	

	public Customer(String name) {
		this.name = name;
		this.card = null;
	}

	@Override
	public String getCustomerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomerName(String customerName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCustomerCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomerCard(String customerCard) {
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

	@Override
	public Integer getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPoints(Integer points) {
		// TODO Auto-generated method stub
		
	}

}
