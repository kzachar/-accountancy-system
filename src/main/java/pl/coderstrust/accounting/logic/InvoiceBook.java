package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) {
    database.saveInvoice(invoice);
  }

  public Collection<Invoice> getInvoices() {
    return Arrays.asList();
  }

  public void updateInvoices(Invoice invoice) {
  }

  public void removeInvoice(int id) {
    if (database.get(id) != null) {
      database.removeInvoice(id);
    } else {
      throw new IllegalArgumentException("An invoice with given ID : " + id + " doesn't exist");
    }
  }

}
