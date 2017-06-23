package com.mcs.calc.dto;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ResultDto {

  private BigDecimal result;
  private ArrayList<String> operations;
  private boolean exit = false;

  public ResultDto() {
  }

  public ResultDto(BigDecimal result, ArrayList<String> operations) {
    this.result = result;
    this.operations = operations;
  }

  public BigDecimal getResult() {
    return result;
  }

  public void setResult(BigDecimal result) {
    this.result = result;
  }

  public ArrayList<String> getOperations() {
    return Lists.newArrayList(operations);
  }

  public void setOperations(ArrayList<String> operations) {
    this.operations = operations;
  }

  public void setExit(boolean exit) {
    this.exit = exit;
  }

  public boolean isExit() {
    return exit;
  }
}
