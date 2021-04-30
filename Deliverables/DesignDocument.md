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

The architectural style used is the MVC since we need a model containing data and methods to manipulate them, a view which allows the user to interact with the application and a controller responible to handle events from the user interface. In our specific case we have the model inside the "model" package, the view inside the "gui" package and the interface inside the "data" package. Moreover there is an "exceptions" packaged used to contains all possible exception classes making the model more clean.
Another possible solution would have been to exploit a 3 tier pattern. 


```plantuml
@startuml
package "it.polito.ezshop" {
  left to right direction
  package gui
  package model
  package data
  package exceptions
}

data .r.> exceptions
model ..> data
gui .l.> model
gui .r.> data
model ..> gui
@enduml
```


# Low level design

```plantuml
@startuml
package data{
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
}
@enduml
```
<br>

```plantuml
@startuml
interface EZShopInterface{}

package model{
class EZShop implements EZShopInterface{
-userList: LinkedHashMap<Integer User>
-loggedUser: User
-productList: LinkedHashMap <Integer ProductType>
-orderList: HashMap<Integer Order>
-customerList: HashMap<Integer Customer>
-cardList: HashMap<String Card>
-TransactionList: HashMap<Integer SaleTransaction>
-ReturnList: HashMap<Integer ReturnTransaction>
-accounting: AccountBook
-customerId: Integer
-productId: Integer
-orderId: Integer
-saleId: Integer
-returnId: Integer
-userId: Integer
-appState: List<WriteObject>
-getLoyaltyCard(Integer CardNo): LoyaltyCard
-getOrder(Integer orderId): Order
-saveAppState(List<WriteObject>): boolean
-readAppState(List<WriteObject>): boolean
}
class User{
    -userId: Integer
    -username: String
    -password: String
    -role: String
    -logged: boolean  
}

class ProductType{
-description: String
-barCode: String
-pricePerUnit: double
-note: String
-productId: Integer
-quantity: int
-position: Position
-increaseQnt(in int quantity): boolean
-decreaseQnt(in int quantity): boolean
}

class Order{
-product: ProductType
-quantity: int
-pricePerUnit: double
-orderId: Integer
-state: String
-payment: BalanceOperation
}

class Position{
-aisleId: Sting
-rackId: String
-levelId: String
}

class LoyaltyCard{
-code: String
-customer: Customer
-points: int
-addPoints()
-removePoints()
}

class Customer{
-name: String
-customerId: Integer
-card: LoyaltyCard
}

class AccountBook{
-balance: double
-operationList: LinkedList<BalanceOperation>
-createBalanceOperation(): BalanceOperation
}

class BalanceOperation{
-type: String
-amount: double
-date: LocalDate
}

class SaleTransaction{
-TransactionId: Integer
-products: HashMap<ProductType Integer>
-amount: double
-state: String
-payment: BalanceOperation
-paid: boolean
-returnTransactions: List<ReturnTransaction>
-transactionPoints: Integer
-applyDiscountSingle(in String productCode, in double discount): boolean
-applyDiscountAll(in double discount): boolean
-updateProductQuantity(in ProductType product, in Integer quantity): boolean
-checkCreditCard(in String cardno, in double amount): boolean
}

class ReturnTransaction{
-returnId: Integer
-returnProducts: HashMap<ProductType Integer>
-originalTransaction: SaleTransaction
-committed: boolean
-payment: BalanceOperation
-amount: double
-checkReturnProduct(in String productCode, in int amount): boolean
-updateOriginalTransaction(in SaleTransaction sale, in ProductType product, in Integer amount): boolean
checkCreditCard(in String cardno): boolean
}

EZShop -u->"*"User
EZShop -l->"*"Customer
EZShop -l->"*"LoyaltyCard
Customer -u- LoyaltyCard
EZShop --> AccountBook
AccountBook -->"*"BalanceOperation
EZShop --> "*"ReturnTransaction 
EZShop --> "*"SaleTransaction 
SaleTransaction --"*" ReturnTransaction
EZShop --> "*"Order
EZShop --> "*"ProductType
SaleTransaction  -->"*" ProductType
ReturnTransaction  -->"*" ProductType
Order --> ProductType
ProductType --> Position
Order --> BalanceOperation
SaleTransaction --> BalanceOperation
ReturnTransaction--> BalanceOperation



note "Persistency on this class is provided \nby methods saveAppState() and \nreadAppState() which serialize\na list of object that in our case \nare the list of user, customer, productType,\norders, sales, returns and \nthe Account Book class" as N1


note "Persistency assured by methods\nsaveAppState() and \nreadAppState() of EZShop class" as N2

EZShop .r. N1
AccountBook .u. N2
@enduml
```

# Verification traceability matrix

| |User|EZShop|ProductType|Position|Order|Customer|LoyaltyCard|SaleTransaction|ReturnTransaction|BalanceOperation|AccountBook|
|-|----|------|-----------|--------|-----|--------|-----------|---------------|-----------------|----------------|-----------|
|FR1|X|X||||||||||
|FR3||X|X|||||||||
|FR4.1||X|X|||||||||
|FR4.2||X|X|X||||||||
|FR4.3||X|X|||||||||
|FR4.4||X|||X|||||X|X|
|FR4.5||X|||X|||||X|X|
|FR4.6||X|X|X||||||||
|FR4.7||X||X||||||||
|FR5.1-FR5.4||X|||X|||||||
|FR5.5||X|||||X|||||
|FR5.6||X||||X|X|||||
|FR5.7||X|||||X|||||
|FR6.1-FR6.11||X||||||X||||
|FR6.12||X|||||||X|||
|FR6.13||X|X||||||X|||
|FR6.14||X|||||||X|||
|FR7.1-FR7.2||X||||||X||X|X|
|FR7.3-FR7.4||X|||||||X|X|X|
|FR8||X||||||||X|X|

# Verification sequence diagrams 

```plantuml
title Scenario 1.3
actor "ShopManager or\nAdministrator" as act
act -> ":EZShop" : getProductTypeByBarCode()
":EZShop" --> act : ProductType
act -> ":EZShop" : new pricePerUnit
":EZShop" -> ":ProductType": updateProduct()
":ProductType" --> ":EZShop" : updated ProductType
```

```plantuml
title Scenario 2.3
actor Administrator
Administrator-> ":EZShop" : getUser()
":EZShop" --> Administrator: User
Administrator -> ":EZShop" : new role
":EZShop" -> ":User": updateUserRights()
":User"--> ":EZShop" : updated User
```

```plantuml
title Scenario 3.2
":EZShop" -> ":EZShop": getOrder()
":EZShop" -> ":EZShop": getAccountBook()
":EZShop" -> ":AccountBook": createBalanceOperation()
":AccountBook" -> ":EZShop": BalanceOperation
":EZShop" -> ":Order": setPayment()
":Order" --> ":EZShop"
```

```plantuml
title Scenario 3.3
":EZShop" -> ":EZShop": getOrder()
":EZShop" -> ":Order": getProductType()
":Order" --> ":EZShop": productType
":EZShop" -> ":ProductType": setPosition()
":EZShop" -> ":EZShop": recordOrderArrival()
":EZShop" -> ":Order": updateProductQuantity()
":Order" -> ":ProductType": increaseQnt()
":ProductType" --> ":Order": result
":Order" --> ":EZShop": result
":EZShop" -> ":Order": setState()
```

```plantuml
title Scenario 5.1
actor "Cashier or\n ShopManager or\n Administrator" as act
act -> ":EZShop" : insert username
act -> ":EZShop" : insert password
":EZShop" -> ":User" : login()
":User" --> ":EZShop" : User
```

```plantuml
title Scenario 6.4
":EZShop" -> ":EZShop" : startSaleTransaction()
":EZShop" -> ":EZShop" : addProductToSale()
":EZShop" -> ":ProductType" : getProductTypeByBarCode()
":ProductType" -> ":ProductType" : decreseQnt()
":ProductType" --> ":EZShop" : success
":EZShop" -> ":EZShop" : endSaleTransaction()
":EZShop" -> ":EZShop" : attachCardToCustomer()
note left
start scenario 7.1 
Manage payment by 
valid credit card
end note
":EZShop" -> ":SaleTransaction" : receiveCreditCardPayment()
":SaleTransaction" -> ":SaleTransaction" : checkCreditCard()
":SaleTransaction" --> ":EZShop" : success
note left: end scenario 7.1
":EZShop" -> ":EZShop" : computePointsForSale()
":EZShop" -> ":EZShop" : modifyPointsOnCard()
":EZShop" -> ":AccountBook" : recordBalanceUpdate()
":AccountBook" -> ":AccountBook" : createBalanceOperation()
":AccountBook" --> ":EZShop" : success
```

```plantuml
Title: Scenario 8.1 and Scenario 10.1
":EZShop" -> ":ReturnTransaction" : startReturnTransaction()
":EZShop" -> ":ReturnTransaction" : returnProduct() 
":EZShop" -> ":ReturnTransaction" : updateProductQuantity()
":ReturnTransaction" -> ":ProductType" : increaseQuantity()
":ReturnTransaction" -> ":SaleTransaction" : updateOriginalTransaction()
":EZShop" -> ":ReturnTransaction" : returnCreditCardPayment()
note left
start scenario 10.1 
manage return payment 
by creditCard
end note
":ReturnTransaction" -> ":ReturnTransaction" : checkCreditCard()
":ReturnTransaction" --> ":EZShop" : success
note left: end scenario 10.1
":EZShop" -> ":AccountBook" : recordBalanceUpdate()
":AccountBook" -> ":AccountBook" : createBalanceOperation()
":AccountBook" --> ":EZShop" : success
":EZShop" -> ":ReturnTransaction" : endReturnTransaction()
":ReturnTransaction" --> ":EZShop" : success
```
