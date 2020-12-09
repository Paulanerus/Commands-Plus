package pw.paul.command.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command info annotation.
 *
 * @author Paul
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

  /**
   * @return The {@link Command}'s name.
   */
  String name();

  /**
   * @return The {@link Command}'s aliases.
   */
  String[] aliases() default {};

  /**
   * @return The {@link Command}'s description.
   */
  String description() default "";

  /**
   * @return If the letters case can be ignored.
   */
  boolean ignoreCase() default true;

}
