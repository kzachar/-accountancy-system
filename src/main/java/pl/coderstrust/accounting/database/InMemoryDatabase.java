package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.database.Database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();

  @Override
  public void saveInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
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
}
