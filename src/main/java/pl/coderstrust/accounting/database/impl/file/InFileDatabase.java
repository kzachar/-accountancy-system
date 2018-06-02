package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.helpers.FileInvoiceHelper;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InFileDatabase implements Database {

  private String databaseFilePath;
  private String idFilePath;
  private int id = 0;

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
  }

  @Override
  public Invoice get(int id) {
    return null;
  }

  @Override
  public Collection<Invoice> find(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    Set<Invoice> resultSchearching = new HashSet<>();
    List<Invoice> invoices = null;
    try {
      invoices = FileInvoiceHelper.readInvoicesFromFile(databaseFilePath);
    } catch (IOException ioex) {
      ioex.printStackTrace();
    }
    if (invoices != null) {
      resultSchearching.addAll(findbyDateRange(invoices, normalaizeDateFrom(issuedDateFrom),
          normalaizeDateTo(issuedDateTo)));
      resultSchearching = findById(searchParams.getId(), resultSchearching);
      resultSchearching = findByIdentifier(searchParams.getIdentifier(), resultSchearching);
      resultSchearching = findByIssuedDate(searchParams.getIssuedDate(), resultSchearching);
      resultSchearching = findByBuyer(searchParams.getBuyer(), resultSchearching);
      resultSchearching = findBySeller(searchParams.getSeller(), resultSchearching);
      resultSchearching = findByEntries(searchParams.getEntries(), resultSchearching);
    }
    return resultSchearching;
  }

  private LocalDate normalaizeDateFrom(LocalDate issuedDateFrom) {
    return issuedDateFrom == null ? LocalDate.MIN : issuedDateFrom;
  }

  private LocalDate normalaizeDateTo(LocalDate issuedDateTo) {
    return issuedDateTo == null ? LocalDate.MAX : issuedDateTo;
  }

  private Set<Invoice> findById(Integer id, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> id.equals(invoice.getId()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Set<Invoice> findByIdentifier(String identifier, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> identifier.equals(invoice.getIdentifier()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Set<Invoice> findByIssuedDate(LocalDate issuedDate, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> issuedDate.equals(invoice.getIssuedDate()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Set<Invoice> findByBuyer(Company buyer, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> buyer.equals(invoice.getBuyer()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Set<Invoice> findBySeller(Company seller, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> seller.equals(invoice.getSeller()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Set<Invoice> findByEntries(List<InvoiceEntry> entries, Set<Invoice> resultSchearching) {
    return resultSchearching.stream()
        .filter(invoice -> entries.equals(invoice.getEntries()))
        .collect(Collectors.toCollection(HashSet::new));
  }

  private Collection<Invoice> findbyDateRange(List<Invoice> inputList, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    return inputList.stream()

        .filter(invoice -> invoice.getIssuedDate().isAfter(issuedDateFrom))
        .filter(invoice -> invoice.getIssuedDate().isBefore(issuedDateTo))
        .collect(Collectors.toCollection(ArrayList::new));

  }

  @Override
  public Collection<Invoice> getAll() {
    return null;
  }
}

