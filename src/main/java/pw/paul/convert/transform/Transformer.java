package pw.paul.convert.transform;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Requires;
import pw.paul.convert.registry.TypeRegistry;

/**
 * Transforms a string array to an object array.
 *
 * @author Paul
 */
public final class Transformer {

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
   * @param parameters Method parameters as an array required for the type conversion.
   *
   * @return String array converted to an object array.
   */
  public Object[] toArray(final Parameter[] parameters) {
    final Object[] objectData = new Object[parameters.length];

    int globalLength = 0;

    for ( int i = 0; i < objectData.length; i++ ) {
      Class<?> paramType = parameters[i].getType();

      int length = this.getLength(paramType);

      objectData[i] = TypeRegistry
        .get(
          Arrays.copyOfRange(this.data, globalLength, globalLength + length),
          paramType
        );

      globalLength += length;

    }
    return objectData;
  }

  /**
   * Returns the number of needed arguments for a type to convert.
   *
   * @param type The type representation to search for.
   *
   * @return The length of the amount of needed arguments.
   */
  private int getLength(Class<?> type) {
    Optional<Convertible<?>> convertibleEntry =
      TypeRegistry.getEntries().stream().filter(convertible -> TypeRegistry.matches(
        convertible,
        type
      )).findFirst();

    if (convertibleEntry.isPresent()) {
      Convertible<?> convertible = convertibleEntry.get();

      OptionalInt amount =
        Arrays.stream(convertible.getClass().getDeclaredMethods())
          .filter(method -> method.isAnnotationPresent(Requires.class))
          .mapToInt(method -> method.getAnnotation(Requires.class).amount()).findFirst();

      if (amount.isPresent()) {
        return amount.getAsInt();
      }
    }
    return 1;
  }
}
