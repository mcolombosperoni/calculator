package com.mcs.calc.dao.impl;

import com.mcs.calc.dao.Operation;

import java.math.BigDecimal;


public class AddOperationImpl implements Operation {
  @Override
  public BigDecimal doOperation(BigDecimal number1, BigDecimal number2) {
    return number1.add(number2);
  }
}
