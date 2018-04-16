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

  public Integer getId() {
    return id;
  }



}
