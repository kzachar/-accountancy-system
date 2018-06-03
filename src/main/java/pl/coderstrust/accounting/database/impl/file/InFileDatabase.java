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
    Set<Invoice> searchResult = new HashSet<>();
    List<Invoice> invoices = null;
    try {
      invoices = FileInvoiceHelper.readInvoicesFromFile(databaseFilePath);
    } catch (IOException ioex) {
      ioex.printStackTrace();
    }
    if (invoices != null) {
      if (searchParams != null) {
        searchResult = findById(searchParams.getId(), searchResult);
        searchResult = findByIdentifier(searchParams.getIdentifier(), searchResult);
        searchResult = findByIssuedDate(searchParams.getIssuedDate(), searchResult);
        searchResult = findByBuyer(searchParams.getBuyer(), searchResult);
        searchResult = findBySeller(searchParams.getSeller(), searchResult);
        searchResult = findByEntries(searchParams.getEntries(), searchResult);
      }
      searchResult.addAll(findbyDateRange(invoices, changeToSearchDateFrom(issuedDateFrom),
          changeToSearchDateTo(issuedDateTo)));
    }
    return searchResult;
  }

  private LocalDate changeToSearchDateFrom(LocalDate issuedDateFrom) {
    return issuedDateFrom == null ? LocalDate.MIN : issuedDateFrom;
  }

  private LocalDate changeToSearchDateTo(LocalDate issuedDateTo) {
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

