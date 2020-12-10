package pw.paul.command.model;

import java.lang.reflect.Method;
import java.util.Arrays;

import pw.paul.command.exception.CommandNotDescribedException;
import pw.paul.command.exception.MissingExecuteDefinitionException;
import pw.paul.command.model.execution.Execute;

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

  /**
   * @return Finds the "execution" method.
   */
  public final Method findMethod() {
    return Arrays.stream(this.getClass().getDeclaredMethods())
      .peek(method -> method.setAccessible(true))
      .filter(method -> method.isAnnotationPresent(Execute.class)).findFirst()
      .orElseThrow(MissingExecuteDefinitionException::new);
  }

}
