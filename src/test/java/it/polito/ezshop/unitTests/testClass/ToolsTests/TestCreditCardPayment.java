package it.polito.ezshop.unitTests.testClass.ToolsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import it.polito.ezshop.model.Tools;

public class TestCreditCardPayment {
	Tools t = new Tools();
	String creditCardFile="./src/main/java/it/polito/ezshop/utils/creditcards.txt";
	
	@Test
	public void testNoFile() throws IOException {
		assertThrows(IOException.class, ()->t.paymentCreditCards("4485370086510891", 12.2, "./wrong"));
	}
	
	@Test
	public void testNoCard() throws IOException {
		assertFalse(t.paymentCreditCards("0085370086510891", 12.2, creditCardFile));
	}
	
	@Test
	public void testNoMoney() throws IOException {
		assertFalse(t.paymentCreditCards("4485370086510891", 1000.0, creditCardFile));
	}
	
	@Test
	public void testMoneyAvaiable() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", 10.0, creditCardFile));
	}
	
	@Test
	public void testZeroMoney() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", 0.0, creditCardFile));
	}
	
	@Test
	public void testReturnMoney() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", -10.0, creditCardFile));
	}
	
	

}
