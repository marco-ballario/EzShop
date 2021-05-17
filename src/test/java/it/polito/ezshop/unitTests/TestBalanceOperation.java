package it.polito.ezshop.unitTests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import java.time.LocalDate;
import it.polito.ezshop.model.BalanceOperation;

public class TestBalanceOperation {
	BalanceOperation b = new BalanceOperation();

	@Test
	public void testSetterBalanceOperation() {
		b.setBalanceId(3);
		assertEquals(b.getBalanceId(), 3);
		
		LocalDate d = LocalDate.parse("2021-05-17");
		b.setDate(d);
		assertEquals(b.getDate(), d);
		
		b.setMoney(30.50);
		assertEquals(b.getMoney(), 30.50, 0);
		
		b.setType("CREDIT");
		assertEquals(b.getType(), "CREDIT");
		
	}

}
