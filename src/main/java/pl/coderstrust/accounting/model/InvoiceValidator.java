package pl.coderstrust.accounting.model;

import java.time.LocalDate;

public class InvoiceValidator {

  public static void validate(Invoice invoice) throws InvoiceValidatorException {

    if (invoice.getId() == null) {
      throw new InvoiceValidatorException("Expected Id field not empty");
    }

    if (invoice.getId() < 0) {
      throw new InvoiceValidatorException(
          "Expected Id to be greater than 0, got: " + String.valueOf(invoice.getId()));
    }

    if (invoice.getIssuedDate() == null) {
      throw new InvoiceValidatorException("Expected issue date field not empty");
    }

    if (invoice.getIssuedDate().isAfter(LocalDate.now())) {
      throw new InvoiceValidatorException(
          "Expected issue date not greater than today date, got: " + String
              .valueOf(invoice.getIssuedDate()));
    }

    if (invoice.getIdentifier() == null) {
      throw new InvoiceValidatorException("Expected invoice number field not empty, got: " + String
          .valueOf(invoice.getIdentifier()));
    }

    if (invoice.getEntries() == null) {
      throw new InvoiceValidatorException(
          "Expected invoice entries field not empty, got: " + String.valueOf(invoice.getEntries()));
    }

    if (invoice.getBuyer() == null) {
      throw new InvoiceValidatorException("Expected buyer field not empty");
    }

    if (invoice.getSeller() == null) {
      throw new InvoiceValidatorException("Expected seller field not empty");
    }


  }
}