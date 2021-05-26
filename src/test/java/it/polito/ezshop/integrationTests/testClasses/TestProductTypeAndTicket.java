package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.TicketEntry;

public class TestProductTypeAndTicket {

	@Test
	public void testTicketConstructor() {
		ProductType pt = new ProductType("pane","1234567890123",12.0, "molto buono");
		TicketEntry te = new TicketEntry(pt,120);
		assertEquals(te.getAmount(), 120);
		assertEquals(te.getBarCode(), "1234567890123");
		assertEquals(te.getDiscountRate(), 0, 0.1);
		assertEquals(te.getPricePerUnit(), 12.0,0.1);
		assertEquals(te.getPrice(), 12.0*120,0.1);
		assertEquals(te.getProductDescription(), "pane");
	}
}
