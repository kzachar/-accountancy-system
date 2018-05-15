package pl.coderstrust.accounting.model;

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

  public Invoice() {
  }

  public Invoice(Integer id, String identifier, LocalDate issuedDate, Company buyer,
      Company seller, List<InvoiceEntry> entries) {
    this.id = id;
    this.identifier = identifier;
    this.issuedDate = issuedDate;
    this.buyer = buyer;
    this.seller = seller;
    this.entries = entries;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Invoice invoice = (Invoice) o;
    return Objects.equals(id, invoice.id) &&
        Objects.equals(identifier, invoice.identifier) &&
        Objects.equals(issuedDate, invoice.issuedDate) &&
        Objects.equals(buyer, invoice.buyer) &&
        Objects.equals(seller, invoice.seller) &&
        Objects.equals(entries, invoice.entries);
  }
}