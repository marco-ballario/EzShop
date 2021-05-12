package it.polito.ezshop.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import it.polito.ezshop.data.ProductType;
import it.polito.ezshop.data.TicketEntry;

public class SaleTransaction implements it.polito.ezshop.data.SaleTransaction,  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2068771338240775445L;
	// design properties

	private List<ReturnTransaction> returnTransactions= new LinkedList<ReturnTransaction>();
	private it.polito.ezshop.model.BalanceOperation payment;
	private Integer TransactionId, transactionPoints;
	private String state;
	private String status;

	// interface properties
	private double price;
	private double discountRate;
	private List<TicketEntry> entries= new LinkedList<TicketEntry>();
	private Integer ticketNumber;
	
	public SaleTransaction() {
		this.setTransactionPoints(0);
		this.setState("open");
		this.price = 0.0;
	}

	@Override
	public Integer getTicketNumber() {
		return this.ticketNumber;
	}

	@Override
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	@Override
	public List<it.polito.ezshop.data.TicketEntry> getEntries() {
		return this.entries;
	}

	@Override
	public void setEntries(List<it.polito.ezshop.data.TicketEntry> entries) {
		this.entries = entries;		
	}

	@Override
	public double getDiscountRate() {
		return this.discountRate;
	}

	@Override
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;		
	}


	public TicketEntry addProduct(ProductType pt, int amount) {
		for(TicketEntry te: entries) {
			if(te.getBarCode().equals(pt.getBarCode())) {
				te.setAmount(te.getAmount()+amount);
				return te;
			}
		}
		TicketEntry te = new it.polito.ezshop.model.TicketEntry(pt, amount);
		this.entries.add(te);
		this.price += pt.getPricePerUnit() * amount;
		return te;
		
	}

	public List<ReturnTransaction> getReturnTransactions() {
		return returnTransactions;
	}

	public void setReturnTransactions(List<ReturnTransaction> returnTransactions) {
		this.returnTransactions = returnTransactions;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void applyProdDisc(it.polito.ezshop.data.ProductType p, double discountRate) {
		TicketEntry t=null;
		for(TicketEntry te:entries) {
			if(p.getBarCode().equals(te.getBarCode())) {
				t = te;
				t.setDiscountRate(discountRate);
			}
		}
		if(t==null) {
			return;
		}
		double price = t.getPricePerUnit()*t.getAmount();
		this.price = this.price - price;
		
		this.price = this.price + price*(1-discountRate);
		
		return;
	}

	public Integer getTransactionPoints() {
		return transactionPoints;
	}

	public void setTransactionPoints(Integer transactionPoints) {
		this.transactionPoints = transactionPoints;
	}




	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BalanceOperation getPayment() {
		return payment;
	}

	public void setPayment(it.polito.ezshop.model.BalanceOperation b) {
		this.payment = b;
	}

	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public void updateStatusMin(Integer returnId) {
		ReturnTransaction r = null;
		for(ReturnTransaction rt : returnTransactions) {
			if(rt.getReturnId()==returnId) {
				r=rt;
			}
		}
		if(r==null) {
			return;
		}
		
		this.price = this.price - r.getAmount();
		for(ProductType pt : r.getReturnProducts().keySet()) {
			for(TicketEntry t : entries) {
				if(t.getBarCode().equals(pt.getBarCode())) {
					t.setAmount(t.getAmount() - r.getReturnProducts().get(pt));
				}
			}
		}
		
	}
	
	public void updateStatusPlus(Integer returnId) {
		ReturnTransaction r = null;
		for(ReturnTransaction rt : returnTransactions) {
			if(rt.getReturnId()==returnId) {
				r=rt;
			}
		}
		if(r==null) {
			return;
		}
		
		this.price = this.price + r.getAmount();
		for(ProductType pt : r.getReturnProducts().keySet()) {
			for(TicketEntry t : entries) {
				if(t.getBarCode().equals(pt.getBarCode())) {
					t.setAmount(t.getAmount() + r.getReturnProducts().get(pt));
				}
			}
		}
		
	}

	public boolean removeProducts(String productCode, int amount) {
		for (TicketEntry t: entries){
			if(t.getBarCode().equals(productCode)) {
				if(t.getAmount()<amount) {
					return false;
				}
				t.setAmount(t.getAmount()-amount);
				break;
			}
		}
		return true;
	}

	public TicketEntry getTicketEntry(String productCode) {
		for(TicketEntry t : entries) {
			if(t.getBarCode().equals(productCode)) {
				return t;
			}
		}
		return null;
	}


	

	
	
	
	
	
}
