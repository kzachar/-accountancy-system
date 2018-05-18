package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

  private final String description;
  private final BigDecimal price;
  private final Vat vat;

  @JsonCreator
  public InvoiceEntry(@JsonProperty("description") String description, @JsonProperty("price") BigDecimal price, @JsonProperty("vat") Vat vat) {
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
        &&
        Objects.equals(price, that.price)
        &&
        vat == that.vat;
  }
}