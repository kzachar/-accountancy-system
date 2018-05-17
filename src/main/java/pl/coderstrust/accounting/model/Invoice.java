package pl.coderstrust.accounting.model;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

  private Integer id;
  private String identifier;
  private LocalDate issuedDate;
  private Company buyer;
  private Company seller;
  private List<InvoiceEntry> entries;

  // Left empty constructor for Jackson
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
}