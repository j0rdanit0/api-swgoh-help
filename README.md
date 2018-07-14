# api-swgoh-help [![Maven Central](https://img.shields.io/maven-central/v/help.swgoh.api/swgoh-api-connector.svg?style=flat-square)](https://mvnrepository.com/artifact/help.swgoh.api/swgoh-api-connector) [![Build Status](https://img.shields.io/circleci/project/github/Discord4J/Discord4J/master.svg?style=flat-square)](https://circleci.com/gh/j0rdanit0/api-swgoh-help/tree/master)
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

# Other Language Clients
SwgohHelp.js (Javascript): https://github.com/r3volved/api-swgoh-help
