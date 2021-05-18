package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.AccountBook;

public class TestAccountBook {
	
	AccountBook ab = AccountBook.getIstance();
	
	@Test
	public void testAccountConstructor() {
		assertEquals(ab.getBalance(),(double)0, 0.1);
	}
	
	@Test
	public void testSetBalance() {
		ab.setBalance(10.0);
		assertEquals(ab.getBalance(),(double)10.0, 0.1);
	}
	
	
	
}
