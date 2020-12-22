
download project from repository and  execute below command
    mvn spring-boot:run will run the applicaiton on port 8080
    
(Executable JAR is too big to commit, I can share it individually if reuired)

Once application is running, you can browse API with below swagger URL
  http://localhost:8080/swagger-ui.html#!/
In-memory H2 database can browsed with below URL
  http://localhost:8080/h2-console
  URL : jdbc:h2:mem:instrumentdb
  USERNAME: sa
  PASSWORD: password
  
Assumptions:
1) Current Thread Executor configuraiton is sufficient for 500 concurrent users, this can be change according to the environment
2) Timestamp in the request is in Unix mili-seconds
3) Spring Security is developed but disabled in this project to allow swagger and DB URL
4) Request fields like timestamp and price is converted into to appropiate datatype for scalability purpose
5) Exception Handling and Request Validation has been implimented just for demonstration and can be enhanced more according to the business requirement
6) JUnit is written on controller level to save development time.

