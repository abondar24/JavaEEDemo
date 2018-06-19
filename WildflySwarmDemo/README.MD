#WILDFLY-SWARM Demo

This demo is showing basic usage of Wildfly-Swarm for building microservices

It's an adoptation of RESTDemo. [Check here for details](../README.md) 

The demo shows usage of JaxRS,EJB and JPA with Hibernate alongside H2 database

## Build and run

```yaml
  gradle build
  # in order to build without tests 
  # gradle build -x test
  
  java -jar build/libs/WildflySwarmDemo-swarm.jar
```

## P.S

Current version of Wildfly-Swarm doesn't support JDK 10. 



