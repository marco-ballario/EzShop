package it.polito.ezshop.acceptanceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import it.polito.ezshop.unitTests.*;

@RunWith(Suite.class)
@SuiteClasses({TestOrder.class,TestCustomer.class,TestProductType.class, TestLoyaltyCard.class, TestBalanceOperation.class, TestUser.class, it.polito.ezshop.unitTests.ToolsTests.TestAddRemoveProduct.class,it.polito.ezshop.unitTests.ToolsTests.TestCheckDigits.class,it.polito.ezshop.unitTests.ToolsTests.TestCreditCardPayment.class,it.polito.ezshop.unitTests.ToolsTests.TestLuhn.class,it.polito.ezshop.unitTests.ToolsTests.TestUpdateStatus.class, TestAccountBook.class, TestSaleTransaction.class})
public class TestEZShop {
}