package it.polito.ezshop.unitTests.testClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;


public class TestReturnTransaction {
	
	SaleTransaction st = new SaleTransaction();
	ReturnTransaction rt = new ReturnTransaction(123, st);
	
	
	@Test
	public void testReturnConstructor() {
		assertEquals(rt.getReturnId(), (Integer)123);
		assertEquals(rt.getOriginalTransaction(), st);
		assertEquals(rt.isCommitted(), false);
		assertEquals(rt.getAmount(), (Double)0.0, 0.1);
	}
	
	@Test
	public void testSetReturnId() {
		rt.setReturnId(4);
		assertEquals(rt.getReturnId(), (Integer)4);
	}
	
	@Test
	public void testSetCommitted() {
		rt.setCommitted(true);
		assertEquals(rt.isCommitted(), true);
	}
	
	
	@Test
	public void testSetAmount() {		
		rt.setAmount(0.5);
		assertEquals(rt.getAmount(), (Double)0.5, 0.1);
	}
	

}
