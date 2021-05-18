package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;

public class TestProductType {
	ProductType pt = new ProductType("Cereali", "12345678901231", 12.2, "molto buoni");

	
	@Test
	public void testProductConstructor() {
		assertEquals(pt.getBarCode(), "12345678901231");
		assertEquals(pt.getNote(), "molto buoni");
		assertEquals(pt.getPricePerUnit(), (Double)12.2);
		assertEquals(pt.getProductDescription(), "Cereali");
		assertEquals(pt.getLocation(), "");
		assertEquals(pt.getQuantity(), (Integer)0);
	}
	
	@Test
	public void testProductConstructor2() {
		ProductType p = new ProductType("Cereali", "12345678901231", 12.2, "molto buoni");
		assertEquals(p.getQuantity(), (Integer)0);
	}
		
	
	
	@Test
	public void testSetBarCode() {
		pt.setBarCode("12345678901231");
		assertEquals(pt.getBarCode(), "12345678901231");
	}
	
	
	@Test
	public void testSetQuantity() {
		pt.setQuantity(10);
		assertEquals(pt.getQuantity(), (Integer)10);
	}
	
	
	@Test
	public void testSetId() {
		pt.setId(1);
		assertEquals(pt.getId(), (Integer)1);
	}
	
	@Test
	public void testSetNote() {
		pt.setNote("costoso");
		assertEquals(pt.getNote(), "costoso");
	}

	@Test
	public void testIncreaseQuantity() {
		pt.setQuantity(0);
		pt.increaseQuantity(10);
		assertEquals(pt.getQuantity(), (Integer)10);
	}
	
	
	@Test
	public void testDecreaseQuantity() {
		pt.setQuantity(10);
		pt.decreaseQuantity(10);
		assertEquals(pt.getQuantity(), (Integer)0);
	}
	
	@Test
	public void testSetPriceUnit() {
		pt.setPricePerUnit(10.0);
		assertEquals(pt.getPricePerUnit(), (Double)10.0);
	}
	@Test
	public void testSetDescription() {
		pt.setProductDescription("carne");
		assertEquals(pt.getProductDescription(), "carne");
	}

}
