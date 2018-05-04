package pl.coderstrust.accounting.model.validator;

import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.validator.exception.InvoiceEntryValidationException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class InvoiceEntryValidator {

  public Collection<InvoiceEntryValidationException> validate(InvoiceEntry invoiceEntry) {
    List<InvoiceEntryValidationException> validationExceptions = new LinkedList<>();
    if (invoiceEntry.getDescription() == null) {
      validationExceptions.add(new InvoiceEntryValidationException(
          "Expected not empty description "));
    }

    if (invoiceEntry.getPrice() == null) {
      validationExceptions.add(new InvoiceEntryValidationException(
          "Expected not empty price"));
    }

    if (invoiceEntry.getVat() == null) {
      validationExceptions.add(new InvoiceEntryValidationException(
          "Expected not empty vat"));
    }
    return validationExceptions;
  }
}