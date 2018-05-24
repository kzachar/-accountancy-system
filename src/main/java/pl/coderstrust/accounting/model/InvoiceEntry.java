package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceEntry {

  private String description;
  private BigDecimal price;
  private Vat vat;

  @Override
  public String toString() {
    return "InvoiceEntry{"
        + "description='" + description + '\''
        + ", price=" + price
        + ", vat=" + vat
        + '}';
  }

  public InvoiceEntry() {
  // Left empty constructor for Jackson
  }

  @JsonCreator
  public InvoiceEntry(@JsonProperty("description") String description, @JsonProperty("price") BigDecimal price, @JsonProperty("vat") Vat vat) {
    this.description = description;
    this.price = price;
    this.vat = vat;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public int hashCode() {

    return Objects.hash(description, price, vat);
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
        && Objects.equals(price, that.price)
        && vat == that.vat;
  }
}
