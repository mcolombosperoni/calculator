package com.mcs.calc.dao;

import java.math.BigDecimal;


public interface Operation {
  BigDecimal doOperation(BigDecimal number1, BigDecimal number2);
}
