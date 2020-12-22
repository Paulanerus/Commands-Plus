package pw.paul.command.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the subcommands of a {@link Command}.
 *
 * @author Paul
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubCommands {

  /**
   * @return Array of the subcommands.
   */
  String[] subCommands();

}
