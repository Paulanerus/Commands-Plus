package pw.paul.command.model;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import pw.paul.command.exception.CommandNotDescribedException;
import pw.paul.command.exception.MissingExecuteDefinitionException;
import pw.paul.command.model.execution.Execute;
import pw.paul.command.model.execution.SubExecute;

/**
 * Command to execute actions.
 *
 * @author Paul
 */
public abstract class Command {

  /**
   * Error code for the missing parameter error.
   */
  public static final int MISSING_PARAMETER = 0;

  /**
   * Method that allows error handling.
   *
   * @param errorCode The resulting error.
   */
  public abstract void onError(int errorCode);

  /**
   * @return The {@link Command}'s name.
   */
  public final String getName() {
    return this.getInfo().name();
  }

  /**
   * @return The {@link Command}'s aliases.
   */
  public final String[] getAliases() {
    return this.getInfo().aliases();
  }

  /**
   * @return The {@link Command}'s description.
   */
  public final String getDescription() {
    return this.getInfo().description();
  }

  /**
   * @return If the letters case can be ignored.
   */
  public final boolean isIgnoreCase() {
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
   * Checks if the given identifier is a subcommand.
   *
   * @param identifier The identifier to check.
   *
   * @return True, if the given identifier is a subcommand.
   */
  public final boolean isSubCommand(String identifier) {
    return Arrays.stream(this.getSubCommands())
      .anyMatch(subCommand -> subCommand.equalsIgnoreCase(identifier));
  }

  /**
   * @return Every subcommand of this {@link Command}.
   */
  private String[] getSubCommands() {
    return this.getInfo().subCommands();
  }

  /**
   * Finds a subcommand method by an identifier and returns the result as an {@link
   * Optional<Method>}
   *
   * @param identifier The identifier to search for.
   *
   * @return The result as an {@link Optional<Method>}
   */
  public final Optional<Method> findSubMethod(String identifier) {
    return Arrays.stream(this.getClass().getDeclaredMethods())
      .peek(method -> method.setAccessible(true))
      .filter(method -> method.isAnnotationPresent(
        SubExecute.class)).filter(
        method -> method.getAnnotation(SubExecute.class).ignoreCase() ? method
          .getAnnotation(SubExecute.class).identifier().equalsIgnoreCase(identifier)
          : method.getAnnotation(SubExecute.class).identifier().equals(identifier))
      .findFirst();
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

  /**
   * Tests whether the specified parameters and method lead to an error.
   *
   * @param params Provided parameters.
   * @param method Provided method.
   *
   * @return Whether there will be an error or not.
   */
  public final boolean hasError(String[] params, Method method) {
    if (params.length < method.getParameters().length) {
      this.onError(MISSING_PARAMETER);
      return true;
    }

    return false;
  }
}
