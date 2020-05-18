# Feedback Web App

Feedback Web application is a Java library for dealing with Employee feed back reviews.

# Technologies
  Java8, Maven, Spring boot, Spring data, JPA, MySql, Tomcat Server. 

# Prerequisites

  Download and install Java8, MySql, Spring Testing Suite and Tomcat Server. 

# Run Application
  Create JNDI on server side. In tomcat add the below code in "tomcat installed directory/conf/server.xml
```XML
  <GlobalNamingResources>
    <Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"  maxActive="20" maxIdle="0" 
              maxWait="10000" name="jdbc/feedback" password="root" username="root" 
              type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/feedback"/>
   </GlobalNamingResources>
```
Run Maven command from the workspace directory and deploy the war file in tomcat.
```cmd
  Run mvn install
```

# Design
  
  Developed Rest APIs using Spring Boot. See below for request 
  
 # Admin Login
 * UserName: admin
 * Password: password
  
  Http Request -> Spring Security(JWT Authentication)-> RestController -> Service -> DAO -> DB
  
  Once the request comes, JwtRequestFilter will check for the Jwt token and autherizes the request to pass it to Rest controller, 
  right now iam not saving jwt token in DB/external Cache saving it in SpringSecurityContext.
                
# DB Design

Below is the ER diagram for the application; Run create createTables.sql for DB tables creation.


![alt text](https://github.com/smadanu2134/FullStackEngineerChallenge/blob/master/images/feedback-ER.PNG)

# Admin Login
 * UserName: admin
 * Password: passwoord
