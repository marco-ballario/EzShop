package it.polito.ezshop;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

import it.polito.ezshop.model.*;

public class TestEzShops {
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
	public void testNoFile() throws IOException {
		assertThrows(IOException.class, ()->t.paymentCreditCards("4485370086510891", 12.2, "./wrong"));
	}
	
	@Test
	public void testNoCard() throws IOException {
		assertFalse(t.paymentCreditCards("0085370086510891", 12.2, creditCardFile));
	}
	
	@Test
	public void testNoMoney() throws IOException {
		assertFalse(t.paymentCreditCards("4485370086510891", 1000.0, creditCardFile));
	}
	
	@Test
	public void testMoneyAvaiable() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", 10.0, creditCardFile));
	}
	
	@Test
	public void testZeroMoney() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", 0.0, creditCardFile));
	}
	
	@Test
	public void testReturnMoney() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", -10.0, creditCardFile));
	}
	
	@Test
	public void testMaxMoney() throws IOException {
		assertTrue(t.paymentCreditCards("4485370086510891", -Double.MAX_VALUE, creditCardFile));
		assertTrue(t.paymentCreditCards("4485370086510891", Double.MAX_VALUE-150.0, creditCardFile));
	}
	
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
