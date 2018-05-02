package pl.coderstrust.accounting.model;

import org.junit.Test;

import java.math.BigDecimal;

public class InvoiceEntryValidatorTest {

  @Test(expected = InvoiceEntryValidatorException.class)
  public void shouldCheckIfDescriptionFieldIsNotNull() throws InvoiceEntryValidatorException {

    InvoiceEntry testInvoiceEntry = new InvoiceEntry(null, BigDecimal.TEN, Vat.REGULAR);

    InvoiceEntryValidator.validate(testInvoiceEntry);
  }

  @Test(expected = InvoiceEntryValidatorException.class)
  public void shouldCheckIfPriceFieldIsNotNull() throws InvoiceEntryValidatorException {

    InvoiceEntry testInvoiceEntry = new InvoiceEntry("description", null, Vat.REGULAR);

    InvoiceEntryValidator.validate(testInvoiceEntry);
  }

  @Test(expected = InvoiceEntryValidatorException.class)
  public void shouldCheckIfVatFieldIsNotNull() throws InvoiceEntryValidatorException {

    InvoiceEntry testInvoiceEntry = new InvoiceEntry("description", BigDecimal.TEN, null);

    InvoiceEntryValidator.validate(testInvoiceEntry);
  }
}