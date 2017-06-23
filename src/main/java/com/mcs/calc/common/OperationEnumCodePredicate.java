package com.mcs.calc.common;

import java.util.function.Predicate;


public class OperationEnumCodePredicate implements Predicate<OperationEnum> {

  private final String code;

  public OperationEnumCodePredicate(String code) {
    this.code = code;
  }

  @Override
  public boolean test(OperationEnum operationEnum) {
    return operationEnum.getCode().equals(code);
  }
}
