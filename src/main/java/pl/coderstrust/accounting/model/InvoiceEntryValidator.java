package pl.coderstrust.accounting.model;

public class InvoiceEntryValidator {

  public static void validate(InvoiceEntry invoiceEntry) throws InvoiceEntryValidatorException {

    if (invoiceEntry.getDescription() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Description field filled up");
    }

    if (invoiceEntry.getPrice() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Price field filled up");
    }

    if (invoiceEntry.getVat() == null) {
      throw new InvoiceEntryValidatorException(
          "Expected Vat field filled up");
    }
  }
}