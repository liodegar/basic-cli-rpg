# Multi-Topic RPG (MT-RPG)

This is a basic CLI RPG that is able to manage different RPG topics and games in a declarative way. This application was designed as a multilayer app 
(UI, business logic and data access layers) and developed by using only standard JDK classes.



## Design considerations

- Logging: A basic in-house logging framework was used to log messages and errors, where the generated logs are sent to the STDOUT console.

- Exception handling: all the errors and exceptions are gracefully managed and wrapped into higher level exceptions.

- Documentation: all the core classes are intradocumented (Javadocs).

- No hard coded values. All the config properties are defined in the `application.properties` file

- Test coverage: the main different layer artifacts (utility, service and repository) were tested to validate their proper functionality.

- Extensibility: new RPG topics and games can be added in a declarative way. New RPG topic implementations are retrieved dynamically (at runtime) 
by using a factory that creates instances belonging to the RPGTopic hierarchy. The Abstract Factory pattern was not an option because as GoF authors stated: "Supporting new kinds of products is difficult".
In fact, extending abstract factories to produce new kinds of Products is cumbersome, because the AbstractFactory interface fixes the set of products 
that can be created. Supporting new kinds of products requires extending the factory interface, which involves changing the AbstractFactory class and all of its subclasses.

## Stack
- Java 8
- Maven
- JUnit
- Mockito

## Getting Started

In order to start the application, you should first build it by executing the following command:

` mvn clean install`
 
 and then, you should execute from a command line the following instruction:

` java -jar target\basic-cli-rpg-1.0.jar`

After this, the Multi-Topic RPG (MT-RPG) app will start.


### Extending the Hera logging framework

   Remember that Multi-Topic RPG (MT-RPG) has a flexible and extensible architecture. If you wish to add another RPG topic and its games, you
   simply must create a new class with a public constructor without arguments, that
   extends the `com.acme.rpg.domain.game.AbstractRpgTopicImpl` class.
   Furthermore, add in the `application.properties` file the following keys:
    
    ` #full qualified class name of the new RPG topic class.`
    ` rpg.topic.key-3=com.acme.rpg.domain.game.ScienceFictionTopicImpl` 
    
    ` #Science Fiction RPG games`
    ` ScienceFictionTopicImpl.game.key-1=com.acme.rpg.domain.game.SpaceOdysseyRpgGameImpl`

For every RPG game belonging to new RPG topic, you should include a key following this pattern: `newClassSimpleName.game.key-n`, where n indicates the number of the game.

  Topic example code:

```java
   public class ScienceFictionTopicImpl extends AbstractRpgTopicImpl {
   
       public ScienceFictionTopicImpl() {
           super("ScienceFiction", "Science fiction (often shortened to Sci-Fi or SF) is a genre of speculative fiction");
       }
   
   }
```

Game example code:

```java
   public class SpaceOdysseyRpgGameImpl extends AbstractRpgGameImpl {
   
       public SpaceOdysseyRpgGameImpl() {
           super("Space Odyssey", new ScienceFictionTopicImpl(),
                   "In this game you need to fight against aliens from different galaxies",
                   Arrays.asList(new Mage(), new Rogue(), new Warrior()), //You can use pre-existent characters or create new ones
                   Arrays.asList(new GenericEnemy("DarthVader", Race.HUMAN, Gender.MALE),
                           new GenericEnemy("Vanna", Race.ROBOT, Gender.FEMALE),
                           new GenericEnemy("Sysker", Race.DWARF, Gender.MALE),
                           new GenericEnemy("Archibald", Race.HUMAN, Gender.MALE)));
       }
   
   
   }

```

If you need to create new player characters for this game, you should create them as classes that extend `com.acme.rpg.domain.character.PlayerCharacter` class.
Take `com.acme.rpg.domain.character.Warrior` class as a guideline.


## Class diagram of the core classes

![Multi-Topic RPG (MT-RPG) core classes](classDiagram.png "Multi-Topic RPG (MT-RPG) core classes")

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

Although I have tried my best, this guide undoubtedly may contain omissions, inaccuracies
and mistakes. You can help me to improve it by sending your suggestions or questions to:

* **Liodegar Bracamonte** - *Initial work* - [liodegar@gmail.com)


## License

Apache License 2.0.

## Acknowledgments

* To the all open source software contributors.