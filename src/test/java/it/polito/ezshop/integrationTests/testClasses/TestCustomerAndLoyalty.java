package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.Customer;
import it.polito.ezshop.model.LoyaltyCard;

public class TestCustomerAndLoyalty {
	LoyaltyCard lc = new LoyaltyCard();
	Customer c = new Customer("gianni");
	

	@Test
	public void testSetLoyalty() {
		lc.setCode("1234567890");
		c.setLoyaltyCard(lc);
		assertEquals(c.getLoyaltyCard(), lc);
	}
	
	
	@Test
	public void testSetPoints() {
		lc.setCode("1234567890");
		c.setLoyaltyCard(lc);
		c.setPoints(50);
		assertEquals(c.getPoints(), (Integer)50);
	}	

}
