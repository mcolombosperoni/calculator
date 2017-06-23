package com.mcs.calc.dao.impl;

import com.mcs.calc.dao.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class DivOperationImpl implements Operation {
  @Override
  public BigDecimal doOperation(BigDecimal number1, BigDecimal number2) {
    return number1.divide(number2, 2, RoundingMode.CEILING);
  }
}
