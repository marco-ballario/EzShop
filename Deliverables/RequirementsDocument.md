# Requirements Document 

Authors:

Date:

Version:

# Contents

- [Essential description](#essential-description)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
    	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)
- [Deployment diagram](#deployment-diagram)

# Essential description

Small shops require a simple application to support the owner or manager. A small shop (ex a food shop) occupies 50-200 square meters, sells 500-2000 different item types, has one or a few cash registers 
EZShop is a software application to:
* manage sales
* manage inventory
* manage customers
* support accounting

# Stakeholders

| Stakeholder name  | Description | 
| ----------------- |:-----------:|
|	Customer     		| Person that goes into the shop in order to buy items | 
|   Cashier     		| Shop employees in charge of using the cash register | 
|   Warehouse employee  | Shop employee that manage the warehouse and the products stored in it  |
|   Shop assistant     	| Person in charge of helping the customers during the purchase |
|   Manager     		| Person managing the shop |
|   IT administrator    | A single person in charge of dealing with IT of the shop |
|   Credit Card System  | Deals with the payment via credit cards |
|   Cash register 		| It stores the money and it's used to scan the products |
|   Product 			| Item sold by the shop. Scanned by the cashier |
|   Loyalty card 		| Card assigned to a single registered customer. Used to accumulate points and get prizes |

# Context Diagram and interfaces

## Context Diagram
\<Define here Context diagram using UML use case diagram>

\<actors are a subset of stakeholders>

```plantuml
left to right direction
actor "Cashier" as C
actor "Product" as P
actor "Credit Card System" as CCS
actor "Manager" as M
actor "Shop assistant" as SA
actor "Warehouse employee" as WE
actor "Customer" as CU
rectangle System{
	(Application) as SW
	C -- SW
	P -- SW
	CCS -- SW
	CU -- SW
	SW -- WE
	SW -- M
	SW -- SA
}
```

## Interfaces
\<describe here each interface in the context diagram>

\<GUIs will be described graphically in a separate document>

| Actor | Physical Interface | Logical Interface  |
| ------------- |:-------------:| -----:|
|   Cashier    | Screen, keyboard |GUI|
|   Product	   | Laser beam		  |Bar code |
|	Credit card system | Internet connection |Web services|
| 	Shop assistant	| Tablet	|GUI|
|	Warehouse worker| Screen, keyboard |GUI	|
|	Manager| Screen, keyboard |GUI	|
|Customer | Mobile|GUI|

# Stories and personas
\<A Persona is a realistic impersonation of an actor. Define here a few personas and describe in plain text how a persona interacts with the system>

\<Persona is-an-instance-of actor>

\<stories will be formalized later as scenarios in use cases>

# Functional and non functional requirements

## Functional Requirements

\<In the form DO SOMETHING, or VERB NOUN, describe high level capabilities of the system>

\<they match to high level use cases>

| ID        | Description  |
| ------------- |:-------------:| 
|  FR1     | Manage sales |
|  FR11     | Create a transaction |
|  FR12     | Modify a transaction |
|  FR13     | Delete a transaction |
|  FR14     | Insert a product in a transaction |
|  FR15     | Add points to customer card |
|  FR2     | Manage inventory  |
| FR21  | Add product | 
| FR22  | Remove product | 
| FR23  | Modify quantity |
| FR24  | Add delivery informations (date, time, supplier, products) | 
| FR3  | Manage customers |
| FR31  | Add customer |
| FR32  | Delete customer |
| FR32  | Modify customer informations |
| FR33  | Summary of customer informations (points, prizes, ...) |
| FR4  | Support accounting |
| FR41  | Show the product type and the amount of sold items in a period of time  |
| FR42  | Summary of expenses and incoming in a period of time |
| FR43  | Show workers informations |

## Non Functional Requirements

\<Describe constraints on functional requirements>

| ID | Type | Description | Refers to |
|-------------|-------------|-----|-----|
| NFR1 | Usability | Installation time < 5 minutes | - |
| NFR2 | Usability | Application learning time < 10 minutes | - |
| NFR3 | Usability | Average application rating from users point of view after a week of usage >= 4/5 stars | - |
| NFR4 | Usability | Average GUI attractiveness rating from users point of view >= 4/5 stars | - |
| NFR5 | Usability | Number of functions to be added after a week of usage = 0 | - |
| NFR6 | Efficiency | Response time to add a new transaction < 0.1 s | FR11 |
| NFR7 | Efficiency | Response time to add a new product < 0.1 s | FR21 |
| NFR8 | Efficiency | Response time to add a new customer < 0.1 s | FR31 |
| NFR9 | Efficiency | Response time to show the product type and the amount of sold items in a period of time < 0.1 s | FR41 |
| NFR10 | Efficiency | Response time to show the summary of expenses and incoming in a period of time < 0.1 s | FR42 |
| NFR11 | Reliability | Database backup frequency >= 1 backup/week | - |
| NFR12 | Reliability | Availability >= 99% of every day of every week | - |
| NFR13 | Reliability | Overall number of errors per month < 5 | - |
| NFR14 | Reliability | Overall number of application crash per year <= 1 | - |
| NFR15 | Maintainability | Effort to restore a database backup <= 1 man-hour | - |
| NFR16 | Maintainability | Effort to add a new software function <= 2 man-weeks | - |
| NFR17 | Maintainability | Effort to test the correct behaviour of the application <= 1 man-hour | - |		
| NFR18 | Portability | Effort to move the application from an old machine to a new one with the same OS <= 2 man-hours | - |
| NFR19 | Portability | Effort to move the application from an old machine to a new one with a different OS <= 3 man-hours | - |
| NFR20 | Portability | Effort to change the DBMS <= 3 man-hours	| - |

# Use case diagram and use cases

## Use case diagram
\<define here UML Use case diagram UCD summarizing all use cases, and their relationships>

\<next describe here each use case in the UCD>
### Use case 1, UC1 - Registration new customer
| Actors Involved        | Shop assistant, cashier ,customer |
| ------------- |:-------------:| 
|  Precondition     | The customer requests a loyalty card |  
|  Post condition     | The customer has an account, owns an active loyalty card |
|  Nominal Scenario     | The customer requests to the cashier to have a card, the cashier makes her fill and sign a document which is given to an avaiable shop assistant. The shop assistant creates a new user into the system and activates a card. The card is hand to the customer|
|  Variants     | The manager can do the shop assistant job if needed |

##### Scenario 1.1 

\<describe here scenarios instances of UC1>

\<a scenario is a sequence of steps that corresponds to a particular execution of one use case>

\<a scenario is a more formal description of a story>

\<only relevant scenarios should be described>

| Scenario 1.1 | |
| ------------- |:-------------:| 
|  Precondition     | Customer not already registered or registered but the card has expired |
|  Post condition     | Customer registered, owns an activated card |
| Step#        | Description  |
|  1     | Customer requests a card to the cashier |  
|  2     | Cashier hands a document to be filled and signed by the customer |
|  3     | Document delivered to the shop assistant |
|  4     | Shop assistant enters data inside the application  |
|  5     | Account correctly created |
|  6     | Card activated |
|  7     | Card number inserted in the customer account |
|  8     | Card and a copy of the document handed to the customer |

##### Scenario 1.2

| Scenario 1.2 | |
| ------------- |:-------------:| 
|  Precondition     | Customer already registered and card is still valid |
|  Post condition     | Customer registered, owns an activated card |
| Step#        | Description  |
|  1     | Customer requests a card to the cashier |  
|  2     | Cashier hands a document to be filled and signed by the customer |
|  3     | Document delivered to the shop assistant |
|  4     | Shop assistant enters data inside the application  |
|  5     | Application returns an error |
|  6     | Customer informed that she already owns a valid card |

##### Scenario 1.3 
| Scenario 1.3 | |
| ------------- |:-------------:| 
|  Precondition     | Customer already registered but she lost her card |
|  Post condition     | Customer owns a new activated card, old card is disabled |
| Step#        | Description  |
|  1     | Customer reports to the cashier that she lost her loyalty card  |  
|  2     | Cashier hands a dedicated document to be filled and signed by the customer |
|  3     | Document delivered to the shop assistant |
|  4     | Shop assistant disable the old card and activates a new one |
|  5     | Customer account is updated with the new card number|
|  6     | New card handed to the customer |

### Use case 2, UC2 - Customer concludes the shopping 
| Actors Involved        | Cashier ,customer, product |
| ------------- |:-------------:| 
|  Precondition     | All products scanned by the cashier, customer pays, loyalty card scanned before payment  |  
|  Post condition     | Points are added to customer account, products quantity are updated |
|  Nominal Scenario     | The cashier scans all items and the loyalty card, the customer pays the due amount. The points are added to customer account and the bought items are removed from the inventory |

##### Scenario 2.1
| Scenario 2.1 | |
| ------------- |:-------------:| 
|  Precondition     | Card is valid |
|  Post condition     | Points are added to the account  |
| Step#        | Description  |
|  1     | Customer hands the fidelity card to the cashier  |  
|  2     | Cashier scans the card |
|  3     | Payment is performed (both cash and credit card are fine) |
|  4     | Points are added to the customer account |
|  5     | Products quantities are updated |

##### Scenario 2.2
| Scenario 2.2 | |
| ------------- |:-------------:| 
|  Precondition     | Card is expired |
|  Post condition     | No points are added to the account  |
| Step#        | Description  |
|  1     | Customer hands the fidelity card to the cashier  |  
|  2     | Cashier scans the card |
|  3     | The cash register makes an error sound to inform the cashier and the customer |
|  4     | Payment is performed (both cash and credit card are fine) |
|  5     | Products quantities are updated |

### Use case 3, UC3 - Verification of deadlines by the shop assistant
| Actors Involved        | Shop assistant, product |
| ------------- |:-------------:| 
| Precondition	| Shop assistant S exists and has valid account |
|				| Product P exists and is close to its expiration date   |
| Post condition| The application is notified|
| Step#        | Description  |
|1		| Shop assistant scans the bar code of a product |
|2		| The expiry date is checked	|
##### Scenario 3.1
| Scenario 3.1 | Price is wrong |
| ------------- |:-------------:| 
|  Precondition     | Shop assistant S exists and has valid account |
|       			| Product P exists and is close to its expiration date  |
| Post condition 	|  A discount is applied	|
| Step#        | Description  |
|  1		| Shop assistant scans the bar code of a product |
|  2		| The expiry date is checked	|
|  3        | Product's price is updated |

### Use case 4, UC4 - Data check by manager
| Actors Involved        | Manager, product, costumer |
| ------------- |:-------------:| 
|  Precondition     | Manager logs in the application and checks data  |  
|  Post condition     | Data are modified |
|  Nominal Scenario     | Manager checks the data to monitor the sales |

##### Scenario 4.1
| Scenario 4.1 | |
| ------------- |:-------------:| 
|  Precondition     | Manager checks the customer points count |
|  Post condition     | Customer obtains a discount |
| Step#        | Description  |
|  1     | Manager views customers   |  
|  2     | Manager checks all customers whose points are higher than a prefixed threshold |
|  3     | Manager gives a discount to use in the shop to those customers |
|  4     | Customer points are resetted |

##### Scenario 4.2
| Scenario 4.2 | |
| ------------- |:-------------:| 
|  Precondition     | Manager checks the less purchased products of the week |
|  Post condition     | Product price is updated  |
| Step#        | Description  |
|  1     | Manager checks the inventory |  
|  2     | Manager checks the less purchased product |
|  3     | Manager applies a discount to that product |

##### Scenario 4.3
| Scenario 4.3 | |
| ------------- |:-------------:| 
|  Precondition     | On Friday manager controls the sales of the week |
|  Post condition     | A list of products to be bought is made  |
| Step#        | Description  |
|  1     | Manager checks the inventory |  
|  2     | Manager checks the products with the quantity lower than a threshold |
|  3     | Manager contacts the supplier to have more quantity of those products |

### Use case 5, UC5 - Warehouse worker updates products quantity
| Actors Involved        | Warehouse worker, Product |
| ------------- |:-------------:| 
|  Precondition     | Warehouse worker controls the products in the warehouse  |  
|  Post condition     | Products quantity is updated in the application inventory |
|  Nominal Scenario     | Warehouse worker updates the products quantity in the application if he notices that it’s wrong or when the supplier truck arrives |

##### Scenario 5.1
| Scenario 5.1 | |
| ------------- |:-------------:| 
|  Precondition     | The quantity of products actually presents in the warehouse and the one in the inventory are not the same |
|  Post condition     | Products quantity in the inventory is corrected |
| Step#        | Description  |
|  1     | Warehouse worker notices that the quantity of a product in the inventory is different from the one available in the warehouse   |  
|  2     | Warehouse worker modifies the quantity of that product in the application |

##### Scenario 5.2
| Scenario 5.2 | |
| ------------- |:-------------:| 
|  Precondition     | Supplier truck arrives with the products |
|  Post condition     | Products quantity in the inventory is updated |
| Step#        | Description  |
|  1     | Products are unloaded from the truck  |  
|  2     | Warehouse worker checks the quantity |
|  3     | Warehouse worker updates the quantity of the products in the application inventory |

### Use case 6, UC6 - Wrong password
| Actors Involved        | Warehouse worker, Shop assistant, Cashier, Warehouse worker, Customer |
| ------------- |:-------------:| 
|  Precondition     | Wrong credential for the log in  |  
|  Post condition     | Password is changed |
|  Nominal Scenario     | If the user forgot the password to enter his account he will receive an email to change it |

# Glossary

\<use UML class diagram to define important terms, or concepts in the domain of the system, and their relationships> 

\<concepts are used consistently all over the document, ex in use cases, requirements etc>

# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram 

\<describe here deployment diagram >

