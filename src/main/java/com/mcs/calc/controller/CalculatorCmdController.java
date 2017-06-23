package com.mcs.calc.controller;

import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.common.OperationEnum;
import com.mcs.calc.common.StringOperationMalformedException;
import com.mcs.calc.dto.ResultDto;
import com.mcs.calc.facade.CalculatorFacade;
import com.mcs.calc.facade.impl.CalculatorFacadeImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class CalculatorCmdController {
  private static final CalculatorCmdController instance = new CalculatorCmdController();
  private final CalculatorFacade calculatorFacade;
  private Scanner scanner;

  private CalculatorCmdController() {
    calculatorFacade = CalculatorFacadeImpl.getInstance();
  }

  public static CalculatorCmdController getInstance(){
    return instance;
  }

  public ResultDto initCalculator() {
    scanner = new Scanner(System.in);
    scanner.useLocale(Locale.ENGLISH);
    scanner.useDelimiter("\n");
    System.out.println("Welcome to Calc 1.0!");
    System.out.println("This is the main menu");
    System.out.println("Choose your exp<b></b>ression: ");
    return new ResultDto(BigDecimal.ZERO,new ArrayList<>());
  }

  public void cleanScanner(){
    scanner = new Scanner(System.in);
    scanner.useLocale(Locale.ENGLISH);
    scanner.useDelimiter("\n");
  }

  public int chooseOperation() throws CalcApplicationException {
    System.out.println("1. Addition /n 2. Substraction/n 3. Multiplcation /n 4. Divide /n 6 Entire String /n 7 Clear");
    int chosen;
    try {
      chosen = scanner.nextInt();
    } catch (InputMismatchException e) {
      throw new CalcApplicationException(e.getMessage());
    }
    return chosen;
  }

  public ResultDto doChosenOperation(int choice, ResultDto resultDto) throws CalcApplicationException {
    BigDecimal result = resultDto.getResult();
    ArrayList<String> operations = resultDto.getOperations();
    try{
      switch (choice) {
        case 1:
          System.out.println("Insert value: ");
          BigDecimal value = scanner.nextBigDecimal();
          result = calculatorFacade.addition(result, value);
          operations.add((operations.isEmpty() ? "" : OperationEnum.ADD.getCode()) + " (" + value.toString() + ") ");
          break;
        case 2:
          System.out.println("Insert value: ");
          value = scanner.nextBigDecimal();
          result = calculatorFacade.substraction(result, value);
          operations.add((operations.isEmpty() ? "" : OperationEnum.SUB.getCode()) + " (" + value.toString() + ") ");
          break;
        case 3:
          System.out.println("Insert value: ");
          value = scanner.nextBigDecimal();
          result = calculatorFacade.multiplication(result, value);
          operations.add((operations.isEmpty() ? "" : OperationEnum.MULT.getCode()) + " (" + value.toString() + ") ");
          break;
        case 4:
          System.out.println("Insert value: ");
          value = scanner.nextBigDecimal();
          try {
            result = calculatorFacade.division(result, value);
            operations.add((operations.isEmpty() ? "" : OperationEnum.DIV.getCode()) + " (" + value.toString() + ") ");
          } catch (ArithmeticException e) {
            throw new CalcApplicationException(e.getMessage());
          }
        break;
      case 6:
        System.out.println("Insert line: ");
        System.console();
        final String operations1 = scanner.next();
        try {
          result = calculatorFacade.stringOperation(operations1.replace("=",""));
        } catch (StringOperationMalformedException e) {
          throw new CalcApplicationException(e.getMessage());
        }
        break;
      case 7:
        result = BigDecimal.ZERO;
        operations.clear();
        break;
      default:
        resultDto.setExit(true);
        break;
      }
    } catch (InputMismatchException e) {
      throw new CalcApplicationException(e.getMessage());
    }
    return new ResultDto(result,operations);
  }

  public void printAllOperations(ResultDto resultDto) {
    resultDto.getOperations().stream().forEach(System.out::print);
  }

  public void printResult(ResultDto resultDto) {
    System.out.println("= " + resultDto.getResult());
  }
}
