package pw.paul.command.model.execution;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a Method as the "sub execution" method.
 *
 * @author Paul
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubExecute {

  /**
   * @return The String identifier for the subcommand.
   */
  String identifier();

  /**
   * @return If the letters case can be ignored.
   */
  boolean ignoreCase() default true;

}
