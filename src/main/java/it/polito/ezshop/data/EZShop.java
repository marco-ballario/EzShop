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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


public class EZShop implements EZShopInterface {
	private LinkedHashMap<Integer, User> userList = null;
	private User loggedUser;
	private int userId=1;
	private LinkedHashMap<Integer, ProductType> productList = null;
	private int productId=1;
	private HashMap<Integer, Customer> customerList = null;
	private int customerId=1;
	private HashMap<Integer, Order> orderList = null;
	private int orderId=1;
	private HashMap<String, LoyaltyCard> loyaltyCardList = null;
	private int loyaltyCardId = 1;
	private HashMap<Integer, SaleTransaction> transactionList = null;
	private int saleId=1;
	private HashMap<Integer, ReturnTransaction> returnList = null;
	private int returnId=1;
	private AccountBook accounting = null;
	private List<Object> appState = new ArrayList<Object>();
	File f = new File("./src/main/java/it/polito/ezshop/appState.db");
	

    public EZShop() {
    	readAppState();
	}

	@SuppressWarnings("unchecked")
	private void readAppState() {
		if (!f.isFile() || !f.canRead()) {
			userList = new LinkedHashMap<Integer, User>();
		}
		else {
		try {
			 List<Object> e=null;
	         FileInputStream fileIn = new FileInputStream(f);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (List<Object>) in.readObject();
	         if(e != null) {
		         userList = (LinkedHashMap<Integer, User>) e.get(0);
		         userId = (Integer)e.get(1);
	         }
	         else {
	        	 userList = new LinkedHashMap<Integer, User>();
		         userId = 1;
	         }
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	        
	      }}
		return;
	}

	private boolean writeAppState() {
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
		appState.add(11, returnId);
		appState.add(12, accounting);
		appState.add(13, orderList);
		appState.add(14, orderId);
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
    	
    }

    @Override
    public Integer createUser(String username, String password, String role) throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
        if(username == null ||username.length() == 0  ) {
        	throw new InvalidUsernameException();
        }
        if(password == null || password.length() == 0 ) {
        	throw new InvalidPasswordException();
        }
        if(username == null || role.length() == 0 || (!role.equals("Administrator") && !role.equals("Cashier") && !role.equals("ShopManager"))) {
        	throw new InvalidRoleException();
        }
        //Check if username already present
        for(User u:userList.values()) {
        	if(u.getUsername().equals(username)) {
        		return -1;
        	}
        }
        //Create new user
    	User user = new it.polito.ezshop.model.User(username, password, role, userId);
    	System.out.println(userList);
        userList.put(userId, user); //insert user inside the data structure
        System.out.println(userList);
        Integer id = user.getId();
        userId=userId+1; //prepare Id for next user
        boolean res = writeAppState(); // update the state of the app
        if(res == false) {
        	return -1;
        }
    	return id;
    }

    @Override
    public boolean deleteUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
    	if(id==null || id <= 0) {
    		throw new InvalidUserIdException();
    	}
    	if(loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
    		throw new UnauthorizedException();
    	}
    	userList.remove(id);
        return false;
    }

    @Override
    public List<User> getAllUsers() throws UnauthorizedException {
    	if(loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
    		throw new UnauthorizedException();
    	}
        return (List<User>) userList.values();
    }

    @Override
    public User getUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
    	if(id==null || id <= 0) {
    		throw new InvalidUserIdException();
    	}
    	if(loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
    		throw new UnauthorizedException();
    	}
    	User user = userList.get(id);
        return user;
    }

    @Override
    public boolean updateUserRights(Integer id, String role) throws InvalidUserIdException, InvalidRoleException, UnauthorizedException {
    	if(id==null || id <= 0) {
    		throw new InvalidUserIdException();
    	}
    	if(role==null || role.length()==0 || (!role.equals("Administrator") && !role.equals("Cashier") && !role.equals("ShopManager"))) {
    		throw new InvalidRoleException();
    	}
    	if(loggedUser == null || !loggedUser.getRole().equals("Administrator")) {
    		throw new UnauthorizedException();
    	}
    	User user = userList.get(id);
    	if(user == null) {
    		return false;
    	}
    	user.setRole(role);
    	return true;
    }

    @Override
    public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
    	if(username == null || username.length()==0) {
    		throw new InvalidUsernameException();
    	}
    	if(password ==null || password.length()==0) {
    		throw new InvalidPasswordException();	
    	}
    	
    	Collection<User> list = userList.values();
    	for (User u: list) {
    		
    		if(u.getPassword().contentEquals(password) && u.getUsername().equals(username)) {
    			return u;
    		}
    	}
        return null;
    }

    @Override
    public boolean logout() {
    	if(loggedUser == null) {
    		return false;
    	}
    	else {
    		loggedUser = null;
    		return true;
    	} 
    }

    @Override
    public Integer createProductType(String description, String productCode, double pricePerUnit, String note) throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
    	if(description == null || description.length() == 0)
    		throw new InvalidProductDescriptionException();
    	
    	if(productCode == null || productCode.isEmpty()) 
    		throw new InvalidProductCodeException();
    	try{
			Integer.parseInt(productCode);
		}catch(NumberFormatException e) {
			throw new InvalidProductCodeException();
		}
    	// to do
    	
    	if(pricePerUnit <= 0)
    		throw new InvalidPricePerUnitException();
    	
    	if( !this.loggedUser.equals("Administrator") || !this.loggedUser.equals("ShopManager") )
    		throw new UnauthorizedException();
    	
    	if ( this.productList.get(this.productId) != null )
    		return -1;
    	
    	ProductType pt = new it.polito.ezshop.model.ProductType(description, productCode, pricePerUnit, note);
    	pt.setId(this.productId);
    	this.productList.put(this.productId, pt);
    	
    	if( this.writeAppState() == false )
    		return -1;
    	
    	return this.productId++;
    }

    @Override
    public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote) throws InvalidProductIdException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteProductType(Integer id) throws InvalidProductIdException, UnauthorizedException {
        return false;
    }

    @Override
    public List<ProductType> getAllProductTypes() throws UnauthorizedException {
        return null;
    }

    @Override
    public ProductType getProductTypeByBarCode(String barCode) throws InvalidProductCodeException, UnauthorizedException {
        return null;
    }

    @Override
    public List<ProductType> getProductTypesByDescription(String description) throws UnauthorizedException {
        return null;
    }

    @Override
    public boolean updateQuantity(Integer productId, int toBeAdded) throws InvalidProductIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean updatePosition(Integer productId, String newPos) throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
        return false;
    }

    @Override
    public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
        return null;
    }

    @Override
    public Integer payOrderFor(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean recordOrderArrival(Integer orderId) throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
        return false;
    }

    @Override
    public List<Order> getAllOrders() throws UnauthorizedException {
        return null;
    }

    @Override
    public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard) throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
        return false;
    }

    @Override
    public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() throws UnauthorizedException {
        return null;
    }

    @Override
    public String createCard() throws UnauthorizedException {
        return null;
    }

    @Override
    public boolean attachCardToCustomer(String customerCard, Integer customerId) throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded) throws InvalidCustomerCardException, UnauthorizedException {
        return false;
    }

    @Override
    public Integer startSaleTransaction() throws UnauthorizedException {
        return null;
    }

    @Override
    public boolean addProductToSale(Integer transactionId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteProductFromSale(Integer transactionId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidDiscountRateException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean applyDiscountRateToSale(Integer transactionId, double discountRate) throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
        return false;
    }

    @Override
    public int computePointsForSale(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean endSaleTransaction(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteSaleTransaction(Integer saleNumber) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public SaleTransaction getSaleTransaction(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return null;
    }

    @Override
    public Integer startReturnTransaction(Integer saleNumber) throws /*InvalidTicketNumberException,*/InvalidTransactionIdException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean returnProduct(Integer returnId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean endReturnTransaction(Integer returnId, boolean commit) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteReturnTransaction(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public double receiveCashPayment(Integer ticketNumber, double cash) throws InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean receiveCreditCardPayment(Integer ticketNumber, String creditCard) throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        return false;
    }

    @Override
    public double returnCashPayment(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
        return 0;
    }

    @Override
    public double returnCreditCardPayment(Integer returnId, String creditCard) throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean recordBalanceUpdate(double toBeAdded) throws UnauthorizedException {
        return false;
    }

    @Override
    public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
        return null;
    }

    @Override
    public double computeBalance() throws UnauthorizedException {
        return 0;
    }
}
