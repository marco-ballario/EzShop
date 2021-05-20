package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.TicketEntry;

public class TestTicketEntry {
	
	ProductType pt = new ProductType("Cereali", "12345678901231", 12.2, "molto buoni");
	TicketEntry te = new TicketEntry(pt, 10);
	
	@Test
	public void testTicketEntryConstructor() {
		assertEquals(te.getBarCode(), "12345678901231");
		assertEquals(te.getProductDescription(), "Cereali");
		assertEquals(te.getPricePerUnit(), 12.2, 0);
		assertEquals(te.getAmount(), 10);
		assertEquals(te.getDiscountRate(), 0, 0);
		assertEquals(te.getPrice(), 12.2 * 10, 0);
	}
	
	@Test
	public void testSetBarCode() {
		te.setBarCode("12345678901231");
		assertEquals(te.getBarCode(), "12345678901231");
	}
	
	@Test
	public void testSetPriceUnit() {
		te.setPricePerUnit(1.0);
		assertEquals(te.getPricePerUnit(), 1.0, 0);
	}
	
	@Test
	public void testSetDescription() {
		te.setProductDescription("carne");
		assertEquals(te.getProductDescription(), "carne");
	}
	
	@Test
	public void testSetAmount() {
		te.setAmount(5);
		assertEquals(te.getAmount(), 5);
	}
	
	@Test
	public void testSetDiscountRate() {
		te.setDiscountRate(20.0);
		assertEquals(te.getDiscountRate(), 20.0, 0);
	}
	
	@Test
	public void testSetPrice() {
		te.setPrice(15.0);
		assertEquals(te.getPrice(), 15.0, 0);
	}
}
