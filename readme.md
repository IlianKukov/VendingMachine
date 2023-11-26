# **Vending machine Application**

## Requirements:
###### Environment:
The application is created using IntelliJ, Java 17, Sprint boot 3.2.0 and gradle as build tool.
It uses SpringRestControllers to deliver its functionality.
Postman API platform is recommended as an interface.
Download Postman at: www.postman.com

###### Database:
MySQL database is required.
You can install MySQL using a docker container or download and install it from www.mysql.com

The default authentication settings the program uses are as following:

* user 'root'

* password '1234'

If required they can be configured in the application.yaml

###### Specifications:
All REST requests to the vending machine can be made while the program is running
at localhost:8080 using Postman

#### * For DB Seeding please uncomment the section in the DBInit Class

## **Vending Operations:**

###### Insert coin:
* POST request at: 'localhost:8080/api/coins/${coin_value}'

**Constraints:** 
* Only coins with values 0,1(10st), 0,2(20st), 0.5(50st), 1(1lv), 2(2lv) are accepted!
* In case another value is inserted the machine will output HTTP Error 400 Bad request.

**Expected Behaviour:**

* Status 200 OK

###### Return all coins:
* GET request at: 'localhost:8080/api/coins/returncoins'

**Expected Behaviour:**

* Status 200 OK

###### Get list of coins that are currently in the machine:
* GET request at: 'localhost:8080/api/coins'

**Expected Behaviour:**

* Status 200 OK and returning all the products available

###### Buy a product:
**First insert the required amount of coins using the 'Insert coin' operation :)**

* DELETE request at: 'localhost:8080/api/buy/${product_id}'

**Expected Behaviour:**

* Status 200 OK if the product exists and the amount of coins is enough.
* Status 404 NOT FOUND if the product does not exist.
* Status 426 UPGRADE REQUIRED if the amount is not enough.

**_The machine does not return any change as per requirements and the coins amount goes back to 0!_**

## Inventory Functions (CRUD):

###### Create product:

**Constraint:**
* The machine can hold up to 10 products of the same
* type. The products may be priced differently.

To create a product use the following:
* POST request at: 'localhost:8080/api/products'

    {
        "name": "$(PRODUCT_NAME)",
        "price": $(PRODUCT_PRICE),
        "type": "$(PRODUCT_TYPE)"
    }

**Expected Behaviour:**
* Status 200 OK
* Status 415 Unsupported Media Type if the product does not meet the creation JSON Pattern
* Status 405 Method not allowed if the count of products with the same type exceeds 10 as per requirements.

** To test the application You can insert the following 
* POST request at: 'localhost:8080/api/products'


    {
        "name": "Black waffle",
        "price": 6.0,
        "type": "Chocolate"
    },
    {
        "name": "Cola Light",
        "price": 10.0,
        "type": "Cola"
    },
    {
        "name": "KitKat",
        "price": 5.0,
        "type": "Nestle"
    },
    {
        "name": "Costa Signature Brew",
        "price": 15.0,
        "type": "Coffie"
    }

###### Return product:

**To return all products currently in the vending machine:**
* GET request at: 'localhost:8080/api/products'

**Expected Behaviour:**
* Status 200 OK and list of the found products
* Status 404 NOT FOUND if no products are in the machine

**To return a specific product with ${id} :**
* GET request at: 'localhost:8080/api/products/${id}'

**Expected Behaviour:**
* Status 200 OK if the product is found
* Status 404 NOT FOUND if the product does not exist

###### Update product:

**To update a specific product in the machine:**
* POST request at: 'localhost:8080/api/products/${id}'

USING THE FOLLOWING FORMAT:

    {
        "name": "$(PRODUCT_NAME)",
        "price": $(PRODUCT_PRICE),
        "type": "$(PRODUCT_TYPE)"
    }

**Constraint:**

*User Strings for the PRODUCT_NAME and PRODUCT_TYPE and DOUBLE for the PRODUCT_PRICE

**Expected Behaviour:**
* Status 200 OK if the product is found
* Status 406 NOT ACCEPTABLE if the product does not exist OR if there are already 10 products with the same type.


###### Delete product:

**To delete a specific product:**
* DELETE request at: 'localhost:8080/api/products/${id}'

**Expected Behaviour:**
* Status 200 OK if the product exists
* Status 404 NOT FOUND if the product does not exist

###### Delete all products:

**To delete all products in the machine:**
* DELETE request at: 'localhost:8080/api/products/deleteallproducts'

**Expected Behaviour:**
* Status 200 OK




