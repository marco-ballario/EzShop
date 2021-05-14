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



**Criteria for method *name*:**
	

 - String length
 - Digits
 - Correct code
 - Value



**Predicates for method *name*:**

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
| >14 | * | * | * | Invalid | Input stirng is longer than 14 char\n T1("123456789012345"; false) | testMore14() |
| <12 | * | * | * | Invalid | Input stirng is smaller than 12 char\n T2("123456789"; false) | testLess12() |
| *| Alphabetic string|*|*| Invalid|Input string with alphabet char\n T3("123ABCDEFT322"; InvalidProductCodeException);|testAlphabetInput()|
| * | * | Negative |*|Invalid| Input string composed by negative number\n T4("-12345678901234";false);|testNegative()|
| *| *| * | Invalid Code | Invalid| Input string doesn't satisfy the algorith\n T5("12345678901232"; false) |testInvalidCode()|
| 12 | Digits |Positive|Valid Code| Valid| Valid input with lenght 12\n T6("123456789012";true) |test12Digits()|
| 13 | Digits |Positive|Valid Code| Valid| Valid input with lenght 13\n T7("1234567890111";true) |test13Digits()|
| 14 | Digits |Positive|Valid Code| Valid| Valid input with lenght 14\n T8("12345678901231";true) |test14Digits()|





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



