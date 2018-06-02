package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.helpers.FileHelper;
import pl.coderstrust.accounting.helpers.FileInvoiceHelper;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public class InFileDatabase implements Database {

  private String databaseFilePath;
  private String idFilePath;

  public InFileDatabase(String databaseFilePath, String idFilePath) {
    if (databaseFilePath == null || "".equals(databaseFilePath)) {
      throw new IllegalArgumentException("Database filepath can't be empty");
    }
    if (idFilePath == null || "".equals(idFilePath)) {
      throw new IllegalArgumentException("ID filepath can't be empty");
    }
    this.databaseFilePath = databaseFilePath;
    this.idFilePath = idFilePath;
  }

  @Override
  public int saveInvoice(Invoice invoice) {
    int id = 0;
    try {
      id = FileInvoiceHelper.getAndIncrementLastId(idFilePath);
    } catch (IOException ioex) {
      throw new RuntimeException("IOException when opening idFile " + idFilePath, ioex);
    }
    Invoice invoiceToWrite = new Invoice(id, invoice.getIdentifier(), invoice.getIssuedDate(),
        invoice.getBuyer(), invoice.getSeller(), invoice.getEntries());
    try {
      FileInvoiceHelper.writeInvoiceToFile(invoiceToWrite, databaseFilePath);
    } catch (IOException ieox) {
      throw new RuntimeException(ieox);
    }
    return id;
  }

  @Override
  public void updateInvoice(Invoice invoice) {
  }

  @Override
  public void removeInvoice(int id) {
    try {
      FileHelper.writeToFile(FileHelper.removeInvoiceFromFile(databaseFilePath, id), databaseFilePath);
    } catch (IOException ieox) {
      throw new RuntimeException(ieox);
    }
  }

  @Override
  public Invoice get(int id) {
    Invoice invoiceTaken = null;
    try {
      invoiceTaken = FileHelper.getInvoiceFromFileById(databaseFilePath, id);
    } catch (IOException ieox) {
      throw new RuntimeException(ieox);
    }
    return invoiceTaken;
  }

  @Override
  public Collection<Invoice> find(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    return null;
  }
}
