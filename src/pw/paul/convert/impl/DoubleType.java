package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

@Primitive(classes = {Double.class, double.class})
public class DoubleType implements Convertible<Double> {

  @Override
  public Double convert(String[] param) {
    return Double.parseDouble(param[0]);
  }
}
