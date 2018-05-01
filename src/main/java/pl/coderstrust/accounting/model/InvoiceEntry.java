package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public class InvoiceEntry {

  private final String description;
  private final BigDecimal price;
  private final Vat vat;

  public InvoiceEntry(String description, BigDecimal price, Vat vat) {

    this.description = description;
    this.price = price;
    this.vat = vat;
  }


  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Vat getVat() {
    return vat;
  }
}
