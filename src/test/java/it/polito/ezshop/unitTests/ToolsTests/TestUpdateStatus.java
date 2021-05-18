package it.polito.ezshop.unitTests.ToolsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;

public class TestUpdateStatus {
	@Test
	public void testNegativeReturnId() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		
		ReturnTransaction rt = new ReturnTransaction(1, st);
		HashMap<ProductType, Integer> returnProducts = new HashMap<ProductType, Integer>();
		returnProducts.put(pt, 5);
		rt.setReturnProducts(returnProducts);
		
		LinkedList<ReturnTransaction> lst = new LinkedList<ReturnTransaction>();
		lst.add(rt);
		st.setReturnTransactions(lst);
		
		assertFalse(st.updateStatusPlus(-10));
	}
	
	@Test
	public void testNonExistReturnId() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		
		ReturnTransaction rt = new ReturnTransaction(1, st);
		HashMap<ProductType, Integer> returnProducts = new HashMap<ProductType, Integer>();
		returnProducts.put(pt, 5);
		rt.setReturnProducts(returnProducts);
		
		LinkedList<ReturnTransaction> lst = new LinkedList<ReturnTransaction>();
		lst.add(rt);
		st.setReturnTransactions(lst);
		
		assertFalse(st.updateStatusPlus(2));
	}
	
	@Test
	public void testCorrectReturnId() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		
		ReturnTransaction rt = new ReturnTransaction(1, st);
		HashMap<ProductType, Integer> returnProducts = new HashMap<ProductType, Integer>();
		returnProducts.put(pt, 5);
		rt.setReturnProducts(returnProducts);
		
		LinkedList<ReturnTransaction> lst = new LinkedList<ReturnTransaction>();
		lst.add(rt);
		st.setReturnTransactions(lst);
		
		assertTrue(st.updateStatusPlus(1));
	}

}
