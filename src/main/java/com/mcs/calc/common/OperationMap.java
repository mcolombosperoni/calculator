package com.mcs.calc.common;

import com.google.common.base.MoreObjects;
import com.mcs.calc.dao.Operation;
import com.mcs.calc.dao.impl.AddOperationImpl;
import com.mcs.calc.dao.impl.DivOperationImpl;
import com.mcs.calc.dao.impl.MultOperationImpl;
import com.mcs.calc.dao.impl.SubOperationImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class OperationMap {

  private static OperationMap instance = new OperationMap();
  private Map<OperationEnum, Operation> map = new HashMap<> ();

  public static OperationMap getInstance(){
    return instance;
  }

  public void initOperationMap() {
    initOperationMap(null, null, null, null);
  }

  public void initOperationMap(Operation add, Operation sub, Operation mult, Operation div) {
    map.put(OperationEnum.ADD, MoreObjects.firstNonNull(add, new AddOperationImpl()));
    map.put(OperationEnum.SUB, MoreObjects.firstNonNull(sub, new SubOperationImpl()));
    map.put(OperationEnum.MULT, MoreObjects.firstNonNull(mult, new MultOperationImpl()));
    map.put(OperationEnum.DIV, MoreObjects.firstNonNull(div, new DivOperationImpl()));
  }

  public Map<OperationEnum, Operation> getMap(){
    return Collections.unmodifiableMap(map);
  }

  public boolean contains(final String code) {
    return map.keySet().stream().anyMatch(new OperationEnumCodePredicate(code));
  }

  public Operation getOperation(final OperationEnum value) {
    return map.get(value);
  }

  public Operation getOperationByCode(final String code) {
    return map.get(OperationEnum.byCode(code).get());
  }

}
