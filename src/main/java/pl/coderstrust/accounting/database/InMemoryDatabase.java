package pl.coderstrust.accounting.database;

import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();

  private int id = 0;

  @Override
  public int saveInvoice(Invoice invoice) {
    invoices
        .put(++id, new Invoice(id, invoice.getIdentifier(), invoice.getIssuedDate(),//id
            invoice.getBuyer(), invoice.getSeller(), invoice.getEntries()));
    return id;
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    if (!invoices.containsKey(invoice.getId())) {
      throw new IllegalArgumentException(
          "The invoice with given ID does not exist: " + invoice.getId());
    }
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

  @Override
  public Collection<Invoice> find(Invoice searchParams, LocalDate issuedDateFrom,
      LocalDate issuedDateTo) {
    Set<Invoice> result = new HashSet<>();
    for (Invoice invoice : invoices.values()) {
      if (searchParams != null) {
        if (searchParams.getId() != null) {
          if (searchParams.getId().equals(invoice.getId())) {
            result.add(invoice);
          }
        }
        if (searchParams.getIdentifier() != null) {
          if (searchParams.getIdentifier().equals(invoice.getIdentifier())) {
            result.add(invoice);
          }
        }
        if (searchParams.getBuyer() != null) {
          if (searchParams.getBuyer().equals(invoice.getBuyer())) {
            result.add(invoice);
          }
        }
        if (searchParams.getSeller() != null) {
          if (searchParams.getSeller().equals(invoice.getSeller())) {
            result.add(invoice);
          }
        }
        if (searchParams.getIssuedDate() != null) {
          if (searchParams.getIssuedDate().isEqual(invoice.getIssuedDate())) {
            result.add(invoice);
          }
        }
        if (searchParams.getEntries() != null) {
          boolean found = false;
          for (InvoiceEntry entry : searchParams.getEntries()) {
            if (!found && invoice.getEntries().contains(entry)) {
              result.add(invoice);
              found = true;
            }
          }
        }
      }
      if (issuedDateFrom != null && issuedDateTo == null) {
        if (issuedDateFrom.isBefore(invoice.getIssuedDate()) || issuedDateFrom
            .isEqual(invoice.getIssuedDate())) {
          result.add(invoice);
        }
      }
      if (issuedDateTo != null && issuedDateFrom == null) {
        if (issuedDateTo.isAfter(invoice.getIssuedDate()) || issuedDateTo
            .isEqual(invoice.getIssuedDate())) {
          result.add(invoice);
        }
      }
      if (issuedDateTo != null && issuedDateFrom != null) {
        if ((issuedDateTo.isAfter(invoice.getIssuedDate()) || issuedDateTo
            .isEqual(invoice.getIssuedDate())) && (issuedDateFrom.isBefore(invoice.getIssuedDate())
            || issuedDateFrom.isEqual(invoice.getIssuedDate()))) {
          result.add(invoice);
        }
      }
    }
    return result;
  }

  @Override
  public Collection<Invoice> getAll() {
    return invoices.values();
  }
}
