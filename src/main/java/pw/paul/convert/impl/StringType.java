package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;

/**
 * Represents a {@link String}.
 *
 * @author Paul
 */
public final class StringType implements Convertible<String> {

  /**
   * {@inheritDoc}
   */
  @Override
  public String convert(String[] param) {
    return param[0];
  }
}
