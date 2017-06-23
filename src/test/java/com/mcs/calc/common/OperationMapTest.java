package com.mcs.calc.common;

import com.mcs.calc.dao.Operation;
import com.mcs.calc.dao.impl.AddOperationImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class OperationMapTest {

  private OperationMap operationMap;

  @Before
  public void setUp() throws Exception {
    operationMap = new OperationMap();
    operationMap.initOperationMap();
  }

  @After
  public void tearDown() throws Exception {
    operationMap = null;
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetMap() throws Exception {
    final Map<OperationEnum, Operation> map = operationMap.getMap();
    map.put(OperationEnum.ADD, (number1, number2) -> null);
  }

  @Test
  public void testContains() throws Exception {
    //given
    String code = OperationEnum.ADD.getCode();
    //when
    final boolean contains = operationMap.contains(code);
    //then
    Assert.assertTrue(contains);
  }

  @Test
  public void testGetOperation() throws Exception {
    //given
    OperationEnum op = OperationEnum.ADD;
    //when
    final Operation operation = operationMap.getOperation(op);
    //then
    Assert.assertTrue(operation instanceof AddOperationImpl);
  }

  @Test
  public void testGetOperationByCode() throws Exception {
    //given
    String code = OperationEnum.ADD.getCode();
    //when
    final Operation operation = operationMap.getOperationByCode(code);
    //then
    Assert.assertTrue(operation instanceof AddOperationImpl);
  }

}