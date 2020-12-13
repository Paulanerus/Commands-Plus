package pw.paul.command.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pw.paul.command.model.Command;
import pw.paul.convert.transform.Transformer;

/**
 * Repository for all loaded {@link Command}'s.
 *
 * @author Paul
 */
public final class CommandRepository {

  /**
   * Contains every {@link Command}.
   */
  private final Set<Command> commandSet = new HashSet<>();

  /**
   * Default Command prefix.
   */
  private final String prefix;

  public CommandRepository(final String prefix) {
    this.prefix = prefix;

    System.out.println("Command+ by Paul.");
  }

  /**
   * Registers a specific amount of {@link Command}'s.
   *
   * @param commands The {@link Command}'s to register.
   */
  public boolean register(Command... commands) {
    return this.commandSet.addAll(Arrays.asList(commands));
  }

  /**
   * Finds a {@link Command} by a given Class.
   *
   * @param clazz Class of the {@link Command}.
   *
   * @return {@link Optional} of queried {@link Command}.
   */
  public Optional<Command> findCommand(Class<? extends Command> clazz) {
    return this.commandSet.stream().filter(command -> command.getClass() == clazz)
      .findFirst();
  }

  /**
   * Finds a {@link Command} by a given Name.
   *
   * @param name Name to search with.
   *
   * @return {@link Optional} of queried {@link Command}.
   */
  public Optional<Command> findCommandByAliases(String name) {
    return this.commandSet.stream().filter(command -> {
      if (command.isIgnoreCase()) {
        return command.getName().equalsIgnoreCase(name) || Arrays
          .stream(command.getAliases()).anyMatch(name::equalsIgnoreCase);
      } else {
        return command.getName().equals(name) || Arrays.asList(command.getAliases())
          .contains(name);
      }
    }).findFirst();
  }

  /**
   * Handles an incoming message.
   *
   * @param message The incoming message as {@link String}.
   */
  public void onMessageReceived(String message) {
    if (!message.startsWith(this.prefix)) {
      return;
    }

    String[] messageArguments = message.split("\\s+");

    String commandName = messageArguments[0].substring(1);

    Optional<Command> queryCommand = this.findCommandByAliases(commandName);

    queryCommand.ifPresent(command -> {
      String[] params = Arrays.copyOfRange(messageArguments, 1, messageArguments.length);

      Method method = command.findMethod();

      try {
        method.invoke(command, Transformer.create(params).toArray(method));
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * @return The set prefix.
   */
  public String getPrefix() {
    return this.prefix;
  }

  /**
   * @return A copy of the current command set.
   */
  public Set<Command> getCommands() {
    return new HashSet<>(this.commandSet);
  }
}
