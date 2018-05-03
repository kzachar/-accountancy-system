package pl.coderstrust.accounting.model;

public class InvoiceEntryValidator {

  public static void validate(InvoiceEntry invoiceEntry) throws InvoiceEntryValidatorException {

    if (invoiceEntry.getDescription() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Description field not empty");
    }

    if (invoiceEntry.getPrice() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Price field not empty");
    }

    if (invoiceEntry.getVat() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Vat field not empty");
    }
  }
}