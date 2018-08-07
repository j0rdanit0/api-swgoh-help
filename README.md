# api-swgoh-help [![Maven Central](https://img.shields.io/maven-central/v/help.swgoh.api/swgoh-api-connector.svg?style=flat-square)](https://mvnrepository.com/artifact/help.swgoh.api/swgoh-api-connector)
Java client wrapper for the API at https://api.swgoh.help

# Usage
Include the swgoh-api-connector artifact:

`@VERSION@` should be replaced with the version you want to use.

### Maven
```xml
<dependency>
    <groupId>help.swgoh.api</groupId>
    <artifactId>swgoh-api-connector</artifactId>
    <version>@VERSION@</version>
</dependency>
```

### Gradle
```groovy
compile 'help.swgoh.api:swgoh-api-connector:@VERSION@'
```

### Examples
In code, use the SwgohAPIBuilder to initialize a new instance of the client:
```java
SwgohAPI api = new SwgohAPIBuilder()
        .withUsername( "username" )
        .withPassword( "password" )
        .withClientId( "clientId" )
        .withClientSecret( "clientSecret" )
        .build();
```

Request player profile by ally code:
```java
int allyCode = 123456789;
Player player = api.getPlayer( allyCode );
```

Request guild rosters by ally code:
```java
int allyCode = 123456789;
List<Player> guild = api.getGuild( allyCode );
```

Request various other kinds of data:
```java
Map<String,Unit> units = api.getUnits();
List<Event> events = api.getEvents();
List<TB> territoryBattleInfo = api.getTBs();
```
...and so much more!

Want to receive the raw JSON and parse it yourself? Each endpoint is overloaded with a -JSON postfix that simply returns a JSON String.

# Language Specification
To receive data in a supported language, simply pass the specified language into the overloaded method of your choice.

Example of language-specified result:
```java
Map<String,Unit> units = api.getUnits( SwgohAPI.Language.French );
```

# Spring Integration
If you'd like to see this work with [Spring's dependency injection magic](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core), simply write something similar to the following code:
```java
@Configuration
public class SwgohConfiguration
{
    @Bean
    public SwgohAPI swgohApi()
    {
        return new SwgohAPIBuilder()
                .withUsername( "username" )
                .withPassword( "password" )
                .withClientId( "clientId" )
                .withClientSecret( "clientSecret" )
                .build();
    }
}
```

And it's as simple as that. Now you can Autowire the API client to your heart's content.
```java
@Service
public class SwgohService
{
    @Autowired
    private SwgohAPI swgohApi;
}
```

# Available Language Clients
NodeJS:<br/>
https://github.com/r3volved/api-swgoh-help/tree/node

Java:<br/>
https://github.com/j0rdanit0/api-swgoh-help

C#:<br/>
https://github.com/SdtBarbarossa/SWGOH-Help-Api-C-Sharp
https://gist.github.com/dwcullop/9c6b7933fe23163e59b94da1997adee7

PHP:<br/>
https://github.com/r3volved/api-swgoh-help/tree/php

Google App Script:<br/>
https://github.com/Dragonsight91/api-swgoh-help
