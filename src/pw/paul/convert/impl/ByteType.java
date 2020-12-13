package pw.paul.convert.impl;

import pw.paul.convert.model.Convertible;
import pw.paul.convert.model.Primitive;

@Primitive(classes = {Byte.class, byte.class})
public class ByteType implements Convertible<Byte> {

  @Override
  public Byte convert(String[] param) {
    return Byte.parseByte(param[0]);
  }
}
