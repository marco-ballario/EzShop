package it.polito.ezshop.unitTests.testClass.ToolsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.Tools;

public class TestCheckDigits {
	Tools t = new Tools();
	String creditCardFile="./src/main/java/it/polito/ezshop/utils/creditcards.txt";
	
	@Test
	public void testMore14()  {
		assertFalse(t.checkDigit("123456789012345"));
	}
	
	@Test
	public void testLess12()  {
		assertFalse(t.checkDigit("123456789"));
	}
	
	@Test
	public void testAlphabetInput()  {
		assertFalse(t.checkDigit("123ABCDEFT322") );
	}
	
	@Test
	public void testNegative()  {
		assertFalse(t.checkDigit("-12345678901231"));
	}
	
	
	@Test
	public void testInvalidCode()  {
		assertFalse(t.checkDigit("12345678901232"));
	}
	
	@Test
	public void test14Digits()  {
		assertTrue(t.checkDigit("12345678901231"));
	}
	
	@Test
	public void test13Digits()  {
		assertTrue(t.checkDigit("1234567890111"));
	}
	
	@Test
	public void test12Digits()  {
		assertTrue(t.checkDigit("123456789012"));
	}
	
	@Test
	public void testWhileOnce()  {
		assertFalse(t.checkDigit("00000000000001"));
	}
	
	@Test
	public void testWhileMultiple()  {
		assertTrue(t.checkDigit("12345678901231"));
	}
}
