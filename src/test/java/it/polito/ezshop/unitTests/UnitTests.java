package it.polito.ezshop.unitTests;
import org.junit.runner.RunWith;
import it.polito.ezshop.unitTests.testClass.*;
import it.polito.ezshop.unitTests.testClass.ToolsTests.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({TestOrder.class,TestCustomer.class,TestProductType.class, TestLoyaltyCard.class, TestBalanceOperation.class, TestUser.class, TestAddRemoveProduct.class,TestCheckDigits.class,TestCreditCardPayment.class,TestLuhn.class,TestUpdateStatus.class, TestAccountBook.class, TestSaleTransaction.class, TestTicketEntry.class, TestReturnTransaction.class})

public class UnitTests {

}
