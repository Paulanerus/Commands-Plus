package pw.paul.convert.registry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import pw.paul.convert.exception.MissingTypeException;
import pw.paul.convert.impl.BooleanType;
import pw.paul.convert.impl.ByteType;
import pw.paul.convert.impl.DoubleType;
import pw.paul.convert.impl.FloatType;
import pw.paul.convert.impl.IntType;
import pw.paul.convert.impl.LongType;
import pw.paul.convert.impl.ShortType;
import pw.paul.convert.impl.StringType;
import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

/**
 * Registry to register or get a type.
 *
 * @author Paul
 */
public final class TypeRegistry {

  /**
   * Contains every registered object.
   */
  private static final Set<Convertible<?>> CONVERTIBLE_SET = new HashSet<>();

  /**
   * Registers a new convertible object.
   *
   * @param convertible The object to register.
   */
  public static boolean register(Convertible<?> convertible) {
    return CONVERTIBLE_SET.add(convertible);
  }

  /**
   * Gets and converts a given string array.
   *
   * @param data The data to convert.
   * @param clazz Class to filter the converter.
   * @param <T> The generic type.
   *
   * @return The converted data.
   */
  public static <T> T get(String[] data, Class<? extends T> clazz) {
    return (T) CONVERTIBLE_SET.stream()
      .filter(convertible -> matches(convertible, clazz)).findFirst()
      .orElseThrow(MissingTypeException::new).convert(data);
  }

  /**
   * Checks if the generic type and the given class are equals.
   *
   * @param convertible The {@link Convertible} to compare with.
   * @param clazz The class to compare with.
   *
   * @return If the classes are equal.
   */
  private static boolean matches(Convertible<?> convertible, Class<?> clazz) {
    return hasPrimitive(convertible) ? Arrays.stream(getPrimitiveClasses(convertible))
      .map(
        Class::getName).anyMatch(primitiveName -> primitiveName.equals(clazz.getName()))
      : convertible.getType().equals(clazz.getName());
  }

  /**
   * Checks if the given {@link Convertible} is annotated with {@link Primitive}.
   *
   * @param convertible The {@link Convertible} to check.
   *
   * @return If the {@link Convertible} is annotated with {@link Primitive}.
   */
  private static boolean hasPrimitive(Convertible<?> convertible) {
    return convertible.getClass().isAnnotationPresent(Primitive.class);
  }

  /**
   * Gets the classes from the {@link Primitive} annotation.
   *
   * @param convertible {@link Convertible} to get the annotated classes from.
   *
   * @return The annotated classes.
   */
  private static Class<?>[] getPrimitiveClasses(Convertible<?> convertible) {
    return convertible.getClass().getAnnotation(Primitive.class).classes();
  }

  static {
    register(new StringType());

    register(new ByteType());
    register(new BooleanType());

    register(new ShortType());

    register(new IntType());
    register(new FloatType());

    register(new LongType());
    register(new DoubleType());
  }

}
