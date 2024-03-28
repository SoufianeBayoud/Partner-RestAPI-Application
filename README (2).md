
# Partner REST API application

This is a Partner API Application made for B2Boost. This application allows you to manage partners  through a convenient JSON API. Below you'll find instructions for database configuration, running tests, and starting the application, etc.

## Functional Requirements
### Database Model
The application's database consists of one main entity: Partner.

### Recipe Entity Attributes:

![image](https://github.com/SoufianeBayoud/Partner-RestAPI-Application/assets/101556223/ae36a027-6a16-4ca3-9eab-da6c13ae859a)




## Technical Requirements

### Technology Stack
+ Programming Language: Java 
+ Build Automation: Maven
+ Application Framework: Spring Boot
+ Database: H2
+ Libraries: 
```spring-boot-starter-data-jpa
spring-boot-starter-web
springdoc-openapi-starter-webmvc-ui
h2
junit
mockito-core
lombok
spring-boot-starter-test
spring-boot-starter-actuator
```
### Application Architecture
The application has a layered architecture, consisting of: 
+ Model layer: Entities linked to the database
+ Controller layer: Responsible for the CRUD-operations and the HTTP requests.
+ Service layer: Responsible for the business logic
+ Repository layer: Responsible for the data access
+ Exception:



## API Reference
![image](https://github.com/SoufianeBayoud/Partner-RestAPI-Application/assets/101556223/972e9547-0bc1-4553-a239-f3f11d5bc042)

#### Get all partners

```http
GET /api/partners
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get partner by id
```http
GET /api/partners/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of the item to fetch |


#### Add a partner
```http
POST /api/partners
```
Adds a partner to the **database**

#### Update a partner 
```http
PUT /api/partners/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**.  Id to fetch the partner that you want to update |

#### Delete a partner by id
```http
PUT /api/partners/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**.  Id to fetch the partner that you want to delete |











## Installation

To install and set up the `Partner REST API application`, follow these steps:

 Clone the repository to your local machine:

```bash
git clone https://link-to-project
```

    
## Database Configuration Details

To configure the database for the application, modify the `application.properties` file located in the `src/main/resources` directory. Add or update the following properties with your database connection details:

```properties
spring.datasource.url=jdbc:h2:mem:B2BoostDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```

Make sure to replace `jdbc:h2:mem:B2BoostDB` with your actual database URL, `username` with your database username, and `password` with your database password.



## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  mvn install
```

Start the server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```



