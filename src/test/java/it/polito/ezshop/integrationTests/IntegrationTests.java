package it.polito.ezshop.integrationTests;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import it.polito.ezshop.integrationTests.testClasses.*;
import it.polito.ezshop.unitTests.testClass.*;
import it.polito.ezshop.unitTests.testClass.ToolsTests.TestAddRemoveProduct;
import it.polito.ezshop.unitTests.testClass.ToolsTests.TestCheckDigits;
import it.polito.ezshop.unitTests.testClass.ToolsTests.TestCreditCardPayment;
import it.polito.ezshop.unitTests.testClass.ToolsTests.TestLuhn;
import it.polito.ezshop.unitTests.testClass.ToolsTests.TestUpdateStatus;

@RunWith(Suite.class)
@SuiteClasses({TestSaleAndTicket.class,TestSaleAndReturn.class,TestReturnAndProduct.class,TestProductTypeAndTicket.class,TestCustomerAndLoyalty.class, TestAccountAndBalanceOp.class,
	TestOrder.class,TestCustomer.class, TestProduct.class, TestProductType.class, TestLoyaltyCard.class, TestBalanceOperation.class, TestUser.class, TestAddRemoveProduct.class,TestCheckDigits.class,
	TestCreditCardPayment.class,TestLuhn.class,TestUpdateStatus.class, TestAccountBook.class, TestSaleTransaction.class, TestTicketEntry.class, TestReturnTransaction.class, TestEZShopIntegration.class})

public class IntegrationTests {	

}
