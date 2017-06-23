package com.mcs.calc.common;


public class CalcApplicationException extends Exception{

  public CalcApplicationException(String msg) {
    super(msg);
  }

  public CalcApplicationException(final Throwable e) {
    super(e);
  }
}
