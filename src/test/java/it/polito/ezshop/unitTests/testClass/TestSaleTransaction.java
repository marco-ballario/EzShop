package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.SaleTransaction;

public class TestSaleTransaction {
	SaleTransaction st = new SaleTransaction();
	
	@Test
	public void testSaleConstructor() {
		assertEquals(st.getTransactionPoints(), (Integer)0);
		assertEquals(st.getStatus(), "open");
		assertEquals(st.getPrice(), (Double)0.0, 0.1);
	}
	
	
	@Test
	public void testSetTicketNum() {
		st.setTicketNumber(1);
		assertEquals(st.getTicketNumber(), (Integer)1);
	}
	
	@Test
	public void testSetDiscount() {
		st.setDiscountRate(0.1);
		assertEquals(st.getDiscountRate(), (float)0.1, 0.1);
	}
	
	
	@Test
	public void testSetPrice() {
		st.setPrice(1.0);
		assertEquals(st.getPrice(), (Double)1.0, 0.1);
	}
	
	@Test
	public void testSetStatus() {
		st.setStatus("closed");
		assertEquals(st.getStatus(), "closed");
	}
	
	@Test
	public void testSetPoints() {
		st.setTransactionPoints(100);
		assertEquals(st.getTransactionPoints(), (Integer)100);
	}
	
	@Test
	public void testSetTransactionId() {
		st.setTransactionId(3);
		assertEquals(st.getTransactionId(), (Integer)3);
	}
}
