package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;

public class TestSaleAndReturn {
	SaleTransaction st = new SaleTransaction();
	ReturnTransaction rt = new ReturnTransaction(1,st);
	ReturnTransaction rt2 = new ReturnTransaction(2,st);
	ProductType pt = new ProductType("mango","12345678901231",10.0,"arancione");
	ProductType pt2= new ProductType("arancia","98765432109879",1.0,"giallo");
	
	
	@Test
	public void testSetReturnTransaction() {
		List<ReturnTransaction> l = new LinkedList<>();
		l.add(rt);
		l.add(rt2);
		st.setReturnTransactions(l);
		assertEquals(l, st.getReturnTransactions());	
	}
	
	@Test
	public void testStutusPlusNoReturnId() {
		assertFalse(st.updateStatusPlus(3));
	}
	
	@Test
	public void testStutusPlusNegId() {
		assertFalse(st.updateStatusPlus(-3));
	}
	
	@Test
	public void testStutusPlus() {
		st.addProduct(pt, 200);
		st.addProduct(pt2, 2);
		rt.addProduct(pt, 10, 100);
		rt2.addProduct(pt2, 1, 1);
		List<ReturnTransaction> l = new LinkedList<>();
		l.add(rt);
		l.add(rt2);
		st.setReturnTransactions(l);
		assertTrue(st.updateStatusPlus(1));
		
	}
	
	@Test
	public void testSetOriginalTransaction() {
		rt.setOriginalTransaction(st);
		assertEquals(rt.getOriginalTransaction(), st);	
	}
	


}
