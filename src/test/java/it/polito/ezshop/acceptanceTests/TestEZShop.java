package it.polito.ezshop.acceptanceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import it.polito.ezshop.unitTests.*;

@RunWith(Suite.class)
@SuiteClasses({TestProductType.class, TestLoyaltyCard.class, TestBalanceOperation.class, TestUser.class, TestTools.class, TestAccountBook.class, TestSaleTransaction.class})
public class TestEZShop {
}