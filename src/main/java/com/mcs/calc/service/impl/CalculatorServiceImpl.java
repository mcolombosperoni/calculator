package com.mcs.calc.service.impl;

import com.google.common.base.MoreObjects;
import com.mcs.calc.common.OperationEnum;
import com.mcs.calc.service.CalculatorService;
import com.mcs.calc.common.OperationMap;

import java.math.BigDecimal;
import java.util.List;


public class CalculatorServiceImpl implements CalculatorService {

  private static final CalculatorService instance = new CalculatorServiceImpl(OperationMap.getInstance());
  private OperationMap operationMap;

  protected CalculatorServiceImpl(OperationMap map) {
    this.operationMap = map;
    this.operationMap.initOperationMap();
  }

  public static CalculatorService getInstance(){
    return instance;
  }


  @Override
  public BigDecimal addition(BigDecimal a, BigDecimal b){
    return operationMap.getOperation(OperationEnum.ADD).doOperation(a, b);
  }

  @Override
  public BigDecimal substraction(BigDecimal a, BigDecimal b) {
    return operationMap.getOperation(OperationEnum.SUB).doOperation(a, b);
  }

  @Override
  public BigDecimal multiplication(BigDecimal a, BigDecimal b){
    return operationMap.getOperation(OperationEnum.MULT).doOperation(a, b);
  }

  @Override
  public BigDecimal division(BigDecimal a, BigDecimal b){
    return operationMap.getOperation(OperationEnum.DIV).doOperation(a, b);
  }

  @Override
  public BigDecimal processStringOperation(BigDecimal result, final List<String> list) throws NumberFormatException, ArithmeticException{
    OperationEnum operationEnum = null;

    BigDecimal value = BigDecimal.ZERO;
    for (String el : list){

      if (OperationEnum.contains(el)){
        operationEnum = OperationEnum.byCode(el).get();
      } else {
        value = new BigDecimal(el);
      }
      if ((operationEnum != null && value != null) || list.indexOf(el) == 0) {
        result = operationMap.getOperation(MoreObjects.firstNonNull(operationEnum, OperationEnum.ADD)).doOperation(result, value);
        operationEnum = null;
        value = null;
      }

    }
    return result;
  }
}

