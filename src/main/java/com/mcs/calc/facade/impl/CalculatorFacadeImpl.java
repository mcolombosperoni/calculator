package com.mcs.calc.facade.impl;

import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.facade.CalculatorFacade;
import com.mcs.calc.service.CalculatorService;
import com.mcs.calc.service.impl.CalculatorServiceImpl;
import com.mcs.calc.common.StringOperationMalformedException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class CalculatorFacadeImpl implements CalculatorFacade {

  private static final CalculatorFacade instance = new CalculatorFacadeImpl(CalculatorServiceImpl.getInstance());
  private final CalculatorService calculatorService;


  protected CalculatorFacadeImpl(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
    final DecimalFormat df = new DecimalFormat();
    df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
  }

  public static CalculatorFacade getInstance(){
    return instance;
  }

  @Override
  public BigDecimal addition(BigDecimal a, BigDecimal b){
    return calculatorService.addition(a, b);
  }

  @Override
  public BigDecimal substraction(BigDecimal a, BigDecimal b) {
    return calculatorService.substraction(a, b);
  }

  @Override
  public BigDecimal multiplication(BigDecimal a, BigDecimal b){
    return calculatorService.multiplication(a, b);
  }

  @Override
  public BigDecimal division(BigDecimal a, BigDecimal b) throws CalcApplicationException {
    try {
      return calculatorService.division(a, b);
    } catch (Exception e) {
      throw new CalcApplicationException(e);
    }
  }

  @Override
  public BigDecimal stringOperation(String operations) throws StringOperationMalformedException {
    BigDecimal result = BigDecimal.ZERO;
    try {
      final List<String> list = Arrays.stream(operations.split(" ")).collect(Collectors.toCollection(LinkedList::new));
      result = calculatorService.processStringOperation(result, list);
    } catch (Exception e) {
      throw new StringOperationMalformedException(e);
    }
    return result;
  }


}

