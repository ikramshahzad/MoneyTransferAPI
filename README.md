# MoneyTransferAPI
A sample spring boot project for money transfer between account


### Prerequisite
- Maven
- JDK 1.8+

### Packaging
```
mvn package
```
### Test
```
mvn test
```
### Running
```
java -jar MoneyTransferAPI-0.0.1-SNAPSHOT-spring-boot.jar
```
### Data
Initial data (src\main\resources\accounts-mock.json) will be loaded in the H2 database when application start.

## Feature
It provides APIs for following 2 features
- Fetch all accounts information
- Fetch account information
- Create money transfer transaction
- Fetch all transaction information
- Fetch transaction information

### Basic API Information
| Method | Path | Usage |
| --- | --- | --- |
| GET | /api/account/ | fetch all account information |
| GET | /api/account/{id} | fetch account information |
| GET | /api/transaction | fetch all transaction information |
| GET | /api/transaction/{id} | fetch transaction information |
| POST | /api/transaction/transfer | create money transfer transaction |

### Error Code
| Code | Description |
| --- | --- |
| ERR_ACC_B_0001 | used when account has insufficient balance |
| ERR_ACC_B_0002 | used when account does not exist. |
| ERR_ACC_B_0003 | used when invalid account id |
| ERR_ACC_B_0004 | used when transaction does not exist. |
| ERR_ACC_B_0005 | used when account already exist. |


### Library used
| Library | Usage |
| --- | --- |
| spring boot | for spring boot application |
| spring data jpa | for ORM and DB operation purpose |
| H2 | database for demo purpose |
| springfox swagger | generate swagger API specification from code |
| springfox swagger ui | generate swagger ui page for specification |

### Post Script
script file \MoneyTransferAPIs.postman_collection.json contains postmen collection to call end point.

| Method | Path | Usage | Name |
| --- | --- | --- | --- |
| GET | /api/account/ | retrieve all account information | fetchAllAccounts | 
| GET | /api/account/{id} | retrieve account information | fetchAccount |  
| GET | /api/transaction | retrieve all transaction information | fetchAllTransactions | 
| GET | /api/transaction{id} | retrieve transaction information | fetchTransaction |
| POST | /api/transaction | create money transfer transaction | moneyTransfer | 