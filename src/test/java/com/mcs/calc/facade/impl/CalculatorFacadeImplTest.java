package com.mcs.calc.facade.impl;

import com.mcs.calc.TestUtils;
import com.mcs.calc.common.CalcApplicationException;
import com.mcs.calc.common.StringOperationMalformedException;
import com.mcs.calc.service.CalculatorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorFacadeImplTest {

  @Mock
  CalculatorService calcServiceMock;

  private CalculatorFacadeImpl calculatorFacade;

  @Before
  public void setUp() throws Exception {
    calculatorFacade = new CalculatorFacadeImpl(calcServiceMock);
  }

  @After
  public void tearDown() throws Exception {
    calculatorFacade = null;
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testStringOperation() throws Exception {
    when(calcServiceMock.processStringOperation(any(BigDecimal.class), anyListOf(String.class))).thenReturn(TestUtils.TWO_POINT_FIVE);
    calculatorFacade.stringOperation(TestUtils.OP_RES_TWO_POINT_FIVE);
    verify(calcServiceMock, times(1)).processStringOperation(any(BigDecimal.class), anyListOf(String.class));
  }

  @SuppressWarnings("unchecked")
  @Test(expected = StringOperationMalformedException.class)
  public void testStringOperationRaisingMalformedException() throws Exception {
    when(calcServiceMock.processStringOperation(any(BigDecimal.class), anyListOf(String.class))).thenThrow(NumberFormatException.class);
    calculatorFacade.stringOperation(TestUtils.OP_MALFORMED);
  }

  @Test
  public void testAddition() throws Exception {
    when(calcServiceMock.addition(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TEN);
    calculatorFacade.addition(TestUtils.FIVE, TestUtils.FIVE);
    verify(calcServiceMock, times(1)).addition(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testSubstraction() throws Exception {
    when(calcServiceMock.substraction(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.ZERO);
    calculatorFacade.substraction(TestUtils.FIVE, TestUtils.FIVE);
    verify(calcServiceMock, times(1)).substraction(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testMultiplication() throws Exception {
    when(calcServiceMock.multiplication(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TWENTYFIVE);
    calculatorFacade.multiplication(TestUtils.FIVE, TestUtils.FIVE);
    verify(calcServiceMock, times(1)).multiplication(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testDivision() throws Exception {
    when(calcServiceMock.division(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.ONE);
    calculatorFacade.division(TestUtils.FIVE, TestUtils.FIVE);
    verify(calcServiceMock, times(1)).division(any(BigDecimal.class), any(BigDecimal.class));
  }

  @SuppressWarnings("unchecked")
  @Test(expected = CalcApplicationException.class)
  public void testDivisionByZero() throws Exception {
    when(calcServiceMock.division(any(BigDecimal.class), eq(BigDecimal.ZERO))).thenThrow(ArithmeticException.class);
    calculatorFacade.division(TestUtils.FIVE, TestUtils.ZERO);
    verify(calcServiceMock, times(1)).division(any(BigDecimal.class), any(BigDecimal.class));
  }

}