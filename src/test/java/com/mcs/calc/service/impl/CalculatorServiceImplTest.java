package com.mcs.calc.service.impl;

import com.mcs.calc.TestUtils;
import com.mcs.calc.common.OperationEnum;
import com.mcs.calc.common.OperationMap;
import com.mcs.calc.dao.impl.AddOperationImpl;
import com.mcs.calc.dao.impl.DivOperationImpl;
import com.mcs.calc.dao.impl.MultOperationImpl;
import com.mcs.calc.dao.impl.SubOperationImpl;
import com.mcs.calc.service.CalculatorService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class CalculatorServiceImplTest {
  private CalculatorServiceImpl calculatorService;

  private AddOperationImpl addMock;
  private SubOperationImpl subMock;
  private MultOperationImpl multMock;
  private DivOperationImpl divMock;
  private OperationMap mapMock;

  @Before
  public void setUp() throws Exception {
    addMock = Mockito.mock(AddOperationImpl.class);
    subMock = Mockito.mock(SubOperationImpl.class);
    multMock = Mockito.mock(MultOperationImpl.class);
    divMock = Mockito.mock(DivOperationImpl.class);
    mapMock = Mockito.mock(OperationMap.class);
    mapMock.initOperationMap(addMock,subMock,multMock,divMock);
    calculatorService = new CalculatorServiceImpl(mapMock);
  }

  @After
  public void tearDown() throws Exception {
    calculatorService = null;
  }

  @Test
  public void testGetInstanceReturnAlwaysSameInstance() throws Exception {
    //given
    final CalculatorService instance1 = CalculatorServiceImpl.getInstance();
    //when
    final CalculatorService instance2 = CalculatorServiceImpl.getInstance();
    //then
    Assert.assertEquals(instance1,instance2);
  }

  @Test
  public void testProcessStringOperation() throws Exception {
    // given
    final String opResTwo = TestUtils.OP_RES_TWO_POINT_FIVE;
    final List<String> operations = Arrays.asList(opResTwo.split(" "));

    //set mock behaviour to respect input "1 + 3 / 2 * 3 - 4.5", do not test operations but method logic
    when(addMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.FOUR);
    when(mapMock.getOperation(OperationEnum.ADD)).thenReturn(addMock);
    when(divMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TWO);
    when(mapMock.getOperation(OperationEnum.DIV)).thenReturn(divMock);
    when(multMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.SIX);
    when(mapMock.getOperation(OperationEnum.MULT)).thenReturn(multMock);
    when(subMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TWO_POINT_FIVE);
    when(mapMock.getOperation(OperationEnum.SUB)).thenReturn(subMock);

    // when
    final BigDecimal result = calculatorService.processStringOperation(BigDecimal.ZERO, operations);

    // then
    Assert.assertTrue(result.compareTo(TestUtils.TWO_POINT_FIVE) == 0);
    //two times because consider to start by method argument result
    verify(addMock, times(2)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
    verify(divMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
    verify(multMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
    verify(subMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));


  }

  //NOTE: in these cases doesn't matter to check the operations but only the correctness of service method behavior, the operations are just tested in theirs tests classes

  @Test
  public void testAddition() throws Exception {
    //given
    when(addMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TEN);
    when(mapMock.getOperation(OperationEnum.ADD)).thenReturn(addMock);

    //when
    calculatorService.addition(TestUtils.TWO, TestUtils.FIVE);

    //then
    verify(addMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testSubstraction() throws Exception {
    //given
    when(subMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TEN);
    when(mapMock.getOperation(OperationEnum.SUB)).thenReturn(subMock);

    //when
    calculatorService.substraction(TestUtils.TWO, TestUtils.FIVE);

    //then
    verify(subMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testMultiplication() throws Exception {
    //given
    when(multMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TEN);
    when(mapMock.getOperation(OperationEnum.MULT)).thenReturn(multMock);

    //when
    calculatorService.multiplication(TestUtils.TWO, TestUtils.FIVE);

    //then
    verify(multMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
  }

  @Test
  public void testDivision() throws Exception {
    //given
    when(divMock.doOperation(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TestUtils.TEN);
    when(mapMock.getOperation(OperationEnum.DIV)).thenReturn(divMock);

    //when
    calculatorService.division(TestUtils.TWO, TestUtils.FIVE);

    //then
    verify(divMock, times(1)).doOperation(any(BigDecimal.class), any(BigDecimal.class));
  }


}