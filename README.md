# Kata developerbooks store

A Development Book store written in Java using Spring (not Boot) with Jetty server.

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
  mvn jetty:run
```
The application will be running on http://localhost:8080

## API Documentation

- **Calculate Book Basket Price**

  - **Endpoint:** `/api/v1/store/calculate`
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
- **Get Books**

    - **Endpoint:** `/api/v1/books`
    - **Method:** `GET`
    - **Description:** Get all books available:
        - 1: Clean Code (Robert Martin, 2008)
        - 2: The Clean Coder (Robert Martin, 2011)
        - 3: Clean Architecture (Robert Martin, 2017)
        - 4: Test Driven Development by Example (Kent Beck, 2003)
        - 5: Working Effectively With Legacy Code (Michael C. Feathers, 2004)
    - **Response:**
      ```json
      [
       {
        "id": 1,
        "title": "Clean Code",
        "author": "Robert C. Martin",
        "price": 50.00
       },
       {
        "id": 2,
        "title": "The Clean Coder",
        "author": "Robert C. Martin",
        "price": 50.00
       },
       {
        "id": 3,
        "title": "Clean Architecture",
        "author": "Robert C. Martin",
        "price": 50.00
       },
       {
        "id": 4,
        "title": "Test-Driven Development by Example",
        "author": "Kent Beck",
        "price": 50.00
       },
       {
        "id": 5,
        "title": "Working Effectively with Legacy Code",
        "author": "Michael Feathers",
        "price": 50.00
       }
      ]
      ```
