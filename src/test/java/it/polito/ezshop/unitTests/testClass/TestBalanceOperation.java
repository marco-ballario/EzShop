package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import java.time.LocalDate;
import it.polito.ezshop.model.BalanceOperation;

public class TestBalanceOperation {
	BalanceOperation b = new BalanceOperation();

	@Test
	public void testSetBalanceId() {
		b.setBalanceId(3);
		assertEquals(b.getBalanceId(), 3);
	}
	
	@Test
	public void testSetDate() {
		LocalDate d = LocalDate.parse("2021-05-17");
		b.setDate(d);
		assertEquals(b.getDate(), d);
	}
	
	@Test
	public void testSetType() {
		b.setType("CREDIT");
		assertEquals(b.getType(), "CREDIT");
	}
	
	@Test
	public void testSetMoney() {
		b.setMoney(30.50);
		assertEquals(b.getMoney(), 30.50, 0);
	}

}
