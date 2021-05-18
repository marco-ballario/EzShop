package it.polito.ezshop.unitTests.ToolsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;

public class TestAddRemoveProduct {
	

	@Test
	public void testNoBarcode() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		assertFalse(st.removeProducts("1234567890111", 5));
	}
	
	@Test
	public void testNoAmount() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		assertFalse(st.removeProducts("123456789012", -5));
		assertFalse(st.removeProducts("123456789012", 11));
	}
	
	@Test
	public void testValidAmount() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		assertTrue(st.removeProducts("123456789012", 5));
	}
	
	@Test
	public void testZeroAmount() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		assertTrue(st.removeProducts("123456789012", 0));
	}
	
	@Test
	public void testCurrentQuantityAmount() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		assertTrue(st.removeProducts("123456789012", 10));
	}
	
	
	@Test
	public void testReturnIdNegative() {
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
		
		assertFalse(st.updateStatusMin(-10));
	}
	
	@Test
	public void testReturnIdNotPresent() {
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
		
		assertFalse(st.updateStatusMin(2));
	}
	
	@Test
	public void testReturnIdPresent() {
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
		
		assertTrue(st.updateStatusMin(1));
	}
	
	@Test
	public void testReturnIdPresentAndZero() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		
		ReturnTransaction rt = new ReturnTransaction(0, st);
		HashMap<ProductType, Integer> returnProducts = new HashMap<ProductType, Integer>();
		returnProducts.put(pt, 5);
		rt.setReturnProducts(returnProducts);
		
		LinkedList<ReturnTransaction> lst = new LinkedList<ReturnTransaction>();
		lst.add(rt);
		st.setReturnTransactions(lst);
		
		assertTrue(st.updateStatusMin(0));
	}
	
	@Test
	public void testReturnIdPresentAndMax() {
		SaleTransaction st = new SaleTransaction();
		ProductType pt = new ProductType("Description", "123456789012", 1.0, "Note");
		st.addProduct(pt, 10);
		
		ReturnTransaction rt = new ReturnTransaction(Integer.MAX_VALUE, st);
		HashMap<ProductType, Integer> returnProducts = new HashMap<ProductType, Integer>();
		returnProducts.put(pt, 5);
		rt.setReturnProducts(returnProducts);

		LinkedList<ReturnTransaction> lst = new LinkedList<ReturnTransaction>();
		lst.add(rt);
		st.setReturnTransactions(lst);
		
		assertTrue(st.updateStatusMin(Integer.MAX_VALUE));
	}
	

}
