package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

/**
 * Represents a {@link Integer}.
 *
 * @author Paul
 */
@Primitive(classes = {Integer.class, int.class})
public final class IntType implements Convertible<Integer> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer convert(String[] param) {
    return Integer.parseInt(param[0]);
  }
}
