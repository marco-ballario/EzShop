package it.polito.ezshop.unitTests;

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
	public void testSetLocation() {
		pt.setLocation("11-aa-11");
		assertEquals(pt.getLocation(), "11-aa-11");
	}

}
