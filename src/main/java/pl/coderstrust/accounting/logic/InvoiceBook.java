package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) {
    database.saveInvoice(invoice);
  }

  public void updateInvoice(Invoice invoice) {
    if (invoice == null) {
      throw new IllegalArgumentException("Invoice to update cannot be null");
    }
    if (invoice.getId() == null) {
      throw new IllegalArgumentException("Invoice to update must have a valid ID");
    }
    Invoice current = database.get(invoice.getId());
    if (current != null) {
      String identifier = invoice.getIdentifier() == null ? current.getIdentifier() : invoice
          .getIdentifier();
      LocalDate issuedDate =
          invoice.getIssuedDate() == null ? current.getIssuedDate() : invoice.getIssuedDate();
      List<InvoiceEntry> entries =
          invoice.getEntries() == null ? current.getEntries() : invoice.getEntries();
      Company buyer = invoice.getBuyer() == null ? current.getBuyer() : invoice.getBuyer();
      Company seller = invoice.getSeller() == null ? current.getSeller() : invoice.getSeller();
      database.updateInvoice(
          new Invoice(invoice.getId(), identifier, issuedDate, buyer, seller, entries));
    } else {
      throw new IllegalArgumentException(
          "Cannot update: An invoice with given ID : " + invoice.getId() + " doesn't exist");
    }
  }

  public void removeInvoice(int id) {
    if (database.get(id) != null) {
      database.removeInvoice(id);
    } else {
      throw new IllegalArgumentException(
          "Cannot remove: An invoice with given ID : " + id + " doesn't exist");
    }
  }

  public Collection<Invoice> findInvoices(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    return database.find(searchParams, issuedDateFrom, issuedDateTo);
  }

}
