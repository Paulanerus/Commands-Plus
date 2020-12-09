package pw.paul.command.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pw.paul.command.model.Command;

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

  public void onMessageReceived(String message) {
    if (!message.startsWith(this.prefix)) {
      return;
    }
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
