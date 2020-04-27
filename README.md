# spring-boot-microservices

=>Stock price consuming yahoo api details
1. Consumes rest service from db-service to stock-service. 
2. Consumes Yahoo Finance API in stock-service.
3. Services register for discovery with eureka-service.
4. Has zuul proxy api gateway implemented.
5. Has hystrix fault tolerance mechanism and stream circuit breaker.
6. Has monitoring hystrix stream using hystrix dashboard.
7. Has angular UI for adding stocks to db and retrieving based on stock from stock service.

=>Finra phone alpha combinations details
1. Service take input phone number and gives the Mnemonic combinations.
2. Has UI developed using angular9 to enter input and hit the rest service.
3. Has couple of Test cases implemented.

=>Consuming Github api
1. Service implemented to consume github api which returns single object.
2. Used convertValue of ObjectMapper class to display customized output.
