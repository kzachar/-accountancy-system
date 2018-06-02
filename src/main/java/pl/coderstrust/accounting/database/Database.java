package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;

public interface Database {

  int saveInvoice(Invoice invoice);

  void updateInvoice(Invoice invoice);

  void removeInvoice(int id);

  Invoice get(int id);

  Collection<Invoice> find(Invoice searchParams, LocalDate issuedDateFrom, LocalDate issuedDateTo);

  Collection<Invoice> getAll();
}
