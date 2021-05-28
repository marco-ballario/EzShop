package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.SaleTransaction;
import it.polito.ezshop.model.TicketEntry;

public class TestSaleAndTicket {
	ProductType pt = new ProductType("pane","1234567890123",12.0, "molto buono");
	ProductType pt2 = new ProductType("h20","98765432109879",1.0, "fresca");
	TicketEntry te = new TicketEntry(pt,120);
	TicketEntry te2 = new TicketEntry(pt2,10);
	
	SaleTransaction st = new SaleTransaction();
	
	
	@Test
	public void testSetTicketEntries() {
		List<it.polito.ezshop.data.TicketEntry> l = new LinkedList<it.polito.ezshop.data.TicketEntry>();
		l.add(te);
		l.add(te2);
		st.setEntries(l);
		assertEquals(st.getEntries(), l);
		assertEquals(st.getTicketEntry("98765432109879"), te2);
		assertEquals(st.getTicketEntry("1234567890159"), null);
	}
	
	@Test
	public void testAddProductNull() {
		assertFalse(st.addProduct(null, 100));
	}
	
	@Test
	public void testAddProductNotPresent() {
		double oldPrice = st.getPrice();
		List<it.polito.ezshop.data.TicketEntry> l = new LinkedList<it.polito.ezshop.data.TicketEntry>();
		l.add(te);
		l.add(te2);
		st.setEntries(l);
		ProductType pt3 = new ProductType("farina","12345678900005",20.0, "tipo 00");
		assertTrue(st.addProduct(pt3, 100));
		assertEquals(st.getTicketEntry("12345678900005").getAmount(), 100,0.1);
		assertEquals(st.getPrice(),oldPrice+2000,0.1);
	}
	
	@Test
	public void testAddProductAlreadyPresent() {
		int oldQuantity = te2.getAmount();
		List<it.polito.ezshop.data.TicketEntry> l = new LinkedList<it.polito.ezshop.data.TicketEntry>();
		l.add(te);
		l.add(te2);
		st.setEntries(l);
		assertTrue(st.addProduct(pt2, 100));
		assertEquals(st.getTicketEntry("98765432109879").getAmount(), oldQuantity+100,0.1);
	}
	
	@Test
	public void testProductDiscFail() {
		ProductType pt4 = new ProductType("sale","0980987363",0.5,"marino");
		boolean res = st.applyProdDisc(pt4, 0.5);
		assertFalse(res);
		
	}
	
	@Test
	public void testProductDisc() {
		List<it.polito.ezshop.data.TicketEntry> l = new LinkedList<it.polito.ezshop.data.TicketEntry>();
		l.add(te);
		l.add(te2);
		st.setEntries(l);
		double oldPrice = st.getPrice();
		boolean res = st.applyProdDisc(pt, 0.5);
		TicketEntry t = (TicketEntry) st.getTicketEntry(pt.getBarCode());
		assertTrue(res);
		assertEquals(st.getPrice(), oldPrice - t.getPrice() + t.getPrice()*0.5, 0.1);
		
	}
	
	@Test
	public void testDeleteProductNegAmount() {
		assertFalse(st.removeProducts("12345678900092", -1));
	}
	
	@Test
	public void testDeleteProductNotExists() {
		assertFalse(st.removeProducts("12345678900092", 1));
	}
	
	@Test
	public void testDeleteProduct() {
		assertFalse(st.removeProducts("12345678900092", 1));
		ProductType pt5 = new ProductType("farina","12345678900005",20.0, "tipo 00");
		st.addProduct(pt5, 100);
		assertTrue(st.removeProducts("12345678900005", 100));
		assertEquals(st.getPrice(),0,0.1);
	}	

}
