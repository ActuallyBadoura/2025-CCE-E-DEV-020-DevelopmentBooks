# Kata developerbooks store

A Development Book store written in Java using Spring Boot and Maven.

## Features

- API to calculate book basket price
## Run Locally

Clone the project

```bash
  git clone https://github.com/ActuallyBadoura/2025-CCE-E-DEV-020-DevelopmentBooks
```

Go to the project directory

```bash
  cd 2025-CCE-E-DEV-020-DevelopmentBooks
```

Clean install Maven dependencies

```bash
  mvn clean install
```

Start the server

```bash
  mvn spring-boot:run
```
The application will be running on http://localhost:8080

## API Documentation

- **Calculate Book Basket Price**

  - **Endpoint:** `/api/v1/calculate`
  - **Method:** `POST`
  - **Description:** Calculate the total price of a basket of books. Given a list of book IDs, returns the total price considering applicable discounts. The IDs correspond to the following books:
    - 1: Clean Code (Robert Martin, 2008)
    - 2: The Clean Coder (Robert Martin, 2011)
    - 3: Clean Architecture (Robert Martin, 2017)
    - 4: Test Driven Development by Example (Kent Beck, 2003)
    - 5: Working Effectively With Legacy Code (Michael C. Feathers, 2004)
  - **Request Body:**
    ```json
    {
      "basket": [1,2,3]
    }
    ```
  - **Response:**
    ```json
    {
      "totalPrice": 45.00,
      "currency": "EUR"
    }
    ```
- **Health Check**
  - **Endpoint:** `/api/v1/health`
  - **Method:** `GET`
  - **Description:** Check the health status of the application.
  - **Response:**
      ```text
      Book Store Service is running
      ```
    
- **Get Books List**
  - **Endpoint:** `/api/v1/books`
  - **Method:** `GET`
  - **Description:** Retrieve the list of available books in the store.
  - **Response:**
    ```json
    [
      {
        "id": 1,
        "title": "Clean Code",
        "author": "Robert Martin",
        "price": 20.00
      },
      {
        "id": 2,
        "title": "The Clean Coder",
        "author": "Robert Martin",
        "price": 22.00
      }
    ]
    ```

