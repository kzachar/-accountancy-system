package pl.coderstrust;

import java.util.HashSet;
import java.util.Set;

public class FibonacciChecker {

  Set<Long> setFibbonaci = new HashSet<>();

  public FibonacciChecker() {
    this.setFibbonaci = preCalculateSet();
  }

  public boolean isFibonacciNumber(long numer) {
    return setFibbonaci.contains(numer);
  }

  private Set<Long> preCalculateSet() {
    long f1 = 0;
    long f2 = 1;
    long f3 = 1;

    setFibbonaci.add(f1);
    setFibbonaci.add(f2);

    for (long i = 0L; i < Long.MAX_VALUE; i++) {
      if (f3 > 0) {
        f3 = f1 + f2;
        f1 = f2;
        f2 = f3;
        setFibbonaci.add(f3);
      } else {
        break;
      }
    }
    return setFibbonaci;
  }
}

