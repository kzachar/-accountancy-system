package pl.coderstrust.accounting.model;

import org.junit.Test;
import pl.coderstrust.accounting.helpers.InvoiceHelper;

import java.time.LocalDate;
import java.time.Month;

public class InvoiceValidatorTest {

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfIdentifierFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(-9, "Sample Identifier", LocalDate.of(2017, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(),
        InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfIdFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(null, "Sample Identifier",
        LocalDate.of(2017, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(),
        InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfIssueDateFieldIsNotLaterThanToday() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, "Sample Identifier", LocalDate.of(2019, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(),
        InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfIssueDateFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, "Sample Identifier", null,
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(),
        InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfIdFieldGreaterThenOrEqualZero() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, null, LocalDate.of(2017, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(),
        InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfBuyerFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, "Sample Identifier", LocalDate.of(2017, Month.JANUARY, 1),
        null, InvoiceHelper.getSampleSeller(), InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfSellerFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, "Sample Identifier", LocalDate.of(2017, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), null, InvoiceHelper.getSampleListOfEntries());

    InvoiceValidator.validate(testInvoice);
  }

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfEntriesFieldIsNotNull() throws InvoiceValidatorException {

    Invoice testInvoice = new Invoice(9, "Sample Identifier", LocalDate.of(2017, Month.JANUARY, 1),
        InvoiceHelper.getSampleBuyer(), InvoiceHelper.getSampleSeller(), null);

    InvoiceValidator.validate(testInvoice);
  }
}