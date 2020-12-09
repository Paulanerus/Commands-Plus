package pw.paul.command.model;

import pw.paul.command.exception.CommandNotDescribedException;

/**
 * Command to execute actions.
 *
 * @author Paul
 */
public class Command {

  /**
   * @return The {@link Command}'s name.
   */
  public String getName() {
    return this.getInfo().name();
  }

  /**
   * @return The {@link Command}'s aliases.
   */
  public String[] getAliases() {
    return this.getInfo().aliases();
  }

  /**
   * @return The {@link Command}'s description.
   */
  public String getDescription() {
    return this.getInfo().description();
  }

  /**
   * @return If the letters case can be ignored.
   */
  public boolean isIgnoreCase() {
    return this.getInfo().ignoreCase();
  }

  /**
   * @return The Info of this {@link Command}.
   */
  private CommandInfo getInfo() {
    if (!this.getClass().isAnnotationPresent(CommandInfo.class)) {
      throw new CommandNotDescribedException();
    }
    return this.getClass().getAnnotation(CommandInfo.class);
  }

}
