package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;

public class TestReturnAndProduct {
	SaleTransaction st = new SaleTransaction();
	ProductType pt = new ProductType("manzo","12345678901231",10.0,"ottimo");
	ProductType pt2= new ProductType("arancia","98765432109879",1.0,"giallo");
	ReturnTransaction rt = new ReturnTransaction(1,st);
	
	
	@Test
	public void testAddProduct() {
		st.addProduct(pt, 100);
		double old = rt.getAmount();
		rt.addProduct(pt, 100, 1000);
		assertEquals(rt.getAmount(),old+1000,0.1);
		assertNotNull(rt.getReturnProducts());
	}
	
	@Test
	public void testAddProductAlreadyPresent() {
		HashMap<ProductType, Integer> rp = new HashMap<ProductType, Integer>();
		rp.put(pt,10);
		rp.put(pt2,20);
		rt.setReturnProducts(rp);
		rt.setAmount( pt.getPricePerUnit() * rp.get(pt) + pt2.getPricePerUnit() * rp.get(pt2));
		double old = rt.getAmount();
		rt.addProduct(pt, 100, 1000);
		assertEquals(rt.getAmount(),old+1000,0.1);
		assertNotNull(rt.getReturnProducts());
	}
}
