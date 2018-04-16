package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.database.Database;

import java.util.Arrays;
import java.util.Collection;

public class InvoiceBook {

  private Database database;

  public InvoiceBook(Database database){
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) {
   database.saveInvoice(invoice);
  }

  public Collection<Invoice> getInvoice(){
    return Arrays.asList();
  }

  public void updateInvoices(Invoice invoice){}

  public void removeInvoice(int id){}

}
