### E-commerce Use case-1 Backend Code

##   🌱 Features in our Ecommerce Site
-  Login
-  Register
-  Product 
-  Cart
-  Order
-  Product Description page

## 💼 Tech stack we used

- Spring boot
- JWT Authentication
- Java
- mvn
- MySQL

## 💻 Deployed Link

- <a href="https://13.232.254.213:2000" target="_blank" rel="noreferrer" >Click here</a>

##  📱 Services

-The service is just a simple hotel review REST service. It uses an MySQL to store the data. 
- Port we use is 1000
- Full integration with the latest Spring Framework: inversion of control, dependency injection, etc.
-  Our Endpoints for swagger openAPI
-   ```   
      BASE_URL "/users/",
            "/login/{userId}",
            "/authenticate",
            "/registerNewUser",
            "/products/save/{catId}",
            "/products/**",
            "/category/**",
            "/users/{userId}/product/{productId}/orders",
            "/product/{productId}/order",
            "/users/{userId}/order",
            "/search/**"
     ```
   - Swagger openAPI
      ```
      http://13.232.254.213:2000/swagger-ui.html/
      
      ```

##  📝  Running the project with MySQL
- Firstly we need to install the MySQL workbench 8.0 CE

In pom.xml you should need to add dependency
-    ```
       <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
     ```
   In Application.properties
   -  ```
        MySQL Database
        spring.datasource.url=jdbc:mysql://database-2.cctzpp5rvrir.ap-south-1.rds.amazonaws.com:3306/project_new
        spring.datasource.username=admin
        spring.datasource.password=admin1234
        spring-datasource-driver-class-name=com.mysql.cj.jdbc.Driver
      ```

##  🤝 Collabrators in code

- Swapnali
- Madhuri
- Shiv
- Nancy
