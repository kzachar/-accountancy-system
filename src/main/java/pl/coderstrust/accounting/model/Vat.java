package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;


public enum Vat {
  REGULAR(23), REDUCED1(8), REDUCED2(5), ZERO(0);

  private final int rate;

  @JsonCreator
  public static Vat fromValue(int value) {
    return Arrays.stream(Vat.values())
        .filter(status -> status.getValue() == value)
        .findFirst()
        .orElse(ZERO);
  }

  @JsonValue
  public int getValue() {
    return rate;
  }

  Vat(int rate) {
    this.rate = rate;
  }
}