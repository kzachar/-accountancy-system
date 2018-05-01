package pl.coderstrust.accounting.model;

import java.time.LocalDate;

public class InvoiceValidator {

  public static void validate(Invoice invoice) throws InvoiceValidatorException {
    if (invoice.getId() < 0) {
      throw new InvoiceValidatorException("Expected Id to be greater than 0, got: " + String.valueOf(invoice.getId()));
    }

    if (invoice.getId() == null) {
      throw new InvoiceValidatorException("Expected Id filled up");
    }

    if (invoice.getIssuedDate().isAfter(LocalDate.now())) {
      throw new InvoiceValidatorException("Expected issue date not greater than today date, got: " + String.valueOf(invoice.getIssuedDate()));
    }

    if (invoice.getIssuedDate() == null) {
      throw new InvoiceValidatorException("Expected issue date field filled up");
    }

    if (invoice.getIdentifier() == null) {
      throw new InvoiceValidatorException("Expected invoice number field filled up, got: " + String.valueOf(invoice.getIdentifier()));
    }

    if (invoice.getEntries() == null) {
      throw new InvoiceValidatorException("Expected invoice entries field filled up, got: " + String.valueOf(invoice.getEntries()));
    }

    if (invoice.getBuyer() == null) {
      throw new InvoiceValidatorException("Expected buyer field filled up");
    }

    if (invoice.getSeller() == null) {
      throw new InvoiceValidatorException("Expected seller field filled up");
    }
  }
}