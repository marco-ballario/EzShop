package it.polito.ezshop.data;

import it.polito.ezshop.exceptions.*;
import it.polito.ezshop.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EZShop implements EZShopInterface {
	private HashMap<Integer, it.polito.ezshop.model.User> userList = new HashMap<Integer, it.polito.ezshop.model.User>();
	private User loggedUser;
	private int userId = 1;
	private HashMap<Integer, it.polito.ezshop.model.ProductType> productList = new HashMap<Integer, it.polito.ezshop.model.ProductType>();
	private int productId = 1;
	private HashMap<Integer, it.polito.ezshop.model.Customer> customerList = new HashMap<Integer, it.polito.ezshop.model.Customer>();;
	private int customerId = 1;
	private HashMap<Integer, it.polito.ezshop.model.Order> orderList = new HashMap<Integer, it.polito.ezshop.model.Order>();
	private int orderId = 1;
	private HashMap<String, it.polito.ezshop.model.LoyaltyCard> loyaltyCardList = new HashMap<String, it.polito.ezshop.model.LoyaltyCard>();
	private int loyaltyCardId = 1;
	private List<String> creditCards = new ArrayList<String>();
	private HashMap<Integer, it.polito.ezshop.model.SaleTransaction> transactionList = new HashMap<Integer, it.polito.ezshop.model.SaleTransaction>();;
	private int saleId = 1;
	private HashMap<Integer, it.polito.ezshop.model.ReturnTransaction> returnList = new HashMap<Integer, it.polito.ezshop.model.ReturnTransaction>();
	private int returnId = 1;
	private AccountBook accounting = AccountBook.getIstance(); // SINGLETON PATTERN THAT AVOIDS MULTIPLE ACCOUNTS
	private List<Object> appState = new ArrayList<Object>();
	private HashMap<Long, it.polito.ezshop.model.Product> listRFID = new HashMap<Long, it.polito.ezshop.model.Product>();
	private Tools tool = new Tools();
	File f = new File("./src/main/java/it/polito/ezshop/utils/appState.db");
	String fileLoyalty = "./src/main/java/it/polito/ezshop/utils/cards.in";
	String creditCardFile = "./src/main/java/it/polito/ezshop/utils/creditcards.txt";

	public EZShop() {

		readAppState();
	}

	public int getUserId() {
		return userId;
	}

	public int getProductId() {
		return productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getSaleId() {
		return saleId;
	}

	public int getReturnId() {
		return returnId;
	}

	@SuppressWarnings("unchecked")
	private void readAppState() {

		try {
			List<Object> e = null;
			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (List<Object>) in.readObject();
			if (e != null) {
				userList = (HashMap<Integer, it.polito.ezshop.model.User>) e.get(0);
				userId = (Integer) e.get(1);
				productList = (HashMap<Integer, it.polito.ezshop.model.ProductType>) e.get(2);
				productId = (Integer) e.get(3);
				customerList = (HashMap<Integer, it.polito.ezshop.model.Customer>) e.get(4);
				customerId = (Integer) e.get(5);
				loyaltyCardList = (HashMap<String, it.polito.ezshop.model.LoyaltyCard>) e.get(6);
				loyaltyCardId = (Integer) e.get(7);
				transactionList = (HashMap<Integer, it.polito.ezshop.model.SaleTransaction>) e.get(8);
				saleId = (Integer) e.get(9);
				returnList = (HashMap<Integer, it.polito.ezshop.model.ReturnTransaction>) e.get(10);
				creditCards = (ArrayList<String>) e.get(11);
				returnId = (Integer) e.get(12);
				accounting = (AccountBook) e.get(13);
				orderList = (HashMap<Integer, it.polito.ezshop.model.Order>) e.get(14);
				orderId = (Integer) e.get(15);
				listRFID = (HashMap<Long, it.polito.ezshop.model.Product>) e.get(16);
				;

			}
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException i) {
			return;

		}
		return;
	}

	private boolean writeAppState() {
		// save data structure inside the list that will be stored in the file
		appState.add(0, userList);
		appState.add(1, userId);
		appState.add(2, productList);
		appState.add(3, productId);
		appState.add(4, customerList);
		appState.add(5, customerId);
		appState.add(6, loyaltyCardList);
		appState.add(7, loyaltyCardId);
		appState.add(8, transactionList);
		appState.add(9, saleId);
		appState.add(10, returnList);
		appState.add(11, creditCards);
		appState.add(12, returnId);
		appState.add(13, accounting);
		appState.add(14, orderList);
		appState.add(15, orderId);
		appState.add(16, listRFID);

		try {

			FileOutputStream fileOut = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(appState);
			out.close();
			fileOut.close();
			return true;
		} catch (IOException i) {
			return false;
		}
	}

	@Override
	public void reset() {
		readAppState();
		this.customerId = 1;
		this.orderId = 1;
		this.loyaltyCardId = 1;
		this.userId = 1;
		this.orderId = 1;
		this.productId = 1;
		this.saleId = 1;
		this.returnId = 1;
		this.productList = new HashMap<Integer, it.polito.ezshop.model.ProductType>();
		this.transactionList = new HashMap<Integer, it.polito.ezshop.model.SaleTransaction>();
		this.loggedUser = null;
		this.orderList = new HashMap<Integer, it.polito.ezshop.model.Order>();
		this.accounting.resetBalance();
		this.returnList = new HashMap<Integer, it.polito.ezshop.model.ReturnTransaction>();
		this.customerList = new HashMap<Integer, it.polito.ezshop.model.Customer>();
		this.userList = new HashMap<Integer, it.polito.ezshop.model.User>();
		this.listRFID = new HashMap<Long, it.polito.ezshop.model.Product>();
		writeAppState();
	}

	@Override
	public Integer createUser(String username, String password, String role)
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		if (username == null || username.length() == 0) {
			throw new InvalidUsernameException();
		}
		if (password == null || password.length() == 0) {
			throw new InvalidPasswordException();
		}
		if (role == null || role.length() == 0
				|| (!role.equals("Administrator") && !role.equals("Cashier") && !role.equals("ShopManager"))) {
			throw new InvalidRoleException();
		}
		// Check if username already present
		for (User u : userList.values()) {
			if (u.getUsername().equals(username)) {
				return -1;
			}
		}
		// Create new user
		it.polito.ezshop.model.User user = new it.polito.ezshop.model.User(username, password, role, userId);
		userList.put(userId, user); // insert user inside the data structure
		Integer id = user.getId();
		userId = userId + 1; // prepare Id for next user
		boolean res = writeAppState(); // update the state of the app
		if (res == false) {
			return -1;
		}
		return id;
	}

	@Override
	public boolean deleteUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
		if (id == null || id <= 0) {
			throw new InvalidUserIdException();
		}
		if (loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
			throw new UnauthorizedException();
		}
		it.polito.ezshop.model.User res = userList.remove(id);
		if (res == null) {
			return false;
		}
		if (!writeAppState()) {
			return false;
		}
		return true;
	}

	@Override
	public List<User> getAllUsers() throws UnauthorizedException {
		if (loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
			throw new UnauthorizedException("cannot do it");
		}
		if (userList.values().size() == 0) {
			return new ArrayList<User>();
		}
		List<User> lu = new ArrayList<User>(userList.values());
		return lu;
	}

	@Override
	public User getUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
		if (loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
			throw new UnauthorizedException();
		}
		if (id == null || id <= 0) {
			throw new InvalidUserIdException("error in id");
		}
		User user = userList.get(id);
		return user;
	}

	@Override
	public boolean updateUserRights(Integer id, String role)
			throws InvalidUserIdException, InvalidRoleException, UnauthorizedException {
		if (loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
			throw new UnauthorizedException();
		}
		if (id == null || id <= 0) {
			throw new InvalidUserIdException();
		}
		if (role == null || role.length() == 0
				|| (!role.equals("Administrator") && !role.equals("Cashier") && !role.equals("ShopManager"))) {
			throw new InvalidRoleException("wrong role");
		}

		User user = userList.get(id);
		if (user == null) {
			return false;
		}
		user.setRole(role);
		if (!writeAppState()) {
			return false;
		}
		return true;
	}

	@Override
	public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
		if (username == null || username.length() == 0) {
			throw new InvalidUsernameException("invalid user name");
		}
		if (password == null || password.length() == 0) {
			throw new InvalidPasswordException("invalid password");
		}

		if (this.loggedUser != null) {
			return null;
		}

		Collection<it.polito.ezshop.model.User> list = userList.values();
		for (User u : list) {

			if (u.getPassword().contentEquals(password) && u.getUsername().equals(username)) {
				this.loggedUser = u;
				return u;
			}
		}
		return this.loggedUser;
	}

	@Override
	public boolean logout() {
		if (loggedUser == null) {
			return false;
		} else {
			loggedUser = null;
			return true;
		}
	}

	@Override
	public Integer createProductType(String description, String productCode, double pricePerUnit, String note)
			throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			UnauthorizedException {
		if (description == null || description.length() == 0)
			throw new InvalidProductDescriptionException("invalid product descriptor");

		if (productCode == null || productCode.isEmpty())
			throw new InvalidProductCodeException("Invalid prodict code");

		if (tool.checkDigit(productCode) == false)
			throw new InvalidProductCodeException();

		if (pricePerUnit <= 0)
			throw new InvalidPricePerUnitException("wrong price");

		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (this.productList.get(this.productId) != null)
			return -1;

		for (it.polito.ezshop.model.ProductType p : productList.values()) { // Check if a product with the same barcode
			// is already present
			if (p.getBarCode().equals(productCode))
				return -1;
		}

		it.polito.ezshop.model.ProductType pt = new it.polito.ezshop.model.ProductType(description, productCode,
				pricePerUnit, note);
		pt.setId(this.productId);
		this.productList.put(this.productId, pt);

		if (this.writeAppState() == false)
			return -1;

		return this.productId++;
	}

	@Override
	public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
			throws InvalidProductIdException, InvalidProductDescriptionException, InvalidProductCodeException,
			InvalidPricePerUnitException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (id <= 0 || id == null)
			throw new InvalidProductIdException("invalid product id");

		if (newDescription == null || newDescription.length() == 0)
			throw new InvalidProductDescriptionException();

		if (newCode == null || newCode.isEmpty())
			throw new InvalidProductCodeException();

		if (tool.checkDigit(newCode) == false)
			throw new InvalidProductCodeException();

		if (newPrice <= 0)
			throw new InvalidPricePerUnitException();

		it.polito.ezshop.model.ProductType p = productList.get(id);
		if (p == null)
			return false;

		// if p is not null update product
		p.setProductDescription(newDescription);
		p.setBarCode(newCode);
		p.setPricePerUnit(newPrice);
		p.setNote(newNote);

		boolean ret = writeAppState();
		return ret;
	}

	@Override
	public boolean deleteProductType(Integer id) throws InvalidProductIdException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (id == null || id <= 0)
			throw new InvalidProductIdException();

		it.polito.ezshop.model.ProductType p = productList.remove(id);

		if (p == null)
			return false;

		boolean ret = writeAppState();
		return ret;

	}

	@Override
	public List<ProductType> getAllProductTypes() throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager") && !this.loggedUser.getRole().equals("Cashier")))
			throw new UnauthorizedException();
		List<ProductType> l = new ArrayList<ProductType>(productList.values());
		return l;
	}

	@Override
	public ProductType getProductTypeByBarCode(String barCode)
			throws InvalidProductCodeException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (barCode == null || barCode.isEmpty())
			throw new InvalidProductCodeException();

		if (tool.checkDigit(barCode) == false)
			throw new InvalidProductCodeException();

		for (it.polito.ezshop.model.ProductType p : productList.values()) {
			if (p.getBarCode().equals(barCode))
				return p;
		}

		return null;
	}

	@Override
	public List<ProductType> getProductTypesByDescription(String description) throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		List<ProductType> lp = new LinkedList<ProductType>();

		if (description == null || description.equals("")) {
			return new LinkedList<ProductType>(productList.values());
		}

		for (ProductType p : productList.values()) {
			if (p.getProductDescription().contains(description)) {
				lp.add(p);
			}

		}
		return lp;
	}

	@Override
	public boolean updateQuantity(Integer productId, int toBeAdded)
			throws InvalidProductIdException, UnauthorizedException {

		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (productId == null || productId <= 0)
			throw new InvalidProductIdException();

		it.polito.ezshop.model.ProductType p = productList.get(productId);

		if (p == null)
			return false;
		if (p.getLocation().isEmpty())
			return false;

		Integer previousQuantity = p.getQuantity();

		if (toBeAdded < 0) {
			p.decreaseQuantity(-1 * toBeAdded);

			if (p.getQuantity() < 0) { // if the final quantity is negative, the previous quantity is restored and it's
				// not updated
				p.setQuantity(previousQuantity);

				return false;
			}
		} else
			p.increaseQuantity(toBeAdded);
		boolean ret = writeAppState();
		return ret;
	}

	@Override
	public boolean updatePosition(Integer productId, String newPos)
			throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (productId == null || productId <= 0)
			throw new InvalidProductIdException();

		String[] pos = newPos.split("-");
		if (!newPos.equals("") && pos.length < 3) // invalid format
			throw new InvalidLocationException("wrong location");

		try {
			Integer.parseInt(pos[0]);
			Integer.parseInt(pos[2]);
		} catch (NumberFormatException e) {
			if (!newPos.equals("")) {
				throw new InvalidLocationException();
			}
		}

		for (ProductType pt : productList.values()) { // if another product has the same position return false
			if (pt.getId() != productId && newPos != null && !newPos.equals("") && pt.getLocation().equals(newPos))
				return false;
		}

		it.polito.ezshop.model.ProductType p = productList.get(productId);

		if (p == null)
			return false;

		if (newPos == null || newPos.isEmpty()) // if newPos is empty or null it resets the location of the given
			// product
			p.setLocation("");
		else
			p.setLocation(newPos);

		boolean ret = writeAppState();
		return ret;
	}

	@Override
	public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException,
			InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (productCode == null || productCode.isEmpty() || !tool.checkDigit(productCode))
			throw new InvalidProductCodeException();
		if (quantity <= 0)
			throw new InvalidQuantityException();
		if (pricePerUnit <= 0)
			throw new InvalidPricePerUnitException();
		boolean flag = false;
		for (ProductType pt : productList.values()) {
			if (pt.getBarCode().equals(productCode))
				flag = true;
		}
		if (flag == false) {
			return -1;
		}

		it.polito.ezshop.model.Order newOrder = new it.polito.ezshop.model.Order(productCode, quantity, pricePerUnit);
		newOrder.setOrderId(this.orderId);
		newOrder.setStatus("ISSUED");
		this.orderList.put(this.orderId, newOrder);
		if (this.writeAppState() == false)
			return -1;

		return this.orderId++;
	}

	@Override
	public Integer payOrderFor(String productCode, int quantity, double pricePerUnit)
			throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException,
			UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (productCode == null || productCode.isEmpty() || !tool.checkDigit(productCode))
			throw new InvalidProductCodeException();

		if (quantity <= 0)
			throw new InvalidQuantityException();
		if (pricePerUnit <= 0)
			throw new InvalidPricePerUnitException();
		if (quantity * pricePerUnit >= this.accounting.getBalance()) {
			return -1;
		}

		it.polito.ezshop.model.Order newOrder = new it.polito.ezshop.model.Order(productCode, quantity, pricePerUnit);
		newOrder.setOrderId(this.orderId);

		it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		this.accounting.insertBalanceOperation(b, -pricePerUnit * quantity);
		newOrder.setStatus("PAYED");
		it.polito.ezshop.model.ProductType pt = (it.polito.ezshop.model.ProductType) this
				.getProductTypeByBarCode(productCode);
		if (pt == null) {
			return -1;
		}
		pt.increaseQuantity(quantity);
		this.orderList.put(this.orderId, newOrder);
		boolean res = writeAppState();
		if (res == false) {
			return -1;
		}

		return this.orderId++;
	}

	@Override
	public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (orderId == null || orderId <= 0) {
			throw new InvalidOrderIdException("invalid id of order");
		}
		Order order = this.orderList.get(orderId);
		if (order == null || !order.getStatus().equals("ISSUED"))
			return false;

		if (order.getPricePerUnit() * order.getQuantity() > this.accounting.getBalance()) {
			return false;
		}
		order.setStatus("PAYED");
		String pc = order.getProductCode();
		it.polito.ezshop.model.ProductType pt;
		try {
			pt = (it.polito.ezshop.model.ProductType) this.getProductTypeByBarCode(pc);
			if (pt == null) {
				return false;
			}
			pt.increaseQuantity(order.getQuantity());
			it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
			this.accounting.insertBalanceOperation(b, -order.getPricePerUnit() * order.getQuantity());
		} catch (InvalidProductCodeException | UnauthorizedException e) {
			return false;
		}
		boolean res = writeAppState();
		return res;
	}

	@Override
	public boolean recordOrderArrival(Integer orderId)
			throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (orderId == null || orderId <= 0) {
			throw new InvalidOrderIdException();
		}

		Order order = this.orderList.get(orderId);
		if (order == null)
			return false;
		if (order.getStatus().equals("PAYED")) {

			it.polito.ezshop.model.ProductType product = null;
			try {
				product = (it.polito.ezshop.model.ProductType) this.getProductTypeByBarCode(order.getProductCode());
				if (product.getLocation() == null || product.getLocation().equals("")) {
					throw new InvalidLocationException();
				}
			} catch (InvalidProductCodeException | UnauthorizedException e) {
				return false;
			}
			order.setStatus("COMPLETED");
			product.increaseQuantity(order.getQuantity());
			boolean res = writeAppState();
			return res;

		} else {
			return false;
		}
	}

	@Override
	public List<Order> getAllOrders() throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		List<Order> lo = new ArrayList<it.polito.ezshop.data.Order>(orderList.values());
		return lo;
	}

	@Override
	public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {
		if (customerName == null || customerName.length() == 0) {
			throw new InvalidCustomerNameException();
		}
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager") && !this.loggedUser.getRole().equals("Cashier"))) {
			throw new UnauthorizedException();
		}
		it.polito.ezshop.model.Customer c = new it.polito.ezshop.model.Customer(customerName);
		c.setId(customerId);
		customerList.put(customerId, c);

		boolean res = writeAppState();
		if (res == false) {
			return -1;
		}
		return customerId++;
	}

	@Override
	public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard)
			throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException,
			UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (newCustomerName == null || newCustomerName.length() == 0)
			throw new InvalidCustomerNameException("invalid name");
		if (id == null || id <= 0) {
			throw new InvalidCustomerIdException("error in customer id");
		}

		if (newCustomerCard != null && newCustomerCard.length() != 0) {
			try {
				Long.parseLong(newCustomerCard);
			} catch (NumberFormatException e) {
				throw new InvalidCustomerCardException();
			}
			LoyaltyCard lc = this.loyaltyCardList.get(newCustomerCard);
			if (lc == null || newCustomerCard.length() != 10) {
				throw new InvalidCustomerCardException();
			}
		}

		Customer c = customerList.get(id);
		if (c == null) {
			return false;
		}
		c.setCustomerName(newCustomerName);

		boolean isAssigned = false;
		boolean updateSuccess = true;

		if (newCustomerCard != null && newCustomerCard.length() == 0) {
			c.setCustomerCard(null);
			updateSuccess = true;
		} else if (newCustomerCard != null) {

			for (Customer cu : customerList.values()) {
				if (cu.getCustomerCard() != null && cu.getCustomerCard().equals(newCustomerCard)
						&& cu.getId() != c.getId()) {
					isAssigned = true;
					break; // someone already has it
				}
			}
			if (isAssigned == false)
				this.attachCardToCustomer(newCustomerCard, id);
			updateSuccess = !isAssigned;

		}
		boolean ret = writeAppState();
		if (ret == false)
			return false;
		return updateSuccess;
	}

	@Override
	public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (id == null || id <= 0)
			throw new InvalidCustomerIdException();

		Customer c = customerList.remove(id);
		if (c == null) {
			return false;
		}
		boolean ret = writeAppState();
		return ret;
	}

	@Override
	public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (id == null || id <= 0)
			throw new InvalidCustomerIdException();
		Customer c = customerList.get(id);
		return c;
	}

	@Override
	public List<Customer> getAllCustomers() throws UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (customerList.values().size() == 0) {
			new ArrayList<Customer>();
		}
		return new ArrayList<Customer>(customerList.values());
	}

	@Override
	public String createCard() throws UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		LoyaltyCard lc = new LoyaltyCard();
		ArrayList<Long> cards = tool.readLoyalty(this.fileLoyalty);
		if (cards == null) {
			return "";
		}
		String code = cards.remove(0).toString();
		if (!tool.updateLoyalty(cards, this.fileLoyalty)) {
			return "";
		}
		lc.setCode(code);
		lc.setPoints(0);
		loyaltyCardList.put(code, lc);
		boolean ret = writeAppState();
		if (ret == false)
			return "";
		return code;
	}

	@Override
	public boolean attachCardToCustomer(String customerCard, Integer customerId)
			throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (customerId == null || customerId <= 0)
			throw new InvalidCustomerIdException();
		if (customerCard == null || customerCard.length() != 10) {
			throw new InvalidCustomerCardException("invalid card");
		}
		try {
			Long.parseLong(customerCard);
		} catch (NumberFormatException e) {
			throw new InvalidCustomerCardException();
		}

		it.polito.ezshop.model.Customer c = customerList.get(customerId);
		if (c == null) {
			return false;
		}
		LoyaltyCard lc = loyaltyCardList.get(customerCard);

		for (Customer cu : customerList.values()) {
			if (cu.getCustomerCard() != null && cu.getCustomerCard().equals(customerCard)) {
				return false; // someone alreay has it
			}
		}

		c.setLoyaltyCard(lc);
		boolean ret = writeAppState();
		if (ret == false) {
			// rollback
			c.setCustomerCard("");
			return false;
		}
		return true;
	}

	@Override
	public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded)
			throws InvalidCustomerCardException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		// card validity
		if (customerCard == null || customerCard.length() != 10) {
			throw new InvalidCustomerCardException();
		}
		try {
			Long.parseLong(customerCard);// check is composed by numbers
		} catch (NumberFormatException e) {
			throw new InvalidCustomerCardException();
		}
		// end check on card

		LoyaltyCard lc = loyaltyCardList.get(customerCard);
		if (lc == null || (lc.getPoints() + pointsToBeAdded) < 0) {
			return false;
		}

		Integer oldPoint = lc.getPoints();
		lc.setPoints(oldPoint + pointsToBeAdded);

		if (!writeAppState()) {
			lc.setPoints(oldPoint); // RollBack
			return false;
		}
		return true;
	}

	@Override
	public Integer startSaleTransaction() throws UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		it.polito.ezshop.model.SaleTransaction st = new it.polito.ezshop.model.SaleTransaction();
		st.setTicketNumber(this.saleId);
		st.setStatus("open");
		this.transactionList.put(this.saleId, st);

		return this.saleId++;
	}

	@Override
	public boolean addProductToSale(Integer transactionId, String productCode, int amount)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException("invalid trasaction id");

		if (amount < 0)
			throw new InvalidQuantityException();

		if (productCode == null || productCode.isEmpty() == true || !tool.checkDigit(productCode))
			throw new InvalidProductCodeException();

		it.polito.ezshop.model.SaleTransaction st = transactionList.get(transactionId);
		if (st == null)
			return false;

		it.polito.ezshop.model.ProductType pt = null;
		for (it.polito.ezshop.model.ProductType p : productList.values()) {
			if (p.getBarCode().equals(productCode)) {
				pt = p;
			}
		}

		if (pt == null)
			return false;

		int oldQuantity = pt.getQuantity();
		if (oldQuantity - amount < 0)
			return false;
		pt.decreaseQuantity(amount);

		st.addProduct(pt, amount); // to be checked

		return true;
	}

	@Override
	public boolean deleteProductFromSale(Integer transactionId, String productCode, int amount)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		if (productCode == null || productCode.length() == 0 || !tool.checkDigit(productCode))
			throw new InvalidProductCodeException();
		if (amount < 0)
			throw new InvalidQuantityException("invalid qnt");
		it.polito.ezshop.model.SaleTransaction st = this.transactionList.get(transactionId);
		it.polito.ezshop.model.ProductType pt = null;
		for (it.polito.ezshop.model.ProductType p : productList.values()) {
			if (p.getBarCode().equals(productCode)) {
				pt = p;
			}
		}

		if (st == null || !st.getStatus().equals("open") || pt == null) {
			return false;
		}

		boolean res = st.removeProducts(productCode, amount);
		pt.increaseQuantity(amount);

		return res;
	}

	@Override
	public boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidDiscountRateException,
			UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		if (productCode == null || productCode.length() == 0 || !tool.checkDigit(productCode))
			throw new InvalidProductCodeException();
		if (discountRate >= 1 || discountRate < 0)
			throw new InvalidDiscountRateException("error in discount");

		it.polito.ezshop.model.SaleTransaction st = transactionList.get(transactionId);
		it.polito.ezshop.model.ProductType pt = null;
		for (it.polito.ezshop.model.ProductType p : productList.values()) {
			if (p.getBarCode().equals(productCode)) {
				pt = p;
			}
		}

		if (pt == null || st == null || !st.getStatus().equals("open")) {
			return false;
		}

		return st.applyProdDisc(pt, discountRate);
	}

	@Override
	public boolean applyDiscountRateToSale(Integer transactionId, double discountRate)
			throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		if (discountRate >= 1 || discountRate < 0)
			throw new InvalidDiscountRateException();

		it.polito.ezshop.model.SaleTransaction st = this.transactionList.get(transactionId);
		if (st == null || st.getStatus().equals("payed")) {
			return false;
		}
		st.setPrice(st.getPrice() * (1 - discountRate));
		st.setDiscountRate(discountRate);

		return true;
	}

	@Override
	public int computePointsForSale(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		it.polito.ezshop.model.SaleTransaction sl = this.transactionList.get(transactionId);
		if (sl == null) {
			return -1;
		}
		double tot = sl.getPrice();
		int point = (int) (tot / 10);
		sl.setTransactionPoints(point);

		return point;
	}

	@Override
	public boolean endSaleTransaction(Integer transactionId)
			throws InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		it.polito.ezshop.model.SaleTransaction sl = this.transactionList.get(transactionId);
		if (sl == null || sl.getStatus().equals("closed")) {
			return false;
		}
		sl.setStatus("closed");
		if (!writeAppState()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSaleTransaction(Integer saleNumber)
			throws InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (saleNumber == null || saleNumber <= 0)
			throw new InvalidTransactionIdException();
		it.polito.ezshop.model.SaleTransaction sl = this.transactionList.get(saleNumber);
		if (sl == null || sl.getStatus().equals("payed")) {
			return false;
		}

		List<it.polito.ezshop.data.TicketEntry> saleProducts = sl.getEntries();

		for (TicketEntry t : saleProducts) {
			for (it.polito.ezshop.model.ProductType p : productList.values()) {
				if (t.getBarCode().equals(p.getBarCode()))
					p.increaseQuantity(t.getAmount());
			}
		}

		this.transactionList.remove(saleNumber);

		if (!writeAppState()) {
			return false;
		}
		return true;
	}

	@Override
	public SaleTransaction getSaleTransaction(Integer transactionId)
			throws InvalidTransactionIdException, UnauthorizedException {

		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();

		it.polito.ezshop.model.SaleTransaction sl = this.transactionList.get(transactionId);

		if (sl != null && sl.getStatus().equals("closed")) {
			return sl;
		}

		return null;
	}

	@Override
	public Integer startReturnTransaction(Integer saleNumber)
			throws /* InvalidTicketNumberException, */InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (saleNumber == null || saleNumber <= 0)
			throw new InvalidTransactionIdException();
		it.polito.ezshop.model.SaleTransaction s = transactionList.get(saleNumber);
		if (s == null) {
			return -1;
		}
		ReturnTransaction rt = new ReturnTransaction(returnId, s);
		returnList.put(returnId, rt);
		// add return transaction to sale list
		List<ReturnTransaction> ret = s.getReturnTransactions();
		ret.add(rt);
		s.setReturnTransactions(ret);

		return returnId++;
	}

	@Override
	public boolean returnProduct(Integer returnId, String productCode, int amount) throws InvalidTransactionIdException,
			InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {

		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (returnId == null || returnId <= 0) {
			throw new InvalidTransactionIdException();
		}
		if (productCode == null || productCode.length() == 0 || tool.checkDigit(productCode) == false) {
			throw new InvalidProductCodeException();
		}
		if (amount <= 0) {
			throw new InvalidQuantityException();
		}
		it.polito.ezshop.model.ProductType pt = null;
		for (it.polito.ezshop.model.ProductType p : productList.values()) {
			if (p.getBarCode().equals(productCode)) {
				pt = p;
			}
		}

		if (pt == null)
			return false;

		ReturnTransaction rt = returnList.get(returnId);
		if (rt == null) {
			return false;
		}
		it.polito.ezshop.model.SaleTransaction st = rt.getOriginalTransaction();
		TicketEntry t = st.getTicketEntry(productCode);
		if (t == null) {
			return false;
		}
		if (t.getAmount() < amount) {
			return false;
		}

		rt.setOriginalTransaction(st);
		rt.setReturnId(returnId);
		rt.addProduct(pt, amount,
				pt.getPricePerUnit() * amount * (1 - t.getDiscountRate()) * (1 - st.getDiscountRate()));

		return true;
	}

	@Override
	public boolean endReturnTransaction(Integer returnId, boolean commit)
			throws InvalidTransactionIdException, UnauthorizedException {
		if (returnId == null || returnId <= 0) {
			throw new InvalidTransactionIdException();
		}
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		ReturnTransaction rt = returnList.get(returnId);
		if (rt == null) {
			return false;
		}
		it.polito.ezshop.model.SaleTransaction st = rt.getOriginalTransaction();
		HashMap<it.polito.ezshop.model.ProductType, Integer> retProds = rt.getReturnProducts();
		if (commit) {
			rt.setCommitted(true);
			// update product quantity in the shop
			for (it.polito.ezshop.model.ProductType pt : retProds.keySet()) {
				/*
				 * try { this.updateQuantity(pt.getId(), +rt.getReturnProducts().get(pt)); }
				 * catch (InvalidProductIdException | UnauthorizedException e) { return false; }
				 */
				pt.increaseQuantity(+rt.getReturnProducts().get(pt));
				st.setPrice(st.getPrice() - rt.getAmount());// update price
				st.updateStatusMin(returnId);

			}
			if (!writeAppState())
				return false;
		} else {
			rt.setCommitted(false);
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteReturnTransaction(Integer returnId)
			throws InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (returnId == null || returnId <= 0) {
			throw new InvalidTransactionIdException();
		}

		ReturnTransaction rt = returnList.get(returnId);
		it.polito.ezshop.model.SaleTransaction st = rt.getOriginalTransaction();
		HashMap<it.polito.ezshop.model.ProductType, Integer> retProds = rt.getReturnProducts();

		for (it.polito.ezshop.model.ProductType p : retProds.keySet()) {
			/*
			 * try { this.updateQuantity(p.getId(), retProds.get(p)); } catch
			 * (InvalidProductIdException | UnauthorizedException e) { return false; }
			 */
			p.increaseQuantity(+rt.getReturnProducts().get(p));
			st.setPrice(st.getPrice() + p.getPricePerUnit() * retProds.get(p));// remove old price
			st.updateStatusPlus(returnId);

		}

		returnList.remove(rt.getReturnId());

		if (!writeAppState()) {
			return false;
		}

		return true;
	}

	@Override
	public double receiveCashPayment(Integer transactionId, double cash)
			throws InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();
		if (cash <= 0)
			throw new InvalidPaymentException("error in payment");

		it.polito.ezshop.model.SaleTransaction s = transactionList.get(transactionId);
		if (s == null || s.getPrice() > cash)
			return -1;

		it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		this.accounting.insertBalanceOperation(b, s.getPrice());
		// s.setStatus("payed");
		if (!writeAppState()) {
			return -1;
		}
		return cash - s.getPrice();
	}

	@Override
	public boolean receiveCreditCardPayment(Integer transactionID, String creditCard)
			throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (transactionID == null || transactionID <= 0)
			throw new InvalidTransactionIdException();

		if (creditCard == null || creditCard.length() == 0 || !tool.checkCardLuhn(creditCard))
			throw new InvalidCreditCardException("invalid credit card");

		it.polito.ezshop.model.SaleTransaction s = transactionList.get(transactionID);
		if (s == null)
			return false;

		try {
			if (!tool.paymentCreditCards(creditCard, s.getPrice(), this.creditCardFile))
				return false;
		} catch (NumberFormatException | IOException e) {
			return false;
		}

		it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		if (!this.accounting.insertBalanceOperation(b, s.getPrice())) {
			return false;
		}
		// s.setStatus("payed");

		if (!writeAppState()) {
			return false;
		}
		return true;
	}

	@Override
	public double returnCashPayment(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (returnId == null || returnId <= 0)
			throw new InvalidTransactionIdException();

		ReturnTransaction r = returnList.get(returnId);
		if (r == null || !r.isCommitted())
			return -1;

		double moneyReturned = r.getAmount();

		it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		if (!this.accounting.insertBalanceOperation(b, -moneyReturned)) {
			return -1;
		}
		if (!writeAppState()) {
			return -1;
		}
		return moneyReturned;
	}

	@Override
	public double returnCreditCardPayment(Integer returnId, String creditCard)
			throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();
		if (returnId == null || returnId <= 0)
			throw new InvalidTransactionIdException();
		if (creditCard == null || creditCard.length() == 0 || !tool.checkCardLuhn(creditCard))
			throw new InvalidCreditCardException();
		ReturnTransaction r = returnList.get(returnId);
		if (r == null || !r.isCommitted())
			return -1;

		double moneyReturned = r.getAmount();
		it.polito.ezshop.model.BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		this.accounting.insertBalanceOperation(b, -moneyReturned);

		try {
			if (!tool.paymentCreditCards(creditCard, -r.getAmount(), this.creditCardFile))
				return -1;
		} catch (NumberFormatException | IOException e) {
			return -1;
		}

		if (!writeAppState()) {
			return -1;
		}

		return moneyReturned;
	}

	@Override
	public boolean recordBalanceUpdate(double toBeAdded) throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager"))) {
			throw new UnauthorizedException();
		}
		BalanceOperation b = new it.polito.ezshop.model.BalanceOperation();
		boolean res = accounting.insertBalanceOperation(b, toBeAdded);
		if (!writeAppState()) {
			return false;
		}
		return res;
	}

	@Override
	public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager"))) {
			throw new UnauthorizedException();
		}
		if (from != null && to != null && from.compareTo(to) > 0) {
			// invert dates if from is gretaer than to
			LocalDate tmp = from;
			from = to;
			to = tmp;
		}
		List<BalanceOperation> result = new ArrayList<BalanceOperation>();
		if (from == null && to == null) {
			result = accounting.getOperationList();
		} else if (from == null) {
			for (BalanceOperation bo : accounting.getOperationList()) {
				if (bo.getDate().compareTo(to) <= 0) {
					result.add(bo);
				}
			}
		} else if (to == null) {
			for (BalanceOperation bo : accounting.getOperationList()) {
				if (bo.getDate().compareTo(from) >= 0) {
					result.add(bo);
				}
			}
		} else {
			for (BalanceOperation bo : accounting.getOperationList()) {
				if (bo.getDate().compareTo(from) >= 0 && bo.getDate().compareTo(to) <= 0) {
					result.add(bo);
				}
			}
		}
		return result;
	}

	@Override
	public double computeBalance() throws UnauthorizedException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager"))) {
			throw new UnauthorizedException();
		}

		return accounting.getBalance();
	}

	@Override
	public boolean recordOrderArrivalRFID(Integer orderId, String RFIDfrom)
			throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidRFIDException {
		if (this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator")
				&& !this.loggedUser.getRole().equals("ShopManager"))) {
			throw new UnauthorizedException();
		}
		if (orderId == null || orderId <= 0) {
			throw new InvalidOrderIdException();

		}

		if (RFIDfrom == null || RFIDfrom.length() != 10) {
			throw new InvalidRFIDException();
		}
		Long rfid_from = null;
		try {
			rfid_from = Long.parseLong(RFIDfrom);
		} catch (NumberFormatException e) {
			throw new InvalidRFIDException();
		}

		Order order = this.orderList.get(orderId);
		if (order == null)
			return false;
		if (order.getStatus().equals("PAYED")) {

			it.polito.ezshop.model.ProductType productType = null;
			try {
				productType = (it.polito.ezshop.model.ProductType) this.getProductTypeByBarCode(order.getProductCode());
				if (productType.getLocation() == null || productType.getLocation().equals("")) {
					throw new InvalidLocationException();
				}
			} catch (InvalidProductCodeException | UnauthorizedException e) {
				return false;
			}

			order.setStatus("COMPLETED");
			productType.increaseQuantity(order.getQuantity());
			Long rfid_tmp = rfid_from;
			for (int i = 0; i < order.getQuantity(); i++) {
				if (listRFID.get(rfid_tmp) != null) {
					throw new InvalidRFIDException();
				}
				rfid_tmp++;
			}

			for (int i = 0; i < order.getQuantity(); i++) {
				Product p = new it.polito.ezshop.model.Product(rfid_from, productType);
				listRFID.put(rfid_from, p);
				rfid_from++;
			}

			boolean res = writeAppState();
			return res;

		} else {
			return false;
		}

	}

	@Override
	public boolean addProductToSaleRFID(Integer transactionId, String RFID) throws InvalidTransactionIdException,
			InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();

		if (RFID == null || RFID.length() != 10) {
			throw new InvalidRFIDException();
		}
		Long rfid_from = null;
		try {
			rfid_from = Long.parseLong(RFID);
		} catch (NumberFormatException e) {
			throw new InvalidRFIDException();
		}

		it.polito.ezshop.model.SaleTransaction st = transactionList.get(transactionId);
		if (st == null)
			return false;

		// remove or not from list????
		Product p = listRFID.get(rfid_from);

		if (p == null) {
			return false;
		}

		it.polito.ezshop.model.ProductType pt = p.getProductType();

		if (pt == null)
			return false;

		pt.decreaseQuantity(1);
		st.addProduct(pt, 1); // to be checked

		return true;

	}

	@Override
	public boolean deleteProductFromSaleRFID(Integer transactionId, String RFID) throws InvalidTransactionIdException,
			InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (transactionId == null || transactionId <= 0)
			throw new InvalidTransactionIdException();

		if (RFID == null || RFID.length() != 10) {
			throw new InvalidRFIDException();
		}
		Long rfid_from = null;
		try {
			rfid_from = Long.parseLong(RFID);
		} catch (NumberFormatException e) {
			throw new InvalidRFIDException();
		}

		it.polito.ezshop.model.SaleTransaction st = this.transactionList.get(transactionId);

		Product p = listRFID.get(rfid_from);

		if (p == null) {
			return false;
		}

		it.polito.ezshop.model.ProductType pt = p.getProductType();

		if (st == null || !st.getStatus().equals("open") || pt == null) {
			return false;
		}

		boolean res = st.removeProducts(pt.getBarCode(), 1);
		pt.increaseQuantity(1);

		return res;
	}

	@Override
	public boolean returnProductRFID(Integer returnId, String RFID)
			throws InvalidTransactionIdException, InvalidRFIDException, UnauthorizedException {
		if (this.loggedUser == null
				|| (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator")
						&& !this.loggedUser.getRole().equals("ShopManager")))
			throw new UnauthorizedException();

		if (returnId == null || returnId <= 0) {
			throw new InvalidTransactionIdException();
		}
		if (RFID == null || RFID.length() != 10) {
			throw new InvalidRFIDException();
		}
		Long rfid_from = null;
		try {
			rfid_from = Long.parseLong(RFID);
		} catch (NumberFormatException e) {
			throw new InvalidRFIDException();
		}
		Product p = listRFID.get(rfid_from);

		if (p == null)
			return false;

		it.polito.ezshop.model.ProductType pt = p.getProductType();

		if (pt == null)
			return false;

		ReturnTransaction rt = returnList.get(returnId);
		if (rt == null) {
			return false;
		}
		it.polito.ezshop.model.SaleTransaction st = rt.getOriginalTransaction();
		TicketEntry t = st.getTicketEntry(pt.getBarCode());
		if (t == null) {
			return false;
		}
		if (t.getAmount() < 1) {
			return false;
		}

		rt.setOriginalTransaction(st);
		rt.setReturnId(returnId);
		rt.addProduct(pt, 1, pt.getPricePerUnit() * (1 - t.getDiscountRate()) * (1 - st.getDiscountRate()));

		return true;
	}

}
