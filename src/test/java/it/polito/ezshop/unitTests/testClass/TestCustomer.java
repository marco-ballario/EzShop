package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import it.polito.ezshop.model.Customer;

public class TestCustomer {
	Customer c =new Customer("pippo");
	@Test
	public void testCustomerConstructor() {
		assertNull(c.getLoyaltyCard());
		assertEquals(c.getCustomerName(), "pippo");
	}
	
	
	@Test
	public void testSetName() {
		c.setCustomerName("pluto");
		assertEquals(c.getCustomerName(), "pluto");
	}
	
	@Test
	public void testSetId() {
		c.setId(1);
		assertEquals(c.getId(), (Integer)1);
	}
	
	@Test
	public void testSetPoints() {
		c.setPoints(100);
		// no card register so points still 0
		assertEquals(c.getPoints(), (Integer)0);
	}
	
	@Test
	public void testSetCardNull() {
		c.setCustomerCard(null);
		assertEquals(c.getCustomerCard(), null);
	}
	
	@Test
	public void testSetCard() {
		c.setCustomerCard("1234567890");
		assertEquals(c.getCustomerCard(), "1234567890");
	}

}
