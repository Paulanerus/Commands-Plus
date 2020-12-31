package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

/**
 * Represents a {@link Short}.
 *
 * @author Paul
 */
@Primitive(classes = {Short.class, short.class})
public final class ShortType implements Convertible<Short> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Short convert(String[] param) {
    return Short.parseShort(param[0]);
  }
}
