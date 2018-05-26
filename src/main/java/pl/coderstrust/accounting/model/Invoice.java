package pl.coderstrust.accounting.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Invoice {

  private Integer id;
  private String identifier;
  private LocalDate issuedDate;
  private Company buyer;
  private Company seller;
  private List<InvoiceEntry> entries;

  @JsonCreator
  public Invoice(@JsonProperty("id") Integer id, @JsonProperty("identifier") String identifier,
      @JsonProperty("issuedDate") LocalDate issuedDate, @JsonProperty("buyer") Company buyer,
      @JsonProperty("seller") Company seller, @JsonProperty("entries") List<InvoiceEntry> entries) {
    this.id = id;
    this.identifier = identifier;
    this.issuedDate = issuedDate;
    this.buyer = buyer;
    this.seller = seller;
    this.entries = entries;
  }

  @Override
  public String toString() {
    return "Invoice{"
        + "id=" + id
        + ", identifier='" + identifier + '\''
        + ", issuedDate=" + issuedDate
        + ", buyer=" + buyer
        + ", seller=" + seller
        + ", entries=" + entries
        + '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Invoice invoice = (Invoice) obj;
    return Objects.equals(id, invoice.id)
        && Objects.equals(identifier, invoice.identifier)
        && Objects.equals(issuedDate, invoice.issuedDate)
        && Objects.equals(buyer, invoice.buyer)
        && Objects.equals(seller, invoice.seller)
        && Objects.equals(entries, invoice.entries);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, identifier, issuedDate, buyer, seller, entries);
  }

  public Invoice() {
    // Left empty constructor for Jackson
  }

  public Invoice(String identifier, LocalDate issuedDate, Company buyer, Company seller,
      List<InvoiceEntry> entries) {
    this(null, identifier, issuedDate, buyer, seller, entries);
  }

  public Integer getId() {
    return id;
  }

  public String getIdentifier() {
    return identifier;
  }

  public LocalDate getIssuedDate() {
    return issuedDate;
  }

  public Company getBuyer() {
    return buyer;
  }

  public Company getSeller() {
    return seller;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }
}