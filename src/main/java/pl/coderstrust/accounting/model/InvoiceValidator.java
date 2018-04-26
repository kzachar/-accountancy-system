package pl.coderstrust.accounting.model;

import java.time.LocalDate;

public class InvoiceValidator {


  public void validate(Invoice invoice) throws InvoiceValidationException {
    if (invoice.getId() <= 0) {
      throw new InvoiceValidationException("Expected Id to be greater than 0, got: " + String.valueOf(invoice.getId()));
    }

    if (invoice.getId() == null) {
      throw new InvoiceValidationException("Expected Id filled up");
    }

    if (invoice.getIssuedDate().isAfter(LocalDate.now())) {
      throw new InvoiceValidationException("Expected issue date not greater than today date, got: " + String.valueOf(invoice.getIssuedDate()));
    }

    if (invoice.getIssuedDate() == null) {
      throw new InvoiceValidationException("Expected issue date field filled up");
    }

    if (invoice.getIdentifier() == null) {
      throw new InvoiceValidationException("Expected invoice number field filled up, got: " + String.valueOf(invoice.getIdentifier()));
    }

    if (invoice.getEntries() == null) {
      throw new InvoiceValidationException("Expected invoice entries field filled up, got: " + String.valueOf(invoice.getEntries()));
    }

    if (invoice.getBuyer() == null) {
      throw new InvoiceValidationException("Expected buyer field filled up");
    }

    if (invoice.getSeller() == null) {
      throw new InvoiceValidationException("Expected seller field filled up");
    }
  }
}