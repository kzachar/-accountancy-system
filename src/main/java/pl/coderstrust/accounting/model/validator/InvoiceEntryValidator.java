package pl.coderstrust.accounting.model.validator;

import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class InvoiceEntryValidator {

  public Collection<String> validate(InvoiceEntry invoiceEntry) {
    List<String> validationExceptions = new LinkedList<>();
    if (invoiceEntry.getDescription() == null) {
      validationExceptions.add("Expected not empty description ");
    }

    if (invoiceEntry.getPrice() == null) {
      validationExceptions.add("Expected not empty price");
    }

    if (invoiceEntry.getVat() == null) {
      validationExceptions.add("Expected not empty vat");
    }
    return validationExceptions;
  }
}