package com.mcs.calc.common;

import java.util.Arrays;
import java.util.Optional;


public enum OperationEnum {
  ADD("+"),//
  SUB("-"),//
  MULT("*"),//
  DIV("/")//
  ;
  private final String code;

  OperationEnum(String code) {
    this.code=code;
  }

  public String getCode() {
    return code;
  }

  public static boolean contains(final String code) {
    return Arrays.asList(values()).stream().anyMatch(new OperationEnumCodePredicate(code));
  }

  public static Optional<OperationEnum> byCode(final String code) {
    return Arrays.asList(values()).stream().filter(new OperationEnumCodePredicate(code)).findFirst();
  }

}
