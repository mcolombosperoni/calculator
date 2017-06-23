package com.mcs.calc;

import java.math.BigDecimal;


public class TestUtils {
  public static final BigDecimal ZERO = BigDecimal.ZERO;
  public static final BigDecimal ONE = BigDecimal.ONE;
  public static final BigDecimal TWO = new BigDecimal(2);
  public static final BigDecimal TWO_POINT_FIVE = new BigDecimal(2.5);
  public static final BigDecimal FOUR = new BigDecimal(4);
  public static final BigDecimal FIVE = new BigDecimal(5);
  public static final BigDecimal SIX = new BigDecimal(6);
  public static final BigDecimal TEN = BigDecimal.TEN;
  public static BigDecimal TWENTYFIVE = new BigDecimal(25);

  public static final String OP_RES_TWO = "1 + 3 / 2 * 3 - 4.5 + 1 - 0.5";
  public static final String OP_RES_TWO_POINT_FIVE = "1 + 3 / 2 * 3 - 4.5";
  public static String OP_MALFORMED = "1 +3 |";
}
