package pw.paul.convert.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that keeps the representations of a primitive.
 *
 * @author Paul
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Primitive {

  /**
   * @return Class that represent the primitive.
   */
  Class<?>[] classes();

}
