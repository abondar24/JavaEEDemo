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
You need to setup a database with name and credentials from persistence.xml file
Some parts of demo are run from main, some parts are stored as unit tests
