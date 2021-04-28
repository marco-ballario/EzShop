# Design Document 


Authors: Marco Ballario, Pietro Macori, Cosimo Michelagnoli, Lucia Vencato

Date: 30/04/2021

Version: 1.0


# Contents

- [High level design](#package-diagram)
- [Low level design](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document, notably functional and non functional requirements

# High level design 

<discuss architectural styles used, if any>
<report package diagram>






# Low level design

<for each package, report class diagram>

```plantuml
interface EZShopInterface{
    + reset(): void
    + createUser(String username, String password, String role): Integer 
    + deleteUser(Integer id): boolean 
	+ getAllUsers(): List<User>
	+ getUser(Integer id): User 
    + updateUserRights(Integer id, String role): boolean 
    + login(String username, String password): User 
    + logout(): boolean 
    + createProductType(String description, String productCode, double pricePerUnit, String note): Integer 
    + updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote): boolean 
	+ deleteProductType(Integer id): boolean 
    + getAllProductTypes(): List<ProductType> 
    + getProductTypeByBarCode(String barCode): ProductType
    + getProductTypesByDescription(String description): List<ProductType>
    + updateQuantity(Integer productId, int toBeAdded): boolean
    + updatePosition(Integer productId, String newPos): boolean
    + payOrderFor(String productCode, int quantity, double pricePerUnit): Integer
    + payOrder(Integer orderId): boolean
    + recordOrderArrival(Integer orderId): boolean
    + getAllOrders(): List<Order>
    + defineCustomer(String customerName): Integer
    + modifyCustomer(Integer id, String newCustomerName, String newCustomerCard): boolean
    + deleteCustomer(Integer id): boolean
    + getCustomer(Integer id): Customer
    + getAllCustomers(): List<Customer>
    + createCard(): String
    + attachCardToCustomer(String customerCard, Integer customerId): boolean
    + modifyPointsOnCard(String customerCard, int pointsToBeAdded): boolean
    + startSaleTransaction(): Integer
    + addProductToSale(Integer transactionId, String productCode, int amount): boolean
    + deleteProductFromSale(Integer transactionId, String productCode, int amount): boolean
    + applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate): boolean
    + applyDiscountRateToSale(Integer transactionId, double discountRate): boolean
    + computePointsForSale(Integer transactionId): int
    + closeSaleTransaction(Integer transactionId): boolean
    + deleteSaleTransaction(Integer transactionId): boolean
    + getSaleTransaction((Integer transactionId): SaleTransaction
    + startReturnTransaction(Integer transactionID): Integer
    + returnProduct(Integer returnId, String productCode, int amount): boolean
    + endReturnTransaction(Integer returnId, boolean commit): boolean
    + deleteReturnTransaction(Integer returnId): boolean
    + receiveCashPayment(Integer transactionID, double cash): double
    + receiveCreditCardPayment(Integer transactionID, String creditCard): boolean
    + returnCashPayment(Integer returnId): double
    + returnCreditCardPayment(Integer returnId, String creditCard): double
    + recordBalanceUpdate(double toBeAdded): boolean
    + getCreditsAndDebits(LocalDate from, LocalDate to): List<BalanceOperation>
    + computeBalance(): double
}

class EZshop implements EZShopInterface{
    -user_list: ArrayList<User>
    -product_list: ArrayList<ProductType>
    -order_list: ArrayList<Order>
    -customer_list: ArrayList<Customer>
}
class User{
    -id: int
    -username: String
    -password: String
    -role: String
    
}
class Cashier extends User{
}

class ShopManager extends User{
}

class Administrator extends User{
}

class ProductType{
	-id: integer
	-description: String
	-productCode: String
	-pricePerUnit: double
	-note: String
	-barCode: String
	-quantity: Integer
}
class Product{
}
class Position {
    -aisleID: Integer
    -rackID: Integer
    -levelID: Integer
}
 class Order{
	-id: integer
}
class Customer{
	-id: Integer
	-newCustomerName: String
	-newCustomerCard: LoyalityCard
}
class LoyalityCard{
    -id: Integer
    -points: Integer
}
class SaleTransaction{
    -id: Integer
    -date: LocalDate
    -time: LocalDateTime
    -cost: Float 
    -paymentType: String
    -discount: String
    -ticketlist: ArrayList<Ticket>
}
class Ticket{}

EZshop -- User
EZshop -- ProductType
EZshop -- Order
EZshop -- Customer
Customer "0..1"-- LoyalityCard
SaleTransaction --"*" ProductType
Position "0..1"-- ProductType
Product "*"-- ProductType :describes
SaleTransaction "*" --"0..1" LoyalityCard
Ticket "*" -- SaleTransaction
```

# Verification traceability matrix


<div stye="width: 100vw;">
<table>
<thead>
  <tr>
    <th></th>
    <th>User</th>
    <th>EZShop</th>
    <th>ProductType</th>
    <th>Position</th>
    <th>Order</th>
    <th>Customer</th>
    <th>LoyaltyCard</th>
    <th>SaleTransaction</th>
    <th>ReturnTransaction</th>
    <th>FinantialTransaction</th>
    <th>AccountBook</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>FR1</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>FR2</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>FR3</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>FR4</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>FR5</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>FR6</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
</tbody>
</table>
</div>


# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

