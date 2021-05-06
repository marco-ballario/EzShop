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
import java.util.LinkedList;
import java.util.List;


public class EZShop implements EZShopInterface {
	private LinkedHashMap<Integer, User> userList = null;
	private User loggedUser;
	private int userId;
	private LinkedHashMap<Integer, ProductType> productList = null;
	private int productId;
	private HashMap<Integer, Customer> customerList = null;
	private int customerId;
	private HashMap<Integer, Order> orderList = null;
	private int orderId;
	private HashMap<String, LoyaltyCard> loyaltyCardList = null;
	private int loyaltyCardId;
	private HashMap<Integer, SaleTransaction> transactionList = null;
	private int saleId;
	private HashMap<Integer, ReturnTransaction> returnList = null;
	private int returnId;
	private AccountBook accounting = AccountBook.getIstance(); //SINGLETON PATTERN THAT AVOIDS MULTIPLE ACCOUNTS
	private List<Object> appState = new ArrayList<Object>();
	File f = new File("./src/main/java/it/polito/ezshop/appState.db");
	

    public EZShop() {
    	readAppState();
	}

	@SuppressWarnings("unchecked")
	private void readAppState() {
		if (!f.isFile() || !f.canRead()) {
			userList = new LinkedHashMap<Integer, User>();
			productList = new LinkedHashMap<Integer, ProductType>();
			customerList = new HashMap<Integer, Customer>();
			orderList = new HashMap<Integer, Order>();
			loyaltyCardList = new HashMap<String, LoyaltyCard>();
			transactionList = new HashMap<Integer, SaleTransaction>();
			returnList = new HashMap<Integer, ReturnTransaction>();
			userId = 1;
			productId=1;
			customerId=1;
			orderId=1;
			loyaltyCardId =1;
			saleId = 1;
			returnId=1;
		}
		else {
		try {
			 List<Object> e=null;
	         FileInputStream fileIn = new FileInputStream(f);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (List<Object>) in.readObject();
	         if(e != null) {
	        	 //cast back read elements
		         userList = (LinkedHashMap<Integer, User>) e.get(0);
		         userId = (Integer)e.get(1);
		         productList = (LinkedHashMap<Integer, ProductType>)e.get(2);
		         productId =(Integer)e.get(3);
		         customerList = (HashMap<Integer, Customer>) e.get(4);
		         customerId = (Integer) e.get(5);
		         loyaltyCardList =(HashMap<String, LoyaltyCard>) e.get(6);
		 		 loyaltyCardId = (Integer) e.get(7);
		 		 transactionList = (HashMap<Integer, SaleTransaction>) e.get(8);
		 		 saleId =  (Integer) e.get(9);
		 		 returnList = (HashMap<Integer, ReturnTransaction>) e.get(10);
		 		 returnId = (Integer) e.get(11);
		 		 accounting = (AccountBook) e.get(12);
		 		 orderList = (HashMap<Integer, Order>) e.get(13);
		 		 orderId = (Integer) e.get(14);
	         }
	         else {
	        	 userList = new LinkedHashMap<Integer, User>();
	 			productList = new LinkedHashMap<Integer, ProductType>();
	 			customerList = new HashMap<Integer, Customer>();
	 			orderList = new HashMap<Integer, Order>();
	 			loyaltyCardList = new HashMap<String, LoyaltyCard>();
	 			transactionList = new HashMap<Integer, SaleTransaction>();
	 			returnList = new HashMap<Integer, ReturnTransaction>();
	 			userId = 1;
	 			productId=1;
	 			customerId=1;
	 			orderId=1;
	 			loyaltyCardId =1;
	 			saleId = 1;
	 			returnId=1;
	         }
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	    	  System.out.println(i.getMessage());
	         
	         
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	        
	      }}
		return;
	}

	private boolean writeAppState() {
		//save data structure inside the list that will be stored in the file
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
	
	
	@SuppressWarnings("unchecked")
	private ArrayList<Long> readLoyalty() {;
		ArrayList<Long> cardsNo = null;
		try {
			 String fName = "./src/main/java/it/polito/cards.in/";
	         FileInputStream fileIn = new FileInputStream(fName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         cardsNo = (ArrayList<Long>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();} 
			catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cardsNo;
	}
	
	private boolean updateLoyalty(ArrayList<Long> cardsNo) {
		String fName = "./src/main/java/it/polito/cards.in/";
		try {
	         FileOutputStream fileOut = new FileOutputStream(fName);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(cardsNo);
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
		this.loggedUser = null;
    	
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
        userList.put(userId, user); //insert user inside the data structure
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
    	
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")))
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
        return true;
    }

    @Override
    public boolean updatePosition(Integer productId, String newPos) throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
        return false;
    }

    @Override
    public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(productCode == null || productCode.isEmpty()) 
    		throw new InvalidProductCodeException();
    	try{
			Integer.parseInt(productCode);
		}catch(NumberFormatException e) {
			throw new InvalidProductCodeException();
		}
    	if(quantity <= 0)
    		throw new InvalidQuantityException();
    	if(pricePerUnit <= 0)
    		throw new InvalidPricePerUnitException();
    	Order newOrder = new it.polito.ezshop.model.Order(productCode,quantity,pricePerUnit);
    	newOrder.setOrderId(this.orderId);
    	this.orderList.put(this.orderId,newOrder);
    	if( this.writeAppState() == false )
    		return -1;
    	
    	return this.orderId++;
    }

    @Override
    public Integer payOrderFor(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
    	
        return null;
    }

    @Override
    public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(orderId == null || orderId<=0) {
    		throw new InvalidOrderIdException();
    	}
    	Order order = this.orderList.get(orderId);
    	if(order == null || (order.getStatus()!="ISSUED" && order.getStatus()!="ORDERED"))
    		return false;
    	order.setStatus("PAYED");;
    	return true;
    }

    @Override
    public boolean recordOrderArrival(Integer orderId) throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
        return false;
    }

    @Override
    public List<Order> getAllOrders() throws UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	return (List<Order>) orderList.values();
    }

    @Override
    public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {
        if(customerName == null || customerName.length()==0) {
        	throw new InvalidCustomerNameException();
        }
        if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager") && !this.loggedUser.getRole().equals("Cashier"))) {
        	throw new UnauthorizedException();
        }
        it.polito.ezshop.model.Customer c = new it.polito.ezshop.model.Customer(customerName);
        c.setId(customerId);
        if(customerList.put(customerId, c) != null) {
        	return -1;
        }
        boolean res = writeAppState();
        if(res==false) {
        	return -1;
        }
    	return customerId++;
    }

    @Override
    public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard) throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(newCustomerName == null || newCustomerName.length()==0)
    		throw new InvalidCustomerNameException();
    	LoyaltyCard lc = this.loyaltyCardList.get(newCustomerCard);
    	if( lc != null || newCustomerCard.length()!=10 ) {
    		throw new InvalidCustomerCardException();
    	}
    	try {
    		Integer.parseInt(newCustomerCard);
    	}
    	catch(NumberFormatException e) {
    		throw new InvalidCustomerCardException();
    	}
    	if(id == null || id<=0) {
    		throw new InvalidCustomerIdException();
    	}
    	
    	Customer c = customerList.get(id);
    	if (c == null) {
    		return false;
    	}
    	
    	c.setCustomerName(newCustomerName);
    	if(newCustomerCard.length()==0) {
    		c.setCustomerCard(null);
    	}
    	else if(newCustomerCard != null) {
    		c.setCustomerCard(newCustomerCard);
    	}
    	boolean ret = writeAppState();
    	if (ret==false)
    		return false;
    	return true;
    }


	@Override
    public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
		if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
		if(id == null || id<=0)
			throw new InvalidCustomerIdException();
		
		Customer c = customerList.remove(id);
		if(c==null) {
			return false;
		}
		boolean ret = writeAppState();
    	if (ret==false)
    		return false;
        return true;
    }

    @Override
    public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(id == null || id<=0)
			throw new InvalidCustomerIdException();
    	Customer c = customerList.get(id);
    	return c;
    }

    @Override
    public List<Customer> getAllCustomers() throws UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
        return (List<Customer>) customerList.values();
    }

    @Override
    public String createCard() throws UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	LoyaltyCard lc = new LoyaltyCard();
    	ArrayList<Long> cards = readLoyalty();
    	if(cards == null) {
    		return "";
    	}
    	String code = cards.remove(0).toString();
    	if(!updateLoyalty(cards)) {
    		return "";
    	}
    	lc.setCode(code);
    	loyaltyCardList.put(code, lc);
    	boolean ret = writeAppState();
    	if (ret==false)
    		return "";
        return code;
    }

    @Override
    public boolean attachCardToCustomer(String customerCard, Integer customerId) throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(customerId==null || customerId<=0)
    		throw new InvalidCustomerIdException();
    	if(customerCard == null || customerCard.length()!=10 ) {
    		throw new InvalidCustomerCardException();
    	}
    	try {
    		Integer.parseInt(customerCard);
    	}
    	catch(NumberFormatException e) {
    		throw new InvalidCustomerCardException();
    	}
    	
    	Customer c = customerList.get(customerId);
    	if(c == null) {
    		return false;
    	}
    	LoyaltyCard lc = loyaltyCardList.get(customerCard);
    	
    	for(Customer cu : customerList.values()) {
    		if(cu.getCustomerCard().equals(customerCard)) {
    			return false; //someone alreay has it
    		}
    	}
    	
    	lc.setCustomer(c);   	
    	c.setCustomerCard(customerCard);
    	boolean ret = writeAppState();
    	if (ret==false) {
    		//rollback
    		lc.setCustomer(null);
    		c.setCustomerCard("");
    		return false;}
    	return false;
    }

    @Override
    public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded) throws InvalidCustomerCardException, UnauthorizedException {
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	//card validity
    	if(customerCard == null || customerCard.length()!=10 ) {
    		throw new InvalidCustomerCardException();
    	}
    	try {
    		Integer.parseInt(customerCard);//check is composed by numbers
    	}
    	catch(NumberFormatException e) {
    		throw new InvalidCustomerCardException();
    	}
    	//end check on card
    	
    	LoyaltyCard lc = loyaltyCardList.get(customerCard);
    	if(lc == null || (lc.getPoints() + pointsToBeAdded)<0) {
    		return false;
    	}
    	
    	Integer oldPoint =lc.getPoints();
    	lc.setPoints(oldPoint + pointsToBeAdded);
    	
    	if (! writeAppState()) {
    		lc.setPoints(oldPoint); //RollBack
    		return false;
    	}
    	return true;
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
    	if(this.loggedUser == null || (!this.loggedUser.getRole().equals("Cashier") && !this.loggedUser.getRole().equals("Administrator") && !this.loggedUser.getRole().equals("ShopManager")) )
    		throw new UnauthorizedException();
    	if(saleNumber == null || saleNumber <=0)
    		throw new InvalidTransactionIdException();
    	SaleTransaction s = transactionList.get(saleNumber);
    	if(s==null) {
    		return -1;
    	}
    	ReturnTransaction rt = new ReturnTransaction(returnId, s);
    	returnList.put(returnId, rt);
    	//TO DO add return trasaction to SaleTransaction list
    	
    	if (! writeAppState()) {
    		returnList.remove(returnId); //RollBack
    		return -1;
    	}
    	return returnId++;
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
    	if(this.loggedUser==null ||(! this.loggedUser.getRole().equals("Administrator") && ! this.loggedUser.getRole().equals("ShopManager"))) {
        	throw new UnauthorizedException();
        }
    	boolean res = accounting.isertBalanceOperation(toBeAdded);
    	
        return res;
    }

    @Override
    public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
    	if(this.loggedUser==null ||(! this.loggedUser.getRole().equals("Administrator") && ! this.loggedUser.getRole().equals("ShopManager"))) {
        	throw new UnauthorizedException();
        }
    	if (from != null && to != null && from.compareTo(to)>0) {
    		//invert dates if from is gretaer than to
    		LocalDate tmp = from;
    		from = to;
    		to = tmp;
    	}
    	List<BalanceOperation> result = new LinkedList<BalanceOperation>();
    	if(from == null) {
    		for (BalanceOperation bo: accounting.getOperationList() ) {
        		if(bo.getDate().compareTo(to) <= 0) {
        			result.add(bo);
        		}
        	}
    	}
    	else if(to == null) {
    		for (BalanceOperation bo: accounting.getOperationList() ) {
        		if(bo.getDate().compareTo(from) >= 0) {
        			result.add(bo);
        		}
        	}
    	}
    	else if(from == null || to == null) {
    		result = accounting.getOperationList();
    	}
    	else {
    		for (BalanceOperation bo: accounting.getOperationList() ) {
        		if( bo.getDate().compareTo(from) >= 0 && bo.getDate().compareTo(to) <= 0) {
        			result.add(bo);
        		}
        	}
    	}
    	return result;
    }

    @Override
    public double computeBalance() throws UnauthorizedException {
    	if(this.loggedUser==null ||(! this.loggedUser.getRole().equals("Administrator") && ! this.loggedUser.getRole().equals("ShopManager"))) {
        	throw new UnauthorizedException();
        }
        return accounting.getBalance();
    }
}
