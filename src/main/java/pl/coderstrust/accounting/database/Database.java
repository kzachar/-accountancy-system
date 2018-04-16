package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

public interface Database {

  void saveInvoice(Invoice invoice);

  Collection<Invoice> getInvoice();

  void updateInvoice(Invoice invoice);

  void removeInvoiceById(int id);

}
