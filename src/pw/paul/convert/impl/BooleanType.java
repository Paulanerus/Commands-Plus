package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

@Primitive(classes = {Boolean.class, boolean.class})
public final class BooleanType implements Convertible<Boolean> {

  @Override
  public Boolean convert(String[] param) {
    return Boolean.parseBoolean(param[0]);
  }
}
