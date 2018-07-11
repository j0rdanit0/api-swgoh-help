# api-swgoh-help
Java client wrapper for the API at https://api.swgoh.help

# Usage
Include via maven:
```xml
<dependency>
    <groupId>help.swgoh.api</groupId>
    <artifactId>swgoh-api-connector</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
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

Request various other data:
```java
Map<String,Unit> units = api.getUnits();

List<Event> events = api.getEvents();

List<TB> territoryBattleInfo = api.getTBs();
```