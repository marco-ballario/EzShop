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
    + deleteSaleTicket(Integer ticketNumber): boolean
    + getSaleTicket(Integer transactionId): Ticket
    + getTicketByNumber(Integer ticketNumber): Ticket
    + startReturnTransaction(Integer ticketNumber): Integer
    + returnProduct(Integer returnId, String productCode, int amount): boolean
    + endReturnTransaction(Integer returnId, boolean commit): boolean
    + deleteReturnTransaction(Integer returnId): boolean
    + receiveCashPayment(Integer ticketNumber, double cash): double
    + receiveCreditCardPayment(Integer ticketNumber, String creditCard): boolean
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

<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-9wq8{border-color:inherit;text-align:center;vertical-align:middle}
.tg .tg-nrix{text-align:center;vertical-align:middle}
</style>
<table class="tg" style="width:60%;">
<thead>
  <tr>
    <th class="tg-9wq8"></th>
    <th class="tg-9wq8">User</th>
    <th class="tg-9wq8">EZShop</th>
    <th class="tg-9wq8">ProductType</th>
    <th class="tg-nrix">Position</th>
    <th class="tg-nrix">Order</th>
    <th class="tg-9wq8">Customer</th>
    <th class="tg-nrix">LoyaltyCard</th>
    <th class="tg-nrix">SaleTransaction</th>
    <th class="tg-nrix">ReturnTransaction</th>
    <th class="tg-nrix">FinantialTransaction</th>
    <th class="tg-nrix">AccountBook</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-9wq8">FR1</td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-9wq8">FR2</td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-9wq8">FR3</td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-9wq8"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix">FR4</td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix">FR5</td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix">FR6</td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
  <tr>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
    <td class="tg-nrix"></td>
  </tr>
</tbody>
</table>


# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

