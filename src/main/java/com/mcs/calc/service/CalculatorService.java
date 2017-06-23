package com.mcs.calc.service;

import java.math.BigDecimal;
import java.util.List;


public interface CalculatorService {
  BigDecimal addition(BigDecimal a, BigDecimal b);

  BigDecimal substraction(BigDecimal a, BigDecimal b);

  BigDecimal multiplication(BigDecimal a, BigDecimal b);

  BigDecimal division(BigDecimal a, BigDecimal b);


  BigDecimal processStringOperation(BigDecimal result, List<String> list);
}
