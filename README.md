# COVID-19 TestApp

## Technology Stack
Programming Language and framework used: Java, Spring Boot Framework, MySQL, Hibernate, RESTful APIs 

Prerequisites for building and deploying your application locally
1. MySQL
2. Postman

## Build Instructions
1. Clone the testapp repository on your local
2. Update testapp/src/main/resources/application.properties file with database connection details
3. Open a terminal tab in testapp directory
4. Run the command ./mvnw clean install to build the application
5. Run the command ./mvnw spring-boot:run to run the application
6. Insert an entry in Patient table with role as "ROLE_ADMIN"
7. Open Postman application to test API results
8. Now select the POST option and enter the URL as "http://localhost:8080/testapp/register"
9. In the body section below, select 'raw' and then select 'JSON(application/json)'
10. Write the parameters to be sent in JSON format and click on 'Send', see the results on the window below
11. Patient is now registered. Now follow the API calls given below to book test and get results


## REST API endpoints
- Register a new user
  * **Method:** ` POST`
  * **URL:** `/testapp/register`
  * **Body:**
  	```json
  	{
	    "firstName": "John",
	    "lastName": "Doe",
	    "username": "johndoe@gmail.com",
	    "age": 25,
	    "password": "hello123@"
	}
    ```

  * **Success Response:**

  * **Code:** `201 CREATED`
 
  * **Error Response:**

  * **Code:** `400 BAD REQUEST`
    **Content:** 
    ```json
    {"error" : "BAD REQUEST" }
    ```

    OR

  * **Code:** `409 CONFLICT` 
    **Content:** 
    ```json
    { "error" : "User already exits" }
    ````

All below endpoints are authenticated with basic authentication
* **Method:**` GET `
  * **URL:**`/testapp/get`
  * **Success Response:**

  * **Code:** `200 OK`
    **Content:** 
    ```json
  	{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "username": "johndoe@gmail.com",
    "age": 25,
    "password": "$2a$10$duYhsh83.2M00Vp6AKHPg.3KEmNuWJHh33dx8/",
    "test": []
	}
    ```
 
  * **Error Response:**

  * **Code:** `401 UNAUTHORIZED`
    **Content:** 
    ```json
    { "error" : "You are unauthorized to make this request." }
    ```

 - Book new test

 Choose basic auth and give the same email and password which you used to register as a patient
  * **Method:**` POST`
  * **URL:**`/testdate/book`
  * **Body:**
  	```json
  	{
		"date" : "2020-07-07"
	}
    ```
  * **Success Response:**

  * **Code:** `201 CREATED`
 
  * **Error Response:**

  * **Code:** `400 BAD REQUEST`
    **Content:** 
    ```json
    {"error" : "BAD REQUEST" }
    ```

     OR

  * **Code:** `401 UNAUTHORIZED`
    **Content:** 
    ```json
    { "error" : "You are unauthorized to make this request." }
    ```

- Update test result by Doctor

 Choose basic auth and give the email and password of the user with role as "ROLE_ADMIN"

  * **Method:**` PUT`
  * **URL:**`/testing/update`
  * **Body:**
  	```json
  	{
	    "testId": 1,
	    "result": "Positive",
	    "testDates": {
	        "date": "2020-07-07"
	    }
	}
    ```
  * **Success Response:**

  * **Code:** `201 CREATED`
 
  * **Error Response:**

  * **Code:** `400 BAD REQUEST`
    **Content:** 
    ```json
    {"error" : "BAD REQUEST" }
    ```

     OR

  * **Code:** `401 UNAUTHORIZED`
    **Content:** 
    ```json
    { "error" : "You are unauthorized to make this request." }
    ```