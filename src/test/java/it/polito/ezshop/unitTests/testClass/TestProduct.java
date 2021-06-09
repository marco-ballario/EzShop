package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.Product;
import it.polito.ezshop.model.ProductType;

public class TestProduct {
	ProductType pt= new ProductType("pane", "12345678901231", 12.0, "caldo");
	Product p = new Product((long)1, pt);

	@Test
	public void testConstructor() {
		assertEquals((long)1, (long)p.getRFID());
		assertEquals(pt.getBarCode(), p.getProductType().getBarCode());
		assertEquals(p.getProductType(), pt);

	} 
	
	@Test
	public void testSetRFID() {
		p.setRFID((long) 2);
		assertEquals((long)2, (long)p.getRFID());
	}
	
	@Test
	public void testSetProductType() {
		ProductType pt2= new ProductType("h20", "12345678900005", 1.0, "fresca");
		p.setProductType(pt2);
		assertEquals(p.getProductType().getBarCode(), "12345678900005");
		assertEquals(p.getProductType(), pt2);

	}

}
