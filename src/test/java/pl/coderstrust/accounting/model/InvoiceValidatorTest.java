package pl.coderstrust.accounting.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InvoiceValidatorTest {

  @Test(expected = InvoiceValidatorException.class)
  public void shouldCheckIfFieldCompanyNameFilled() throws InvoiceValidatorException {

    Company testCompany = new Company("name", 1010101010, "streetAndNumber", "00-000", "location");

    InvoiceEntry entry = new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REDUCED1);

    List<InvoiceEntry> entries = new ArrayList<>();

    entries.add(entry);

    Invoice testInvoice = new Invoice(-9, null, LocalDate.of(2019, Month.JANUARY, 1), testCompany, testCompany, entries);

    InvoiceValidator.validate(testInvoice);
  }

}