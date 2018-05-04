package pl.coderstrust.accounting.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

  private final String description;
  private final BigDecimal price;
  private final Vat vat;

  public InvoiceEntry(String description, BigDecimal price, Vat vat) {
    this.description = description;
    this.price = price;
    this.vat = vat;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    InvoiceEntry that = (InvoiceEntry) obj;
    return Objects.equals(description, that.description)
        && Objects.equals(price, that.price)
        && vat == that.vat;
  }
}
