# Unit Testing Documentation

Authors: Marco Ballario, Pietro Macori, Cosimo Michelagnoli, Lucia Vencato

Date: 14/05/2021

Version: 1.0

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezshop   You find here, and you can use,  class TestEzShops.java that is executed  
    to start tests
    >

 ### **Class *Tools* - method *checkDigit***

**Criteria for method *checkDigit*:**
 - String length
 - Digits
 - Correct code
 - Value

**Predicates for method *checkDigit*:**
| Criteria | Predicate |
| -------- | --------- |
|    String lenght      |     >14      |
|          |     <12      |
|          |      =<14 && >=12     |
|    Digits      |     String composed by digits       |
|          |     String not composed by digits       |
|    Value      |     Positive      |
|          |     Negative     |
|    Correct code      |     Valid code      |
|          |     Wrong code     |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
|    String length      |          14       |
|          |       12          |
|     Digits     |         Positive        |
|     Value     |         0        |
|               |         MAXLONG          |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | Criteria 3 | Criteria 4 | Valid / Invalid | Description of the test case | JUnit test case |
|------------|------------|------------|------------|---------------- |------------------------------|-----------------|
| >14 | * | * | * | Valid | Input string is longer than 14 char<br/>T1("123456789012345"; false) | testMore14() |
| <12 | * | * | * | Valid | Input stirng is smaller than 12 char<br/>T2("123456789"; false) | testLess12() |
| *| Alphabetic string|*|*| Valid|Input string with alphabet char<br/>T3("123ABCDEFT322"; InvalidProductCodeException);|testAlphabetInput()|
| * | * | Negative |*|Invalid| Input string composed by negative number<br/>T4("-12345678901234";false);|testNegative()|
| *| *| * | Invalid Code | Valid| Input string doesn't satisfy the algorith<br/>T5("12345678901232"; false) |testInvalidCode()|
| 12 | Digits |Positive|Valid Code| Valid| Valid input with lenght 12<br/>T6("123456789012";true) |test12Digits()|
| 13 | Digits |Positive|Valid Code| Valid| Valid input with lenght 13<br/>T7("1234567890111";true) |test13Digits()|
| 14 | Digits |Positive|Valid Code| Valid| Valid input with lenght 14<br/>T8("12345678901231";true) |test14Digits()|

### **Class *Tools* - method *paymentCreditCards***

**Criteria for method *paymentCreditCards*:**
 - File existance
 - Credit card presence
 - Money

**Predicates for method *paymentCreditCards*:**
| Criteria | Predicate |
| -------- | --------- |
| File existance | File exists |
|  | File doesn't exist|
| Credit card presence | Card present |
| | Card not present|
| Money  | price > 0 && price > money avaiable|
| | price < 0 (return) |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
| Money| 0 |
||DBL_MAX|
|| price == money avaiable|

**Combination of predicates**:
| Criteria 1 | Criteria 2 | Criteria 3 | Valid / Invalid | Description of the test case | JUnit test case |
|------------|------------|------------|---------------- |------------------------------|-----------------|
| No file| * | * | Valid | The given file doesn't exist<br/>T1("4485370086510891", 12.2, "./wrong"; false) | testNoFile() |
| * | Card not present | * | Valid | The input credit card is not present in the file<br/>T2("0085370086510891", 21.2, ".../utils/creditcards.txt"; false)| testNoCard() |
| * | * | price < avaiable && price>0 | Valid | The money to be payed are not enough on the card<br/>T3("485370086510891", 1200.0,".../utils/creditcards.txt") | testNoMoney() |
| File present | Card present | price > 0 && price < avaiable |Valid | The money to be payed are enough<br/>T4("485370086510891", 10.0,".../utils/creditcards.txt") | testMoneyAvaiable() |
| File present | Card present | price > 0 && price == avaiable |Valid | The money to be payed are equal to avaiable<br/>T5("485370086510891", 150.0,".../utils/creditcards.txt") | testMoneyEqual() |
| File present | Card present | price < 0  |Valid | The money are returned<br/>T6("485370086510891", 10.0,".../utils/creditcards.txt") | testReturnMoney() |
| File present | Card present | price == 0  |Valid | Zero money are returned<br/>T7("485370086510891", 0.0,".../utils/creditcards.txt") | testZeroMoney() |
| File present | Card present | price == DBL_MAX  |Valid | Max money are returned<br/>T8("485370086510891", DBL_MAX,".../utils/creditcards.txt") | testMaxMoney() |

### **Class *SaleTransaction* - method *addProduct***

**Criteria for method *addProduct*:**
 - Product existance
 - Barcode presence
 - Amount

**Predicates for method *addProduct*:**
| Criteria | Predicate |
| -------- | --------- |
| Product existance | Product exists |
| | Product doesn't exist|
| Barcode presence | Barcode present |
| | Barcode not present|
| Amount  | Amount >= 0 and<br/>Amount <= INT_MAX and<br/>(Amount + Current quantity) <= INT_MAX |
| | Amount < 0 or<br/>Amount > INT_MAX or<br/>(Amount + Current quantity) > INT_MAX |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
| Amount | 0 |
| | INT_MAX |
| | INT_MAX - Current quantity |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| Product doesn't exist | * | * | Invalid | The given product doesn't exist<br/>T1(null, 5; false) | testNoProduct() |
| * | Barcode not present | * | Invalid | The input barcode card is not present in the list of products<br/>T2(product with missing barcode, 5; false)| testNoBarcode() |
| * | * | Amount < 0 or<br/>Amount > INT_MAX or<br/>(Amount + Current quantity) > INT_MAX | Invalid | The amount provided is not summable<br/>T3a(product, -5; false)<br/>T3b(product, INT_MAX+1; false)<br/>T3c(product, INT_MAX - Current quantity + 1; false) | testNoAmount() |
| Product exists | Barcode present | Amount >= 0 and<br/>Amount <= INT_MAX and<br/>(Amount + Current quantity) <= INT_MAX | Valid | Valid amount to be added<br/>T4(product, Amount; true) | testValidAmount() |
| Product exists | Barcode present | Amount = 0 | Valid | No amount to be added<br/>T5(product, 0; true) | testZeroAmount() |
| Product exists | Barcode present | Amount = INT_MAX and<br/>Current quantity = 0 | Valid | Valid amount to be added<br/>T6(product, INT_MAX; true) | testGlobalMaxAmount() |
| Product exists | Barcode present | Amount = (INT_MAX - Current quantity) | Valid | Valid amount to be added<br/>T7(product, INT_MAX - Current quantity; true) | testRelativeMaxAmount() |


### **Class *SaleTransaction* - method *updateStatusPlus***

**Criteria for method *name*:**
 - ReturnId sign
 - ReturnId exists



**Predicates for method *name*:**
| Criteria | Predicate |
| -------- | --------- |
|     ReturnId sign     |      Positive     |
|          |    Negative       |
|     ReturnId exists     |     Yes      |
|          |     No      |


**Combination of predicates**:
| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|------------|------------|-----------------|------------------------------|-----------------|
|Negative| * |Invalid|The return id is negative integer\nT1(-10;false)|testNegativeReturnId()|
|*|id doesn't exit|Valid| The input return id is not present\nT2(123, false)|testNonExistReturnId()|
|Positive|id exists|Valid|The input is valid\nT3(1,true)|testCorrectReturnId()|


### **Class *class_name* - method *name***

**Criteria for method *name*:**
 - 
 - 

**Predicates for method *name*:**
| Criteria | Predicate |
| -------- | --------- |
|          |           |
|          |           |
|          |           |
|          |           |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
|          |                 |
|          |                 |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|||||||
|||||||
|||||||
|||||||
|||||||

# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezshop>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||



