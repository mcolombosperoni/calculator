package com.mcs.calc.dao;

import com.mcs.calc.TestUtils;
import com.mcs.calc.dao.impl.AddOperationImpl;
import com.mcs.calc.dao.impl.DivOperationImpl;
import com.mcs.calc.dao.impl.MultOperationImpl;
import com.mcs.calc.dao.impl.SubOperationImpl;
import org.junit.*;

import java.math.BigDecimal;

public class OperationTest {

  private AddOperationImpl addDao;
  private SubOperationImpl subDao;
  private MultOperationImpl multDao;
  private DivOperationImpl divDao;

  @Before
  public void setUp() throws Exception {
    addDao = new AddOperationImpl();
    subDao = new SubOperationImpl();
    multDao = new MultOperationImpl();
    divDao = new DivOperationImpl();
  }

  @After
  public void tearDown() throws Exception {
    addDao = null;
    subDao = null;
    multDao = null;
    divDao = null;
  }

  @Test
  public void testDoOperationAdd() throws Exception {
    //given
    final BigDecimal five = TestUtils.FIVE;
    //when
    final BigDecimal result = addDao.doOperation(five, five);
    //then
    Assert.assertTrue(result.compareTo(TestUtils.TEN) == 0);
  }

  @Test
  public void testDoOperationSub() throws Exception {
    //given
    final BigDecimal five = TestUtils.FIVE;
    //when
    final BigDecimal result = subDao.doOperation(five, five);
    //then
    Assert.assertTrue(result.compareTo(TestUtils.ZERO) == 0);
  }

  @Test
  public void testDoOperationMult() throws Exception {
    //given
    final BigDecimal five = TestUtils.FIVE;
    final BigDecimal two = TestUtils.TWO;
    //when
    final BigDecimal result = multDao.doOperation(five, two);
    //then
    Assert.assertTrue(result.compareTo(TestUtils.TEN) == 0);
  }

  @Test
  public void testDoOperationDiv() throws Exception {
    //given
    final BigDecimal ten = BigDecimal.TEN;
    final BigDecimal two = TestUtils.TWO;
    //when
    final BigDecimal result = divDao.doOperation(ten, two);
    //then
    Assert.assertTrue(result.compareTo(TestUtils.FIVE) == 0);
  }

  @Test(expected = ArithmeticException.class)
  public void testDoOperationDivByZero() throws Exception {
    //given
    final BigDecimal ten = TestUtils.TEN;
    final BigDecimal zero = TestUtils.ZERO;
    //when
    divDao.doOperation(ten, zero);
    //then --> ExpectedException
  }
}