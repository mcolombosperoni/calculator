package com.mcs.calc.controller;

import com.mcs.calc.facade.CalculatorFacade;
import com.mcs.calc.facade.impl.CalculatorFacadeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;


public class CalculatorWebController {

  public static final Logger LOGGER = LoggerFactory.getLogger(CalculatorWebController.class);
  public static CalculatorFacade calculatorFacade = CalculatorFacadeImpl.getInstance();

  public static void main(String[] args) {

    staticFileLocation("/public");

    // ROUTES
    get("/add", (req, res) -> {
      final String num1 = req.queryParams("num1");
      final String num2 = req.queryParams("num2");
      LOGGER.debug("call ADD: {} + {} ", num1, num2);
      final BigDecimal result = calculatorFacade.addition(new BigDecimal(num1), new BigDecimal(num2));
      LOGGER.debug("{} + {} = {} ", num1, num2, result);
      return result.toString();
    });

    get("/sub", (req, res) -> {
      final String num1 = req.queryParams("num1");
      final String num2 = req.queryParams("num2");
      LOGGER.debug("call SUB: {} - {} ", num1, num2);
      final BigDecimal result = calculatorFacade.substraction(new BigDecimal(num1), new BigDecimal(num2));
      LOGGER.debug("{} - {} = {} ", num1, num2, result);
      return result.toString();
    });

    get("/mult", (req, res) -> {
      final String num1 = req.queryParams("num1");
      final String num2 = req.queryParams("num2");
      LOGGER.debug("call MULT: {} * {} ", num1, num2);
      final BigDecimal result = calculatorFacade.multiplication(new BigDecimal(num1), new BigDecimal(num2));
      LOGGER.debug("{} * {} = {} ", num1, num2, result);
      return result.toString();
    });

    get("/div", (req, res) -> {
      final String num1 = req.queryParams("num1");
      final String num2 = req.queryParams("num2");
      LOGGER.debug("call DIV: {} / {} ", num1, num2);
      final BigDecimal result = calculatorFacade.division(new BigDecimal(num1), new BigDecimal(num2));
      LOGGER.debug("{} / {} = {} ", num1, num2, result);
      return result.toString();
    });

    get("/string-operation", (req, res) -> {
      final String op = req.queryParams("op");
      LOGGER.debug("call STRING-OPERATION: {} ", op);
      final BigDecimal result = calculatorFacade.stringOperation(op);
      LOGGER.debug("{} = {} ", op, result);
      return result.toString();
    });

  }

  // accessors
  public static void setCalculatorFacade(final CalculatorFacade calculatorFacade) {
    CalculatorWebController.calculatorFacade = calculatorFacade;
  }
}
