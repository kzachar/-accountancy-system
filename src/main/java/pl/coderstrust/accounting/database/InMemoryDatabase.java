package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private int id = 0;

  @Override
  public int saveInvoice(Invoice invoice) {
    invoices
        .put(new Integer(++id), new Invoice(id, invoice.getIdentifier(), invoice.getIssuedDate(),
            invoice.getBuyer(), invoice.getSeller(), invoice.getEntries()));
    return id;
  }

  @Override
  public Collection<Invoice> getInvoices() {
    return invoices.values();
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);

  }

  @Override
  public void removeInvoice(int id) {
    invoices.remove(id);
  }

  @Override
  public Invoice get(int id) {
    return invoices.get(id);
  }
}
