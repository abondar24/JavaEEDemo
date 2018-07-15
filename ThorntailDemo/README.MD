#Thorntail Demo

This demo is showing basic usage of Thorntail for building microservices

It's an adoptation of RESTDemo. [Check here for details](../README.md) 

The demo shows usage of JaxRS,EJB and JPA with Hibernate alongside H2 database

## Build and run

```yaml
  mvn clean package
  # in order to build without tests 
  # mvn clean package -DskipTests=true
  
  java -jar target/ThorntailDemo-1.0-SNAPSHOT-thorntail.jar 
  or
  mvn thorntail:run
  check: localhost:8080/ws/book_service/get_title or localhost:8080/ws/customer_service/ping 
```


