# Commands-Plus

Commands+ is a framework designed to simplify the implementation of commands for the user.

## Examples

#### CommandRepository
Create a new instance and register your commands. Don't forget to implement 
```CommandRepository#onMessageReceived``` somewhere otherwise your commands won't work.
```java
public class Main {
  
  public static void main(String[] args) {
    final CommandRepository commandRepository = new CommandRepository(".");

    commandRepository.register(new TestCommand());

    commandRepository.onMessageReceived("my command input", (exception) -> {});
  }
}
```

#### Create a new command
Annotate your command with ```@CommandInfo``` and create a 
method with ```@Execute``` so the framework knows where to insert your arguments.
```java
@CommandInfo(name = "Test")
public final class TestCommand extends Command {

  @Execute
  public void execute(String param1, int param2) {
    System.out.println("Hey, im a string " + param1);
    System.out.println("Automatic type conversion " + param2);
  }
}
```
#### Subcommands
Easily allows you to create subcommands by annotating any method(not your main method) 
with ```@SubExecute```
in your command to 
prevent ugly if-statements.
```java
@CommandInfo(name = "Test", subCommands = "add")
public final class TestCommand extends Command {

  @Execute
  public void execute(String param1, int param2) {
    System.out.println("Hey, im a string " + param1);
    System.out.println("Automatic type conversion " + param2);
  }

  @SubExecute(identifier = "add")
  public void executeSubCommandAdd(int number, int amount) {
    System.out.println(number + amount);
  }
  
}
```

### Error handling 

```java 
@CommandInfo(name = "Test")
public final class TestCommand extends Command {

  @Override
  public void onError(int errorCode) {
    switch (errorCode) {
      case MISSING_PARAMETER:
        System.err.println("Missing Parameter! send help...");
    }
  }
}
```

### Types

Create and registers your own types to use them in your command as an argument.
``` java
public class MyWonderfulType implements Convertible<BigInteger> {

  @Override
  public BigInteger convert(String[] param) {
    return new BigInteger(param[0]);
  }
  
}
```

If you are using more than one element of the array annotate with ```@Requires``` and 
pass the amount of needed arguments.
```java
public final class MyWonderfulType implements Convertible<BigInteger> {

  @Override
  @Requires(amount = 2)
  public BigInteger convert(String[] param) {
    return new BigInteger(param[0], Integer.parseInt(param[1]));
  }
}
```

Register somewhere...

``` java
TypeRegistry.register(new MyWonderfulType());
```

### Dependency 
[![](https://jitpack.io/v/Paulanerus/Commands-Plus.svg)](https://jitpack.io/#Paulanerus/Commands-Plus)

#### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Paulanerus</groupId>
    <artifactId>Commands-Plus</artifactId>
    <version>v1.1</version>
</dependency>
```

#### Gradle
```
repositories {
    maven { url 'https://jitpack.io' }
}
	
dependencies {
    implementation 'com.github.Paulanerus:Commands-Plus:v1.1'
}
```

### TODO
* Automatic type conversion
* Better error handling

Feel free to message me on Discord ```Paulee#1284```
