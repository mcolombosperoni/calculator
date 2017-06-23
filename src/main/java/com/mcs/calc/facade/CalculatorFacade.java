package com.mcs.calc.facade;

import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.common.StringOperationMalformedException;

import java.math.BigDecimal;


public interface CalculatorFacade {
  BigDecimal addition(BigDecimal a, BigDecimal b);

  BigDecimal substraction(BigDecimal a, BigDecimal b);

  BigDecimal multiplication(BigDecimal a, BigDecimal b);

  BigDecimal division(BigDecimal a, BigDecimal b) throws CalcApplicationException;

  BigDecimal stringOperation(String operations) throws StringOperationMalformedException;
}
