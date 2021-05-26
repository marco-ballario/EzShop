package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.Order;

public class TestOrder {
	Order o = new Order("1234567890123", 40, 12.0);
	
	@Test
	public void testOrderConstructor() {
		assertEquals(o.getProductCode(), "1234567890123");
		assertEquals(o.getPricePerUnit(),(double)12.0, 0.1);
		assertEquals(o.getQuantity(), (int)40);
	}
	
	@Test
	public void testSetPriceUnit() {
		o.setPricePerUnit(120.0);
		assertEquals(o.getPricePerUnit(), (Double)120.0, 0.1);
	}
	
	@Test
	public void testSetProductCode() {
		o.setProductCode("98765432109879");
		assertEquals(o.getProductCode(), "98765432109879");
	}
	

	@Test
	public void testSetStatus() {
		o.setStatus("ISSUED");
		assertEquals(o.getStatus(), "ISSUED");
	}

	@Test
	public void testSetQuantity() {
		o.setQuantity(5);
		assertEquals(o.getQuantity(), 5);
	}
	
	@Test
	public void testSetOrderId() {
		o.setOrderId(2);
		assertEquals(o.getOrderId(), (Integer)2);
	}
	
	@Test
	public void testSetBalanceId() {
		o.setBalanceId(4);
		assertEquals(o.getBalanceId(), (Integer)4);
	}

}
