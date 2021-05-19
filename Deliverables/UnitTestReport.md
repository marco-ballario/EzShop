# Unit Testing Documentation

Authors: Marco Ballario, Pietro Macori, Cosimo Michelagnoli, Lucia Vencato

Date: 14/05/2021

Version: 1.0

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)
    + [Class Tools - method checkDigit](#class-tools-method-checkdigit)
    + [Class Tools - method checkCardLuhn](#class-tools-method-checkcardluhn)
    + [Class Tools - method paymentCreditCards](#class-tools-method-paymentcreditcards)
    + [Class SaleTransaction - method removeProducts](#class-saletransaction-method-removeproducts)
    + [Class SaleTransaction - method updateStatusMin](#class-saletransaction-method-updatestatusmin)
    + [Class SaleTransaction - method updateStatusPlus](#class-saletransaction-method-updatestatusplus)
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

 ### **Class *Tools* - method *checkCardLuhn***

**Criteria for method *checkCardLuhn*:**
 - String length
 - Digits
 - Correct code
 - Value

**Predicates for method *checkCardLuhn*:**
| Criteria | Predicate |
| -------- | --------- |
|    String lenght      |     !=16      |
|          |     ==16      |
|    Digits      |     String composed by digits       |
|          |     String not composed by digits       |
|    Value      |     Positive      |
|          |     Negative     |
|    Correct code      |     Valid code      |
|          |     Wrong code     |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
|    String length      |          16       |
|     Digits     |         Positive        |
|     Value     |         0        |
|               |         MAXLONG          |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | Criteria 3 | Criteria 4 | Valid / Invalid | Description of the test case | JUnit test case |
|------------|------------|------------|------------|---------------- |------------------------------|-----------------|
| !=16 | * | * | * | Valid | Input string is not equal to 16 char<br/>T1("44853700865108919"; false) | testSizeNot16() |
| *| Alphabetic string|*|*| Valid|Input string with alphabet char<br/>T3("44853A0086B10891"; false)|testAlphabetInputCard()|
| * | * | Negative |*|Invalid| Input string composed by negative number<br/>T4("-4485370086510891";false);|testNegativeCard()|
| *| *| * | Invalid Code | Valid| Input string doesn't satisfy the algorith<br/>T5("1485370086510891"; false) |testInvalidCard()|
| 16 | Digits |Positive|Valid Code| Valid| Valid input with lenght 16<br/>T6("4485370086510891";true) |test16Digits()|

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

### **Class *SaleTransaction* - method *removeProducts***

**Criteria for method *removeProducts*:**
 - Barcode presence
 - Amount

**Predicates for method *removeProducts*:**
| Criteria | Predicate |
| -------- | --------- |
| Barcode presence | Barcode present |
| | Barcode not present|
| Amount  | Amount >= 0 and<br/>Amount <= Current quantity |
| | Amount < 0 or<br/>Amount > Current quantity |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
| Amount | 0 |
| | Current quantity |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| Barcode not present | * | Invalid | The input barcode is not present in the list<br/>T1(missing barcode, 5; false)| testNoBarcode() |
| * | Amount < 0 or<br/>Amount > Current quantity | Invalid | The amount provided is not removable<br/>T2a(barcode, -5; false)<br/>T2b(barcode, current quantity +1; false) | testNoAmount() |
| Barcode present | Amount >= 0 and<br/>Amount <= Current quantity | Valid | Valid amount to be removed<br/>T3(barcode, 5; true) | testValidAmount() |
| Barcode present | Amount = 0 | Valid | No amount to be removed<br/>T4(barcode, 0; true) | testZeroAmount() |
| Barcode present | Amount = Current quantity | Valid | Valid amount to be removed<br/>T5(barcode, current quantity; true) | testCurrentQuantityAmount() |

### **Class *SaleTransaction* - method *updateStatusMin***

**Criteria for method *updateStatusMin*:**
 - Return ID sign
 - Return ID exists

**Predicates for method *updateStatusMin*:**
| Criteria | Predicate |
| -------- | --------- |
| Return ID sign | Positive |
| | Negative |
| Return ID presence | Return ID present |
| | Return ID not present |

**Boundaries**:
| Criteria | Boundary values |
| -------- | --------------- |
| Return ID sign | Return ID = 0 |
| | Return ID = INT_MAX |

**Combination of predicates**:
| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| Negative | * | Invalid | The Return ID is a negative integer<br/>T1(-10; false) | testReturnIdNegative() |
| * | Return ID not present | Invalid | The input Return ID is not present<br/>T2(123; false) | testReturnIdNotPresent() |
| Positive | Return ID present | Valid | The input Return ID is valid<br/>T3(1; true)| testReturnIdPresent() |
| Positive | Return ID present and<br/>Return ID = 0 | Valid | The input Return ID is valid<br/>T4(0; true)| testReturnIdPresentAndZero() |
| Positive | Return ID present and<br/>Return ID = INT_MAX | Valid | The input Return ID is valid<br/>T5(INT_MAX; true)| testReturnIdPresentAndMax() |

### **Class *SaleTransaction* - method *updateStatusPlus***

**Criteria for method *updateStatusPlus*:**
 - ReturnId sign
 - ReturnId exists



**Predicates for method *updateStatusPlus*:**
| Criteria | Predicate |
| -------- | --------- |
|     ReturnId sign     |      Positive     |
|          |    Negative       |
|     ReturnId exists     |     Yes      |
|          |     No      |


**Combination of predicates**:
| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|------------|------------|-----------------|------------------------------|-----------------|
|Negative| * |Invalid|The return id is negative integer<br/>T1(-10;false)|testNegativeReturnId()|
|*|id doesn't exit|Valid| The input return id is not present<br/>T2(123, false)|testNonExistReturnId()|
|Positive|id exists|Valid|The input is valid<br/>T3(1,true)|testCorrectReturnId()|

# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezshop>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
| AccountBook |testAccountConstructor() |
||testSetBalance()|
|TestLoyaltyCard|testSetCode()|
||testSetPoints|
|TestOrder|testOrderConstructor()|
||testSetPriceUnit()|
||testSetProductCode()|
||testSetStatus()|
||testSetQuantity()|
||testSetOrderId()|
|User|testUserConstructor()|
||testSetId()|
||testSetUsername()|
||testSetPassword()|
||testSetRole()|
| TestBalanceOperation | testSetBalanceId() |
|| testSetDate() |
|| testSetType()  |
|| testSetMoney()  |
| TestCustomer | testCustomerConstructor() |
|| testSetName() |
|| testSetId() |
|| testSetPoints() |
|| testSetCardNull() |
|| testSetCard() |
|ProductType|TestProductType|
||testProductConstructor()|
||testProductConstructor2()|
||testSetBarCode()|
||testSetQuantity()|
||testSetId()|
||testSetNote()|
||testIncreaseQuantity()|
||testDecreaseQuantity())|
||testSetPriceUnit())|
||testSetDescription()|
|ReturnTransaction|TestReturnTransaction|
||testReturnConstructor()|
||testSetReturnId()|
||testSetCommitted())|
||testSetPayment()|
||testSetAmount()|
|SaleTransaction|testSaleConstructor()|
|| testSetTicketNum() |
|| testSetDiscount() |
|| testSetPrice() |
|| testSetStatus |
|| testSetPoints() |
|| testSetTransactionId() |
|TicketEntry| testTicketEntryConstructor()|
||testSetBarCode()|
|| testSetPriceUnit() |
|| testSetDescription() |
|| testSetAmount() |
|| testSetDiscountRate() |
|| testSetPrice() |	

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



