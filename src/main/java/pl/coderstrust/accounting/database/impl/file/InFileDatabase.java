package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InFileDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private int id = 0;

  @Override
  public int saveInvoice(Invoice invoice) {
    invoices.put(++id, new Invoice(id, invoice.getIdentifier(), invoice.getIssuedDate(),
        invoice.getBuyer(), invoice.getSeller(), invoice.getEntries()));
    return id;
  }

  @Override
  public void updateInvoice(Invoice invoice) {

  }

  @Override
  public void removeInvoice(int id) {

  }

  @Override
  public Invoice get(int id) {
    return null;
  }

  @Override
  public Collection<Invoice> find(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    return null;
  }
}
