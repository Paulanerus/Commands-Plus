package pw.paul.convert.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation needed for a {@link Convertible} to know the amount of required
 * arguments.
 *
 * @author Paul
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Requires {

  /**
   * @return The amount of needed arguments.
   */
  int amount();

}
