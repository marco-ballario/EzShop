package it.polito.ezshop.unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import it.polito.ezshop.model.LoyaltyCard;

public class TestLoyaltyCard {
	LoyaltyCard lc = new LoyaltyCard();
	
	@Test
	public void testSetCode() {
		lc.setCode("5125933930");
		assertEquals(lc.getCode(), "5125933930");
	}
	
	@Test
	public void testSetPoints() {
		lc.setPoints(250);
		assertEquals(lc.getPoints(), (Integer)250);	
	}

}
