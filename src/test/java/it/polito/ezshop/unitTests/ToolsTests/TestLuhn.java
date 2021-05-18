package it.polito.ezshop.unitTests.ToolsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.Tools;

public class TestLuhn {
	Tools t = new Tools();
	String creditCardFile="./src/main/java/it/polito/ezshop/utils/creditcards.txt";

	@Test
	public void testSizeNot16()  {
		assertFalse(t.checkCardLuhn("44853700865108919"));
	}
	
	
	@Test
	public void testAlphabetInputCard()  {
		assertFalse(t.checkCardLuhn("44853A0086B10891") );
	}
	
	@Test
	public void testNegativeCard()  {
		assertFalse(t.checkCardLuhn("-4485370086510891"));
	}

	
	@Test
	public void testInvalidCard()  {
		assertFalse(t.checkCardLuhn("1485370086510891"));
	}
		
	@Test
	public void test16Digits()  {
		assertTrue(t.checkCardLuhn("4485370086510891"));
	}
}
