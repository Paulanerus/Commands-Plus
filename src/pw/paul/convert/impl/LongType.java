package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

/**
 * Represents a {@link Long}.
 *
 * @author Paul
 */
@Primitive(classes = {Long.class, long.class})
public class LongType implements Convertible<Long> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Long convert(String[] param) {
    return Long.parseLong(param[0]);
  }
}
