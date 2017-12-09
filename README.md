# JavaEE Demo

Bunch of demos about JAVA EE.

Each maven module covers different part of J2EE

# Demos

- BasicCDI: core of Context Dependency injection. 
```yaml
 Build: mvn clean install
 Run: exec:java
```

- Bean validataion: constraints and custom validators usage
```yaml
 Build: mvn clean install
```

- ORM Demo: JPA and Hibernate usage including(persistence,quering,criteria api,caching,locks and listener usage) 

```yaml
 Build: mvn clean install
 Run: exec:java
```

- EJB Demo: EJB usage examples 

```yaml
 Build: mvn clean install
```

- JSF Demo: JSF Web application example

```yaml
 Build and deploy: mvn clean install wildfly:deploy
```

- Document Demo: XML and Json parsing and transforming demos
```yaml
 Build: mvn clean install
 Run: exec:java
```

- JMS Demo: Wildfly based jms. For activeMQ related messaging see [JMSDemo](https://github.com/abondar24/JMSDemo)
```yaml
Build: mvn clean install
Run Producer: java -jar target/producer.jar
Run Consumer: java -jar target/consumer.jar
 
``` 

# External Stuff

- For ORM demo you need to set up a database with name and credentials from persistence.xml file
   Some parts of demo are run from main, some parts are stored as unit tests  
- EJB and JSF demos are build around Wildfly 11 server. You need to reconfigure them for another servers
- JSF demo is using Wildfly running in a Docker container so wildfly plugin and idea are configured for remote server.
- For JMS Demo you need to create a queue on the server  and add jndi name with format java:jboss/exported/queueName. 
Before build change producer and consumer class names in pom file. 
Also you need a user with credentials from jms.properties file and role "guest"