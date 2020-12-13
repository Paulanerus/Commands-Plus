package pw.paul.convert.model;

import java.lang.reflect.ParameterizedType;

/**
 * An interface to convert a String array to an object.
 *
 * @param <T> The generic type.
 *
 * @author Paul
 */
public interface Convertible<T> {

  /**
   * Converts a given amount of Strings to an Object.
   *
   * @param param Strings to convert.
   *
   * @return An Object.
   */
  T convert(String[] param);

  /**
   * @return The generic type.
   */
  default String getType() {
    return ((ParameterizedType) this.getClass().getGenericInterfaces()[0])
      .getActualTypeArguments()[0].getTypeName();
  }

}
