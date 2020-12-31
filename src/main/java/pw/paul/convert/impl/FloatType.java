package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

/**
 * Represents a {@link Float}.
 *
 * @author Paul
 */
@Primitive(classes = {Float.class, float.class})
public final class FloatType implements Convertible<Float> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Float convert(String[] param) {
    return Float.parseFloat(param[0]);
  }
}
