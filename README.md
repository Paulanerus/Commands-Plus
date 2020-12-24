# Commands-Plus

Commands+ is a framework designed to simplify the implementation of commands for the user.

## Examples
#### Create a new command

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

```java
@SubCommands(subCommands = {"add"})
@CommandInfo(name = "Test")
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

``` java
public class MyWonderfulType implements Convertible<BigInteger> {

  @Override
  public BigInteger convert(String[] param) {
    return new BigInteger(param[0]);
  }
  
}
```

register somewhere

``` java
TypeRegistry.register(new MyWonderfulType());
```