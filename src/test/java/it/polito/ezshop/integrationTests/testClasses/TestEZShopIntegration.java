package it.polito.ezshop.integrationTests.testClasses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polito.ezshop.data.Customer;
import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.*;
import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.User;

public class TestEZShopIntegration {

	static EZShop ezShop;
	static User wrongUser;
	@BeforeClass
	public static void setUp() throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidProductIdException, InvalidLocationException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, InvalidCustomerIdException {
		ezShop = new it.polito.ezshop.data.EZShop();
		ezShop.reset();
		wrongUser = new User("Beppe", "1234", "adminnn", 120);
		ezShop.createUser("admin", "admin", "Administrator");
		ezShop.createUser("ciao", "ciao", "Cashier");
		ezShop.login("admin", "admin");
		ezShop.createProductType("pane", "12345678901231", 10.0, "ciao");
		ezShop.createProductType("acqua", "1234567890005", 20.0, "cane");
		ezShop.createProductType("polenta", "98765432109879", 10.1, "cocnia");
		ezShop.createProductType("chchchc", "98765432100005", 32.1, "cmid");
		ezShop.updatePosition(1, "11-aa-11");
		ezShop.updatePosition(3, "12-s-1");
		ezShop.updatePosition(4, "11-s-11");
		ezShop.updateQuantity(1, 50);
		ezShop.updateQuantity(4, 46);

		ezShop.defineCustomer("laura");
		ezShop.defineCustomer("luigi");
		Customer c1 = ezShop.getCustomer(1);
		Customer c2 = ezShop.getCustomer(2);
		c1.setCustomerCard("7094947694");
		c2.setCustomerCard("2183175269");
		c1.setPoints(142);
		c2.setPoints(52);
		ezShop.recordBalanceUpdate(12000);
		ezShop.logout();

	}
	@AfterClass
	public static void reset() {
		ezShop.reset();
	}

	@Test
	public void testUserAPI() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidUserIdException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteUser(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.getUser(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.getAllUsers());
		assertThrows(UnauthorizedException.class, () -> ezShop.updateUserRights(1, "Cashier"));
		ezShop.logout();

		int userId = ezShop.getUserId();
		assertNotNull(ezShop.login("admin", "admin"));
		assertEquals((Integer) userId, ezShop.createUser("mario", "password", "Cashier"));
		assertEquals((Integer) (-1), ezShop.createUser("mario", "password", "Cashier"));
		assertNotNull(ezShop.getUser(userId));
		assertNotNull(ezShop.getAllUsers());
		assertTrue(ezShop.updateUserRights(userId, "ShopManager"));
		assertTrue(ezShop.deleteUser(userId));
		assertNull(ezShop.getUser(userId));
		assertTrue(ezShop.logout());
	}

	@Test
	public void testWrongCreateUser() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidUserIdException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		assertThrows(InvalidUsernameException.class, () -> ezShop.createUser("", "pass", "Cashier"));
		assertThrows(InvalidPasswordException.class, () -> ezShop.createUser("mario", "", "Cashier"));
		assertThrows(InvalidRoleException.class, () -> ezShop.createUser("mario", "pass", ""));
		ezShop.logout();

	}

	@Test
	public void testWrongIdAndRoleUser() throws InvalidUsernameException, InvalidPasswordException,
			InvalidRoleException, InvalidUserIdException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		assertThrows(InvalidUserIdException.class, () -> ezShop.deleteUser(-1));
		assertThrows(InvalidUserIdException.class, () -> ezShop.getUser(-1));
		assertThrows(InvalidUserIdException.class, () -> ezShop.updateUserRights(-1, "Cashier"));

		assertThrows(InvalidRoleException.class, () -> ezShop.updateUserRights(1, ""));

		ezShop.logout();

	}

	@Test
	public void testLogin() throws InvalidUsernameException, InvalidPasswordException {
		ezShop.logout();
		assertThrows(InvalidUsernameException.class, () -> ezShop.login(null, "pass"));

		assertThrows(InvalidPasswordException.class, () -> ezShop.login("mario", ""));
		ezShop.logout();
		assertNull(ezShop.login("admin", "adminn"));
		assertNull(ezShop.login("sss", "adminn"));

		assertNotNull(ezShop.login("admin", "admin"));
		ezShop.logout();

	}

	@Test
	public void testProductTypeAPI() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidUserIdException, UnauthorizedException, InvalidProductDescriptionException,
			InvalidProductCodeException, InvalidPricePerUnitException, InvalidProductIdException {
		int productId = ezShop.getProductId();
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.createProductType("eke", "12345678901231", 1.0, "ede"));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteProductType(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.updateProduct(1, "nuova", "1234567890134", 1.9, "dddd"));

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.createProductType("eke", "12345678901231", 1.0, "ede"));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteProductType(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.updateProduct(1, "nuova", "1234567890134", 1.9, "dddd"));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidProductDescriptionException.class,
				() -> ezShop.createProductType("", "12345678901231", 1.0, "d"));
		assertThrows(InvalidProductDescriptionException.class,
				() -> ezShop.createProductType(null, "12345678901231", 1.0, "d"));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.createProductType("d", "", 1.0, "d"));
		assertThrows(InvalidProductCodeException.class,
				() -> ezShop.createProductType("dsss", "12345678901233", 1.0, "d"));
		assertThrows(InvalidPricePerUnitException.class,
				() -> ezShop.createProductType("d", "12345678901231", -1.0, "d"));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.updateProduct(1, "s", "", 1, "s"));
		assertThrows(InvalidProductIdException.class, () -> ezShop.updateProduct(-1, "s", "12345678901231", 1, "s"));
		assertThrows(InvalidProductDescriptionException.class,
				() -> ezShop.updateProduct(1, "", "12345678901231", 1, "d"));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.updateProduct(2, "s", "12345678901234", 1, "e"));
		assertThrows(InvalidProductIdException.class, () -> ezShop.deleteProductType(-1));

		Integer i = -1;
		assertEquals(i, ezShop.createProductType("abc", "12345678901231", 1.0, "ddj"));

		assertEquals((Integer) productId, ezShop.createProductType("uova", "3456728901230", 0.5, "fresche"));

		assertTrue(ezShop.updateProduct(productId, "latte", "68932763220989", 0.4, "un pò meno fresche"));
		assertFalse(ezShop.updateQuantity(productId, 5));
		assertNull(ezShop.getProductTypeByBarCode("3456728901230"));
		assertNotNull(ezShop.getProductTypeByBarCode("68932763220989"));
		assertNotNull(ezShop.getProductTypesByDescription("lat"));
		assertTrue(ezShop.deleteProductType(productId));
		assertTrue(ezShop.logout());
	}

	@Test
	public void testGetAllProducts() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException,
			InvalidProductCodeException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.getAllProductTypes());
		assertThrows(UnauthorizedException.class, () -> ezShop.getProductTypeByBarCode("12345678901231"));
		assertThrows(UnauthorizedException.class, () -> ezShop.getProductTypesByDescription("buone"));

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.getProductTypeByBarCode("12345678901231"));
		assertThrows(UnauthorizedException.class, () -> ezShop.getProductTypesByDescription("buone"));
		ezShop.logout();

		ezShop.logout();
		ezShop.login("ciao", "ciao");
		assertNotNull(ezShop.getAllProductTypes());
		ezShop.logout();
		ezShop.login("admin", "admin");

		assertNotNull(ezShop.getAllProductTypes());

		assertThrows(InvalidProductCodeException.class, () -> ezShop.getProductTypeByBarCode(""));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.getProductTypeByBarCode("12345678901234"));
		assertNull(ezShop.getProductTypeByBarCode("68932763220972"));
		assertNotNull(ezShop.getProductTypeByBarCode("12345678901231"));
		assertNotNull(ezShop.getProductTypesByDescription("cane"));
		ezShop.logout();
	}

	@Test
	public void testUpdateProduct() throws InvalidUsernameException, InvalidPasswordException,
			InvalidProductIdException, UnauthorizedException, InvalidLocationException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.updateQuantity(1, 1));
		assertThrows(UnauthorizedException.class, () -> ezShop.updatePosition(1, "1-a-1"));
		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.updateQuantity(1, 1));
		assertThrows(UnauthorizedException.class, () -> ezShop.updatePosition(1, "1-a-1"));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidProductIdException.class, () -> ezShop.updatePosition(-1, "1-a-1"));
		assertThrows(InvalidProductIdException.class, () -> ezShop.updateQuantity(-1, 1));

		assertFalse(ezShop.updateQuantity(1200, 1));
		assertFalse(ezShop.updateQuantity(1, -100000));
		assertTrue(ezShop.updateQuantity(1, 1));

		assertThrows(InvalidLocationException.class, () -> ezShop.updatePosition(1, "11"));
		assertTrue(ezShop.updatePosition(1, "11-aa-11"));

		assertTrue(ezShop.updatePosition(1, ""));
		assertTrue(ezShop.updatePosition(1, "22-bb-22"));
		ezShop.updatePosition(1, "11-aa-11");
		ezShop.logout();

	}

	@Test
	public void testOrdersAPI() throws InvalidUsernameException, InvalidPasswordException, InvalidProductCodeException,
			InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException, InvalidOrderIdException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.issueOrder("12345678901231", 1, 1.0));
		assertThrows(UnauthorizedException.class, () -> ezShop.payOrder(1));

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.payOrder(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.issueOrder("12345678901231", 1, 1.0));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidOrderIdException.class, () -> ezShop.payOrder(-1));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.issueOrder("12345678901239", 1, 1.0));
		assertThrows(InvalidQuantityException.class, () -> ezShop.issueOrder("12345678901231", -1, 1.0));
		assertThrows(InvalidPricePerUnitException.class, () -> ezShop.issueOrder("12345678901231", 1, -1.0));
		Integer id = ezShop.getOrderId();
		assertEquals(id, ezShop.issueOrder("12345678901231", 1, 1.0));

		// correct
		ezShop.recordBalanceUpdate(1);
		assertTrue(ezShop.payOrder(id));
		assertFalse(ezShop.payOrder(12000));

		// no money
		id = ezShop.getOrderId();
		assertEquals(id, ezShop.issueOrder("12345678901231", 1000, 1000.0));
		assertFalse(ezShop.payOrder(id));
		ezShop.logout();
	}

	@Test
	public void testIssueAndPayOrder() throws InvalidUsernameException, InvalidPasswordException,
			InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.payOrderFor("12345678901231", 1, 1.0));
		assertThrows(UnauthorizedException.class, () -> ezShop.recordBalanceUpdate(1));
		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.payOrderFor("12345678901231", 1, 1.0));
		assertThrows(UnauthorizedException.class, () -> ezShop.recordBalanceUpdate(1));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidProductCodeException.class, () -> ezShop.payOrderFor(null, 1, 1.0));
		assertThrows(InvalidQuantityException.class, () -> ezShop.payOrderFor("12345678901231", -1, 1.0));
		assertThrows(InvalidPricePerUnitException.class, () -> ezShop.payOrderFor("12345678901231", 1, -1.0));
		Integer i = -1;
		assertEquals(ezShop.payOrderFor("12345678901231", 1000, 10000.0), i);

		// correct
		Integer id = ezShop.getOrderId();
		ezShop.recordBalanceUpdate(-1);
		assertEquals(id, ezShop.payOrderFor("12345678901231", 1, 1.0));
		ezShop.logout();

	}

	@Test
	public void testArrivalOrder() throws InvalidUsernameException, InvalidPasswordException, InvalidOrderIdException,
			UnauthorizedException, InvalidLocationException, InvalidProductCodeException, InvalidQuantityException,
			InvalidPricePerUnitException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		Integer id = ezShop.getOrderId() - 1;
		assertThrows(UnauthorizedException.class, () -> ezShop.recordOrderArrival(id));
		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.recordOrderArrival(id));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidOrderIdException.class, () -> ezShop.recordOrderArrival(null));
		assertThrows(InvalidOrderIdException.class, () -> ezShop.recordOrderArrival(-1));
		assertFalse(ezShop.recordOrderArrival(999999));
		
		int ret3 = ezShop.payOrderFor("12345678901231", 1, 1.0);
		assertTrue(ezShop.recordOrderArrival(ret3));
		
		assertFalse(ezShop.recordOrderArrival(ret3));
		Integer ret = ezShop.payOrderFor("12345678901231", 1, 1.0);
		assertTrue(ezShop.recordOrderArrival(ret));
		Integer ret2 = ezShop.payOrderFor("1234567890005", 1, 1.0);
		assertThrows(InvalidLocationException.class, () -> ezShop.recordOrderArrival(ret2));

		ezShop.logout();

	}

	@Test
	public void testAllOrders() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.getAllOrders());
		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.getAllOrders());
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertNotNull(ezShop.getAllOrders());
		ezShop.logout();

	}

	@Test
	public void testCustomersCards()
			throws InvalidUsernameException, InvalidPasswordException, InvalidCustomerNameException,
			UnauthorizedException, InvalidCustomerIdException, InvalidCustomerCardException {
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.modifyCustomer(1, "FakeCustomer", null));
		assertThrows(UnauthorizedException.class, () -> ezShop.createCard());
		assertThrows(UnauthorizedException.class, () -> ezShop.attachCardToCustomer("FakeLoyaltyCard", 1));
		assertThrows(UnauthorizedException.class, () -> ezShop.modifyPointsOnCard("FakeLoyaltyCard", 10));
		ezShop.logout();

		ezShop.login("admin", "admin");

		Integer cid1 = ezShop.getCustomerId();
		ezShop.defineCustomer("Giulia");
		Integer cid2 = ezShop.getCustomerId();
		ezShop.defineCustomer("Luca");
		Integer cid3 = ezShop.getCustomerId();
		ezShop.defineCustomer("Francesco");

		// createCard
		String lc1 = ezShop.createCard();
		assertNotEquals(lc1, "");
		String lc2 = ezShop.createCard();
		assertNotEquals(lc2, "");

		// attachCardToCustomer
		assertThrows(InvalidCustomerIdException.class, () -> ezShop.attachCardToCustomer(lc1, -1));
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.attachCardToCustomer("", cid1));
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.attachCardToCustomer("abcdefghij", cid1));
		assertTrue(ezShop.attachCardToCustomer(lc1, cid1));
		assertFalse(ezShop.attachCardToCustomer(lc1, cid2)); // card already associated with cid1
		assertFalse(ezShop.attachCardToCustomer(lc1, 404));

		// modifyPointsOnCard
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.modifyPointsOnCard("", 10));
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.modifyPointsOnCard(null, 10));
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.modifyPointsOnCard("abcdefghij", 10));
		assertTrue(ezShop.modifyPointsOnCard(lc1, 10));
		assertFalse(ezShop.modifyPointsOnCard("0123456789", 10));
		assertFalse(ezShop.modifyPointsOnCard("0123456789", -10));

		// modifyCustomer
		assertThrows(InvalidCustomerNameException.class, () -> ezShop.modifyCustomer(cid1, "", lc2));
		assertThrows(InvalidCustomerCardException.class, () -> ezShop.modifyCustomer(cid1, "Giulia", "abcdefghij"));
		assertThrows(InvalidCustomerIdException.class, () -> ezShop.modifyCustomer(-1, "Giulia", lc2));
		assertTrue(ezShop.modifyCustomer(cid2, "Paolo", lc2)); // true: change name and assign lc2 to cid2
		assertFalse(ezShop.attachCardToCustomer(lc2, cid3)); // false: lc2 is already assigned to cid2
		assertFalse(ezShop.modifyCustomer(cid3, "Francesco", lc2)); // false: lc2 is already assigned to cid2
		assertTrue(ezShop.modifyCustomer(cid2, "Paolo", "")); // true: remove lc2 from cid2
		assertTrue(ezShop.modifyCustomer(cid1, "Giulia", "")); // true: remove lc1 from cid1
		assertTrue(ezShop.modifyCustomer(cid2, "Paolo", null)); // true: does nothing

		ezShop.deleteCustomer(cid1);
		ezShop.deleteCustomer(cid2);
		ezShop.deleteCustomer(cid3);
		ezShop.deleteCustomer(404);
		ezShop.logout();
	}

	@Test
	public void testCustomersAPI() throws InvalidUsernameException, InvalidPasswordException,
			InvalidCustomerNameException, UnauthorizedException, InvalidCustomerIdException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.defineCustomer("FakeCustomer"));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteCustomer(1));
		ezShop.logout();

		ezShop.login("admin", "admin");

		// defineCustomer
		Integer cid = ezShop.getCustomerId();
		assertThrows(InvalidCustomerNameException.class, () -> ezShop.defineCustomer(""));
		assertEquals(cid, ezShop.defineCustomer("Michele"));

		// deleteCustomer
		assertThrows(InvalidCustomerIdException.class, () -> ezShop.deleteCustomer(-1));
		assertTrue(ezShop.deleteCustomer(cid));
		assertFalse(ezShop.deleteCustomer(404));

		ezShop.logout();
	}

	@Test
	public void testSearchCustomer() throws InvalidUsernameException, InvalidPasswordException,
			InvalidCustomerNameException, UnauthorizedException, InvalidCustomerIdException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.getCustomer(1));
		ezShop.logout();

		ezShop.login("admin", "admin");
		Integer cid = ezShop.getCustomerId();
		ezShop.defineCustomer("Carlo");
		assertThrows(InvalidCustomerIdException.class, () -> ezShop.getCustomer(-1));
		assertEquals(cid, ezShop.getCustomer(cid).getId()); // getCustomer
		assertNull(ezShop.getCustomer(404));
		ezShop.deleteCustomer(cid);
		ezShop.logout();
	}

	@Test
	public void testAllCustomers() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.getAllCustomers());
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertNotNull(ezShop.getAllCustomers()); // getAllCustomers
		ezShop.logout();
	}

	@Test
	public void testSaleTransactionAPI() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			InvalidDiscountRateException, InvalidPaymentException, InvalidCreditCardException {
		ezShop.logout();
		ezShop.login("WrongUsername", "WrongPassword");

		assertThrows(UnauthorizedException.class, () -> ezShop.startSaleTransaction());
		assertThrows(UnauthorizedException.class, () -> ezShop.addProductToSale(1, "FakeProductCode", 5));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteProductFromSale(1, "FakeProductCode", 5));
		assertThrows(UnauthorizedException.class, () -> ezShop.applyDiscountRateToProduct(1, "FakeProductCode", 0.5));
		assertThrows(UnauthorizedException.class, () -> ezShop.applyDiscountRateToSale(1, 0.5));
		assertThrows(UnauthorizedException.class, () -> ezShop.computePointsForSale(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.getSaleTransaction(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.endSaleTransaction(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.receiveCashPayment(1, 20));
		assertThrows(UnauthorizedException.class, () -> ezShop.receiveCreditCardPayment(1, "5100293991053009"));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteSaleTransaction(1));

		ezShop.logout();

		assertNotNull(ezShop.login("admin", "admin"));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.addProductToSale(-1, "12345678901239", 5));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.deleteProductFromSale(-1, "12345678901239", 5));
		assertThrows(InvalidTransactionIdException.class,
				() -> ezShop.applyDiscountRateToProduct(null, "12345678901239", 0.5));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.applyDiscountRateToSale(-1, 0.5));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.computePointsForSale(-1));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.getSaleTransaction(-1));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.endSaleTransaction(-1));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.receiveCashPayment(-1, 20));
		assertThrows(InvalidTransactionIdException.class,
				() -> ezShop.receiveCreditCardPayment(-1, "5100293991053009"));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.deleteSaleTransaction(-1));

		assertThrows(InvalidProductCodeException.class, () -> ezShop.addProductToSale(1, "", 5));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.deleteProductFromSale(1, "958", 5));
		assertThrows(InvalidProductCodeException.class, () -> ezShop.applyDiscountRateToProduct(1, null, 0.5));

		assertThrows(InvalidQuantityException.class, () -> ezShop.addProductToSale(1, "12345678901231", -5));
		assertThrows(InvalidQuantityException.class, () -> ezShop.deleteProductFromSale(1, "12345678901231", -5));

		assertThrows(InvalidDiscountRateException.class,
				() -> ezShop.applyDiscountRateToProduct(1, "12345678901231", -1.2));
		assertThrows(InvalidDiscountRateException.class, () -> ezShop.applyDiscountRateToSale(1, 1.1));

		assertThrows(InvalidPaymentException.class, () -> ezShop.receiveCashPayment(1, -5));
		assertThrows(InvalidCreditCardException.class, () -> ezShop.receiveCreditCardPayment(1, ""));

		Integer id = ezShop.getSaleId();
		assertFalse(ezShop.receiveCreditCardPayment(id, "5100293991053009"));
		assertEquals((Integer) (-1), ezShop.receiveCashPayment(id, 4), 0.0);
		assertEquals(id, ezShop.startSaleTransaction());
		assertTrue(ezShop.addProductToSale(id, "12345678901231", 5));
		assertTrue(ezShop.applyDiscountRateToProduct(id, "12345678901231", 0.2));
		assertTrue(ezShop.applyDiscountRateToSale(id, 0.1));
		assertNotNull(ezShop.computePointsForSale(id));
		assertTrue(ezShop.deleteProductFromSale(id, "12345678901231", 5));
		assertTrue(ezShop.endSaleTransaction(id));
		assertNotNull(ezShop.receiveCashPayment(id, 20));
		assertNotNull(ezShop.receiveCreditCardPayment(id, "5100293991053009"));
		assertNotNull(ezShop.getSaleTransaction(id));
		assertTrue(ezShop.deleteSaleTransaction(id));

		assertTrue(ezShop.logout());

	}
	
	@Test
	public void testManageSaleTransactionCancelled() throws InvalidTransactionIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidUsernameException, InvalidPasswordException  {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();
		it.polito.ezshop.data.ProductType pt = ezShop.getProductTypeByBarCode("12345678901231");
		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier C exists and is logged in
		int id = ezShop.startSaleTransaction();
		int quantityX = pt.getQuantity();
		int unitsN = 2;
		assertTrue(ezShop.addProductToSale(id, "12345678901231", unitsN));
		assertEquals(pt.getQuantity(), (Integer) (quantityX - unitsN)); // X available quantity is decreased by N
		assertTrue(ezShop.endSaleTransaction(id));
		assertTrue(ezShop.deleteSaleTransaction(id));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance, ezShop.computeBalance(), 0.1);
		ezShop.logout();

	}

	@Test
	public void testManageSaleTransaction() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidProductCodeException, InvalidTransactionIdException, InvalidQuantityException,
			InvalidPaymentException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();
		it.polito.ezshop.data.ProductType pt = ezShop.getProductTypeByBarCode("12345678901231");
		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier C exists and is logged in
		int id = ezShop.startSaleTransaction();
		double pricePerUnit = pt.getPricePerUnit();
		int quantityX = pt.getQuantity();
		int unitsN = 2;
		assertTrue(ezShop.addProductToSale(id, "12345678901231", unitsN));
		assertEquals(pt.getQuantity(), (Integer) (quantityX - unitsN)); // X available quantity is decreased by N
		assertTrue(ezShop.endSaleTransaction(id));
		assertNotNull(ezShop.receiveCashPayment(id, 20));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance + (unitsN * pricePerUnit), ezShop.computeBalance(), 0.1);
		ezShop.logout();

	}

	@Test
	public void testManageSaleTransactionProductDiscount() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidProductCodeException, InvalidTransactionIdException, InvalidQuantityException,
			InvalidPaymentException, InvalidDiscountRateException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();
		it.polito.ezshop.data.ProductType pt = ezShop.getProductTypeByBarCode("12345678901231");
		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier C exists and is logged in
		int id = ezShop.startSaleTransaction();
		double pricePerUnit = pt.getPricePerUnit();
		int quantityX = pt.getQuantity();
		int unitsN = 2;
		double discount = 0.2;
		assertTrue(ezShop.addProductToSale(id, "12345678901231", unitsN));
		assertEquals(pt.getQuantity(), (Integer) (quantityX - unitsN)); // X available quantity is decreased by N
		assertTrue(ezShop.applyDiscountRateToProduct(id, "12345678901231", discount));
		assertTrue(ezShop.endSaleTransaction(id));
		assertNotNull(ezShop.receiveCashPayment(id, 20));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance + (unitsN * pricePerUnit - unitsN * pricePerUnit * discount), ezShop.computeBalance(),
				0.1);
		ezShop.logout();

	}

	@Test
	public void testManageSaleTransactionDiscount() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidProductCodeException, InvalidTransactionIdException, InvalidQuantityException,
			InvalidPaymentException, InvalidDiscountRateException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();
		it.polito.ezshop.data.ProductType pt = ezShop.getProductTypeByBarCode("12345678901231");
		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier C exists and is logged in
		int id = ezShop.startSaleTransaction();
		double pricePerUnit = pt.getPricePerUnit();
		int quantityX = pt.getQuantity();
		int unitsN = 2;
		double discount = 0.2;
		assertTrue(ezShop.addProductToSale(id, "12345678901231", unitsN));
		assertEquals(pt.getQuantity(), (Integer) (quantityX - unitsN)); // X available quantity is decreased by N
		assertTrue(ezShop.applyDiscountRateToSale(id, discount));
		assertTrue(ezShop.endSaleTransaction(id));
		assertNotNull(ezShop.receiveCashPayment(id, 20));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance + (unitsN * pricePerUnit - unitsN * pricePerUnit * discount), ezShop.computeBalance(),
				0.1);
		ezShop.logout();

	}
	
	@Test
	public void testDeleteProductFromSaleRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException, InvalidOrderIdException, InvalidLocationException, InvalidRFIDException, InvalidTransactionIdException, InvalidPaymentException{
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteProductFromSaleRFID(1,"1111111"));
		ezShop.logout();
		
		ezShop.logout();
		ezShop.login("admin", "admin");
		Integer id = ezShop.getOrderId();
		int productQuantity = 2;
		assertEquals(id, ezShop.issueOrder("12345678901231", productQuantity, 1.0));
		assertTrue(ezShop.payOrder(id));
		assertTrue(ezShop.recordOrderArrivalRFID(id, "0000000010" ));


		
		it.polito.ezshop.data.ProductType pt = ezShop.getProductTypeByBarCode("12345678901231");
		int quantity = pt.getQuantity();
		int idSale = ezShop.startSaleTransaction();
		int unitsN = 2;	
		assertTrue(ezShop.addProductToSaleRFID(idSale, "0000000010"));
		assertTrue(ezShop.addProductToSaleRFID(idSale, "0000000011"));
		
		
		assertEquals(pt.getQuantity(), (Integer) (quantity - unitsN)); // X available quantity is decreased by N
		assertTrue(ezShop.deleteProductFromSaleRFID(idSale, "0000000011"));
		
		assertEquals(pt.getQuantity(), (Integer) ((quantity - unitsN)+1)); 
		
		assertFalse(ezShop.endSaleTransaction(id));
		assertTrue(ezShop.deleteSaleTransaction(id));
		ezShop.logout();

	}

	@Test
	public void testReturnTransactionAPI() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			InvalidCreditCardException {
		ezShop.logout();
		ezShop.login("WrongUsername", "WrongPassword");

		assertThrows(UnauthorizedException.class, () -> ezShop.startReturnTransaction(3));
		assertThrows(UnauthorizedException.class, () -> ezShop.returnProduct(1, "12345678901239", 5));
		assertThrows(UnauthorizedException.class, () -> ezShop.endReturnTransaction(1, true));
		assertThrows(UnauthorizedException.class, () -> ezShop.returnCashPayment(1));
		assertThrows(UnauthorizedException.class, () -> ezShop.returnCreditCardPayment(1, "5100293991053009"));
		assertThrows(UnauthorizedException.class, () -> ezShop.deleteReturnTransaction(1));

		ezShop.logout();

		ezShop.login("admin", "admin");

		assertThrows(InvalidTransactionIdException.class, () -> ezShop.startReturnTransaction(-1));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.returnProduct(-1, "12345678901239", 5));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.endReturnTransaction(-1, true));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.returnCashPayment(-1));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.returnCreditCardPayment(-1, "5100293991053009"));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.deleteReturnTransaction(-1));

		assertThrows(InvalidProductCodeException.class, () -> ezShop.returnProduct(1, "12344", 5));

		assertThrows(InvalidQuantityException.class, () -> ezShop.returnProduct(1, "12345678901231", -5));

		assertThrows(InvalidCreditCardException.class, () -> ezShop.returnCreditCardPayment(1, ""));

		Integer returnId = ezShop.getReturnId();
		Integer saleId = ezShop.getSaleId();

		assertEquals(ezShop.startReturnTransaction(saleId), (Integer) (-1));

		ezShop.startSaleTransaction();
		ezShop.addProductToSale(saleId, "12345678901231", 1);
		ezShop.endSaleTransaction(saleId);

		assertFalse(ezShop.returnProduct(returnId, "12345678901231", 1));
		assertFalse(ezShop.endReturnTransaction(returnId, true));
		assertEquals((Integer) (-1), ezShop.returnCashPayment(returnId), 0.0);
		assertEquals((Integer) (-1), ezShop.returnCreditCardPayment(returnId, "5100293991053009"), 0.0);

		assertEquals(returnId, ezShop.startReturnTransaction(saleId));
		assertTrue(ezShop.returnProduct(returnId, "12345678901231", 1));
		assertFalse(ezShop.returnProduct(returnId, "1234567891231", 1));
		assertFalse(ezShop.endReturnTransaction(returnId, false));
		assertTrue(ezShop.endReturnTransaction(returnId, true));
		assertNotNull(ezShop.returnCashPayment(returnId));
		assertNotNull(ezShop.returnCreditCardPayment(returnId, "5100293991053009"));
		assertTrue(ezShop.deleteReturnTransaction(returnId));

		ezShop.deleteProductFromSale(saleId, "12345678901231", 1);
		ezShop.deleteSaleTransaction(saleId);

		ezShop.logout();
	}

	@Test
	public void testManageReturnTransactionCreditCard() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			InvalidCreditCardException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();
		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier

		Integer returnId = ezShop.getReturnId();
		Integer saleId = ezShop.getSaleId();

		ezShop.startSaleTransaction();
		ezShop.addProductToSale(saleId, "12345678901231", 1);
		ezShop.endSaleTransaction(saleId);

		assertEquals(returnId, ezShop.startReturnTransaction(saleId));
		assertTrue(ezShop.returnProduct(returnId, "12345678901231", 1));
		assertTrue(ezShop.endReturnTransaction(returnId, true));

		double moneyRet = ezShop.returnCreditCardPayment(returnId, "5100293991053009");
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance - moneyRet, ezShop.computeBalance(), 0.1);
		ezShop.logout();

	}

	@Test
	public void testManageReturnTransactionCash() throws InvalidUsernameException, InvalidPasswordException,
			UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			InvalidCreditCardException {
		ezShop.logout();
		ezShop.login("admin", "admin");
		double balance = ezShop.computeBalance();

		ezShop.logout();

		ezShop.login("ciao", "ciao"); // Cashier

		Integer saleId = ezShop.getSaleId();
		Integer returnId = ezShop.getReturnId();

		ezShop.startSaleTransaction();
		ezShop.addProductToSale(saleId, "12345678901231", 1);
		ezShop.endSaleTransaction(saleId);

		assertEquals(returnId, ezShop.startReturnTransaction(saleId));
		assertTrue(ezShop.returnProduct(returnId, "12345678901231", 1));
		assertTrue(ezShop.endReturnTransaction(returnId, true));
		double moneyRet = ezShop.returnCashPayment(returnId);

		ezShop.logout();

		ezShop.login("admin", "admin");
		assertEquals(balance - moneyRet, ezShop.computeBalance(), 0.1);
		ezShop.logout();

	}

	@Test
	public void testGetCreditsAndDebits()
			throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("WrongUsername", "WrongPassword");

		assertThrows(UnauthorizedException.class,
				() -> ezShop.getCreditsAndDebits(LocalDate.parse("2021-05-17"), LocalDate.parse("2021-05-25")));

		ezShop.logout();
		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class,
				() -> ezShop.getCreditsAndDebits(LocalDate.parse("2021-05-17"), LocalDate.parse("2021-05-25")));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertNotNull(ezShop.getCreditsAndDebits(LocalDate.parse("2020-05-11"), LocalDate.parse("2022-05-10")));
		assertNotNull(ezShop.getCreditsAndDebits(LocalDate.parse("2022-05-10"), LocalDate.parse("2020-05-11")));
		assertNotNull(ezShop.getCreditsAndDebits(null, LocalDate.parse("2022-05-10")));
		assertNotNull(ezShop.getCreditsAndDebits(LocalDate.parse("2021-05-11"), null));
		assertNotNull(ezShop.getCreditsAndDebits(null, null));

		ezShop.logout();
	}

	@Test
	public void testComputeBalance() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException {
		ezShop.logout();
		ezShop.login("WrongUsername", "WrongPassword");

		assertThrows(UnauthorizedException.class, () -> ezShop.computeBalance());

		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.computeBalance());
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertNotNull(ezShop.computeBalance());
		ezShop.logout();
	}
	
	@Test
	public void testRecordOrderArrivalRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidRFIDException, InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		Integer id = ezShop.getOrderId() - 1;
		assertThrows(UnauthorizedException.class, () -> ezShop.recordOrderArrivalRFID(id,"1111"));
		ezShop.logout();

		ezShop.login("ciao", "ciao");
		assertThrows(UnauthorizedException.class, () -> ezShop.recordOrderArrivalRFID(id, "1111"));
		ezShop.logout();

		ezShop.login("admin", "admin");
		assertThrows(InvalidOrderIdException.class, () -> ezShop.recordOrderArrivalRFID(null, "0000000001"));
		assertThrows(InvalidOrderIdException.class, () -> ezShop.recordOrderArrivalRFID(-1, "00000001"));
		assertThrows(InvalidRFIDException.class, () -> ezShop.recordOrderArrivalRFID(1, "00000001"));
		assertThrows(InvalidRFIDException.class, () -> ezShop.recordOrderArrivalRFID(100, null));
		assertThrows(InvalidRFIDException.class, () -> ezShop.recordOrderArrivalRFID(1, "111111111A"));


		assertFalse(ezShop.recordOrderArrivalRFID(999999, "9999999999"));//no order
		
		
		
		int a = ezShop.payOrderFor("12345678901231", 10, 1.0);
		int b = ezShop.payOrderFor("12345678901231", 20, 1.0);

		assertTrue(ezShop.recordOrderArrivalRFID(a, "1000000001"));
		assertThrows(InvalidRFIDException.class, ()->ezShop.recordOrderArrivalRFID(b, "1000000001"));

		
		
		Integer ret2 = ezShop.payOrderFor("1234567890005", 10, 1.0);
		assertThrows(InvalidLocationException.class, () -> ezShop.recordOrderArrivalRFID(ret2, "0000000009"));
	}

	@Test
	public void testReturnProductRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, InvalidRFIDException, UnauthorizedException, InvalidQuantityException, InvalidOrderIdException, InvalidLocationException, InvalidProductCodeException, InvalidPricePerUnitException {
		ezShop.logout();
		ezShop.login("Beppe", "1234");
		assertThrows(UnauthorizedException.class, () -> ezShop.returnProductRFID(0, "0000000001"));
		ezShop.logout();
		
		ezShop.login("admin", "admin");
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.returnProductRFID(null, "0000000001"));
		assertThrows(InvalidTransactionIdException.class, () -> ezShop.returnProductRFID(-1, "00000001"));
		assertThrows(InvalidRFIDException.class, () -> ezShop.returnProductRFID(1, ""));
		assertThrows(InvalidRFIDException.class, () -> ezShop.returnProductRFID(100, null));
		assertThrows(InvalidRFIDException.class, () -> ezShop.returnProductRFID(1, "00000001"));
		assertThrows(InvalidRFIDException.class, () -> ezShop.returnProductRFID(1, "111111111A"));

		int tid = ezShop.getSaleId()-1;
		int rid = ezShop.startReturnTransaction(tid);
		int oid = ezShop.payOrderFor("12345678901231", 10, 1.0);
		ezShop.recordOrderArrivalRFID(oid, "0000000042");		
		ezShop.addProductToSale(tid, "12345678901231", 1);
		ezShop.endSaleTransaction(tid);
		ezShop.startReturnTransaction(tid);

		assertFalse(ezShop.returnProductRFID(999999, "9999999999"));
		assertFalse(ezShop.returnProductRFID(999999, "0000000042"));
		assertFalse(ezShop.returnProductRFID(rid, "9999999999"));
		assertFalse(ezShop.returnProductRFID(rid, "9999999999"));
		assertTrue(ezShop.returnProductRFID(rid, "0000000042"));
	
		ezShop.endReturnTransaction(rid, true);
		ezShop.deleteReturnTransaction(rid);
		ezShop.deleteProductFromSale(tid, "12345678901231", 1);
		ezShop.deleteSaleTransaction(tid);
		
		ezShop.logout();
	}

}
