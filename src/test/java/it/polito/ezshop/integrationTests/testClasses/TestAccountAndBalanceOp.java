package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.BalanceOperation;

public class TestAccountAndBalanceOp {
	AccountBook account = AccountBook.getIstance();
	BalanceOperation bo = new BalanceOperation();
	
	@Test
	public void testInsertBalanceOperationCredit() {
		account.setBalance(100);
		boolean res = account.insertBalanceOperation(bo, 50);
		assertTrue(res);
		assertEquals(bo.getMoney(), 50.0, 0.1);
		assertEquals(bo.getDate(), LocalDate.now());
		assertEquals(bo.getType(),"CREDIT");	
		assertEquals(account.getBalance(), 150.0, 0.1);
	}
	
	@Test
	public void testInsertBalanceOperationDebit() {
		account.setBalance(100);
		boolean res = account.insertBalanceOperation(bo, -50);
		assertTrue(res);
		assertEquals(bo.getMoney(), -50.0, 0.1);
		assertEquals(bo.getDate(), LocalDate.now());
		assertEquals(bo.getType(),"DEBIT");
		assertEquals(account.getBalance(), 50.0, 0.1);
	}
	
	@Test
	public void testInsertBalanceOperationNoMoney() {
		account.setBalance(5);
		boolean res = account.insertBalanceOperation(bo, -50);
		assertFalse(res);
	}
	
	
	@Test
	public void testGetList() {
		account.insertBalanceOperation(bo, 50);
		List<it.polito.ezshop.data.BalanceOperation> res = account.getOperationList();
		assertNotNull(res);
	}

}
