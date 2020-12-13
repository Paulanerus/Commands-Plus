package pw.paul.convert.transform;

import java.lang.reflect.Method;

import pw.paul.convert.registry.TypeRegistry;

/**
 * Transforms a string array to an object array.
 *
 * @author Paul
 */
public class Transformer {

  /**
   * Contains the data as strings
   */
  private final String[] data;

  private Transformer(String[] data) {
    this.data = data;
  }

  /**
   * Creates a new {@link Transformer}
   *
   * @param data Data which needs to be converted.
   *
   * @return A new created {@link Transformer}
   */
  public static Transformer create(String[] data) {
    return new Transformer(data);
  }

  /**
   * Convert every String to the requested type.
   *
   * @param method Method representation to get the requested types.
   *
   * @return String array converted to an object array.
   */
  public Object[] toArray(final Method method) {
    final Object[] objectData = new Object[this.data.length];

    for ( int i = 0; i < method.getParameters().length; i++ ) {
      objectData[i] = TypeRegistry
        .get(new String[]{this.data[i]}, method.getParameters()[i].getType());
    }

    return objectData;
  }

}
