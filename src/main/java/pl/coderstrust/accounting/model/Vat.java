package pl.coderstrust.accounting.model;

public enum Vat {REGULAR(23), REDUCED1(8), REDUCED2(5);

  private final int rate;

  Vat(int rate) {
    this.rate = rate;
  }

  public int getValue() {
    return rate;
  }

}
