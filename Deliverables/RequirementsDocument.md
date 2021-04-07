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
|   Customer     | Person that goes into the shop in order to buy items| 
|   Cashier     | Worker of the shop that  uses the cash register | 
|   Warehouse worker     | Worker of the shop that manage the warehouse and the products stored in it  |
|   Shop assistant     |      Person in charge of helping the customers during the purchase       |
|   Manager     |     Person managing the shop      |
|   IT administrator     |       A single person in charge of dealing with IT of the shop    |
|   Credit Card System     |  Deals with the payment via credit cards  |
|   Cash register | It stores the money and it's used to scan the products  |
|   Product | Item sold by the shop. Scanned by the cashier  |


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

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     |   |  | |
|  NFR2     | |  | |
|  NFR3     | | | |
| NFRx .. | | | | 


# Use case diagram and use cases


## Use case diagram
\<define here UML Use case diagram UCD summarizing all use cases, and their relationships>


\<next describe here each use case in the UCD>
### Use case 1, UC1
| Actors Involved        |  |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the UC can start> |  
|  Post condition     | \<Boolean expression, must evaluate to true after UC is finished> |
|  Nominal Scenario     | \<Textual description of actions executed by the UC> |
|  Variants     | \<other executions, ex in case of errors> |

##### Scenario 1.1 

\<describe here scenarios instances of UC1>

\<a scenario is a sequence of steps that corresponds to a particular execution of one use case>

\<a scenario is a more formal description of a story>

\<only relevant scenarios should be described>

| Scenario 1.1 | |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the scenario can start> |
|  Post condition     | \<Boolean expression, must evaluate to true after scenario is finished> |
| Step#        | Description  |
|  1     |  |  
|  2     |  |
|  ...     |  |

##### Scenario 1.2

##### Scenario 1.x

### Use case 2, UC2
..

### Use case x, UCx
..



# Glossary

\<use UML class diagram to define important terms, or concepts in the domain of the system, and their relationships> 

\<concepts are used consistently all over the document, ex in use cases, requirements etc>

# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram 

\<describe here deployment diagram >

