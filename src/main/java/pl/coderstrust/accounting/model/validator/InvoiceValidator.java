package pl.coderstrust.accounting.model.validator;

import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.validator.exception.InvoiceValidationException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class InvoiceValidator {

  private final InvoiceEntryValidator invoiceEntryValidator;

  private final CompanyValidator companyValidator;

  public InvoiceValidator(InvoiceEntryValidator invoiceEntryValidator,
      CompanyValidator companyValidator) {
    this.invoiceEntryValidator = invoiceEntryValidator;
    this.companyValidator = companyValidator;
  }

  public Collection<InvoiceValidationException> validate(Invoice invoice, boolean checkForId) {
    List<InvoiceValidationException> validationExceptions = new LinkedList<>();
    if (invoice.getId() == null && checkForId) {
      validationExceptions.add(new InvoiceValidationException("Expected not empty Id"));
    } else {
      if (invoice.getId() < 0) {
        validationExceptions.add(new InvoiceValidationException(
            "Expected Id to be greater than 0, got: " + String.valueOf(invoice.getId())));
      }
    }
    if (invoice.getIssuedDate() == null) {
      validationExceptions
          .add(new InvoiceValidationException("Expected not empty issue date"));
    } else {
      if (invoice.getIssuedDate().isAfter(LocalDate.now())) {
        validationExceptions.add(new InvoiceValidationException(
            "Expected issue date not greater than today date, got: " + String
                .valueOf(invoice.getIssuedDate())));
      }
    }
    if (invoice.getIdentifier() == null) {
      validationExceptions.add(
          new InvoiceValidationException("Expected not empty invoice identifier"));
    }
    if (invoice.getEntries() == null) {
      validationExceptions.add(new InvoiceValidationException(
          "Expected not empty invoice entries"));
    } else {
      invoice.getEntries().forEach(invoiceEntry -> invoiceEntryValidator.validate(invoiceEntry)
          .forEach(validationException -> validationExceptions.add(new InvoiceValidationException(
              "Validation of entry failed, message: " + validationException.getMessage()))));
    }
    if (invoice.getBuyer() == null) {
      validationExceptions.add(new InvoiceValidationException("Expected not empty buyer"));
    } else {
      companyValidator.validate(invoice.getBuyer())
          .forEach((validationException -> validationExceptions.add(new InvoiceValidationException(
              "Validation of buyer failed, message: " + validationException.getMessage()))));
    }
    if (invoice.getSeller() == null) {
      validationExceptions.add(new InvoiceValidationException("Expected not empty seller"));
    } else {
      companyValidator.validate(invoice.getSeller())
          .forEach((validationException -> validationExceptions.add(new InvoiceValidationException(
              "Validation of seller failed, message: " + validationException.getMessage()))));
    }
    return validationExceptions;
  }
}