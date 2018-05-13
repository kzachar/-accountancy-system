package pl.coderstrust.accounting.integrationTests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.coderstrust.accounting.database.InMemoryDatabase;
import pl.coderstrust.accounting.helpers.InvoiceHelper;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.validator.CompanyValidator;
import pl.coderstrust.accounting.model.validator.InvoiceEntryValidator;
import pl.coderstrust.accounting.model.validator.InvoiceValidator;

import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class InvoiceBookAndDatabaseTest {

  private InMemoryDatabase database;
  private InvoiceBook invoiceBook;
  private InvoiceValidator invoiceValidator;
  private InvoiceEntryValidator invoiceEntryValidator;
  private CompanyValidator companyValidator;

  @Before
  public void init() {
    database = new InMemoryDatabase();
    invoiceBook = new InvoiceBook(database, invoiceValidator);
    invoiceValidator = new InvoiceValidator(invoiceEntryValidator, companyValidator);
    invoiceEntryValidator = new InvoiceEntryValidator();
    companyValidator = new CompanyValidator();
  }

  @Test
  public void shouldSaveInvoiceToDatabaseViaInvoiceBook() {
    //given
    Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();
    Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId2();
    Invoice invoice3 = InvoiceHelper.getSampleInvoiceWithId3();
    Invoice invoice4 = InvoiceHelper.getSampleInvoiceWithId4();

    //when
    invoiceBook.saveInvoice(invoice1);
    invoiceBook.saveInvoice(invoice2);
    invoiceBook.saveInvoice(invoice3);
    invoiceBook.saveInvoice(invoice4);

    //then
    assertThat(database.get(1).getIdentifier(), is(invoice1.getIdentifier()));
    assertThat(database.get(2).getIdentifier(), is(invoice2.getIdentifier()));
    assertThat(database.get(3).getIdentifier(), is(invoice3.getIdentifier()));
    assertThat(database.get(4).getIdentifier(), is(invoice4.getIdentifier()));
  }

  @Test
  public void shouldIncrementIdWhenInvoiceWithNullIdIsPassed() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithNullId();

    //when
    invoiceBook.saveInvoice(invoice);
    invoiceBook.saveInvoice(invoice);
    invoiceBook.saveInvoice(invoice);

    //then
    assertThat(database.get(1).getId(), is(1));
    assertThat(database.get(2).getId(), is(2));
    assertThat(database.get(3).getId(), is(3));
  }

  @Test
  public void shouldAdd3AndRemove2SampleInvoices() {
    //given
    Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();
    Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId2();
    Invoice invoice3 = InvoiceHelper.getSampleInvoiceWithId3();

    //when
    invoiceBook.saveInvoice(invoice1);
    invoiceBook.saveInvoice(invoice2);
    invoiceBook.saveInvoice(invoice3);
    invoiceBook.removeInvoice(1);
    invoiceBook.removeInvoice(2);

    //then
    assertNull(database.get(1));
    assertNull(database.get(2));
    assertThat(database.get(3).getId(), is(database.get(3).getId()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTryingToUpdateWithNullId() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithNullId();

    //when
    invoiceBook.updateInvoice(invoice);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTryingToUpdateInvoiceThatDoesNotExist() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithId1();

    //when
    invoiceBook.updateInvoice(invoice);
  }

  @Test
  public void shouldFindInvoiceByIssuedDateTo() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    invoiceBook.saveInvoice(sampleInvoice);
    invoiceBook.saveInvoice(InvoiceHelper.getSampleInvoiceWithId3());

    //when
    Collection<Invoice> result = invoiceBook.findInvoices(null, null,
        sampleInvoice.getIssuedDate().plusDays(1));

    //then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    Invoice actual = result.iterator().next();
    assertEquals(1, (int) actual.getId());
    assertThat(actual.getIdentifier(), is(sampleInvoice.getIdentifier()));
  }

  @Test
  @Parameters(method = "findParameters")
  public void shouldFindInvoiceByEveryParameter(Invoice searchParams) {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    invoiceBook.saveInvoice(sampleInvoice);
    invoiceBook.saveInvoice(InvoiceHelper.getSampleInvoiceWithId3());

    //when
    Collection<Invoice> result = invoiceBook.findInvoices(searchParams, null, null);

    //then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    Invoice actual = result.iterator().next();
    assertEquals(1, (int) actual.getId());
    assertThat(actual.getIdentifier(), is(sampleInvoice.getIdentifier()));

  }

  @SuppressWarnings("unused")
  private Object[] findParameters() {
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    return new Object[]{
        new Object[]{new Invoice(1, null, null, null, null, null)},
        new Object[]{new Invoice(null, sampleInvoice.getIdentifier(), null, null, null, null)},
        new Object[]{new Invoice(null, null, sampleInvoice.getIssuedDate(), null, null, null)},
        new Object[]{new Invoice(null, null, null, sampleInvoice.getBuyer(), null, null)},
        new Object[]{new Invoice(null, null, null, null, sampleInvoice.getSeller(), null)},
        new Object[]{new Invoice(null, null, null, null, null, sampleInvoice.getEntries())}
    };
  }

  @Test
  public void shouldFindInvoiceByDateRange() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    invoiceBook.saveInvoice(sampleInvoice);
    invoiceBook.saveInvoice(InvoiceHelper.getSampleInvoiceWithId3());

    //when
    Collection<Invoice> result = invoiceBook
        .findInvoices(null, sampleInvoice.getIssuedDate().minusDays(1),
            sampleInvoice.getIssuedDate().plusDays(1));

    //then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    Invoice actual = result.iterator().next();
    assertEquals(1, (int) actual.getId());
    assertThat(actual.getIdentifier(), is(sampleInvoice.getIdentifier()));
  }

  @Test
  public void shouldFindInvoiceByIssuedDateFrom() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    invoiceBook.saveInvoice(sampleInvoice);
    invoiceBook.saveInvoice(InvoiceHelper.getSampleInvoiceWithId3());

    //when
    Collection<Invoice> result = invoiceBook
        .findInvoices(null, sampleInvoice.getIssuedDate().minusDays(1),
            null);

    //then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    Invoice actual = result.iterator().next();
    assertEquals(1, (int) actual.getId());
    assertThat(actual.getIdentifier(), is(sampleInvoice.getIdentifier()));
  }

  //  @Test
//  public void shouldUpdateInvoice() {
//    //given
//    Invoice invoice = InvoiceHelper.getSampleInvoiceWithId2();
//    String newIdentifier = "ABC";
//    LocalDate newDate = LocalDate.now();
//    Invoice updatedInvoice = new Invoice(1, newIdentifier, newDate,
//        InvoiceHelper.getSampleBuyerCompany(), InvoiceHelper.getSampleSellerCompany(),
//        InvoiceHelper.getSampleInvoiceEntries());
//
//    //when
//    invoiceBook.saveInvoice(invoice);
//    invoiceBook.updateInvoice(updatedInvoice);
//
//    //then
//    Invoice actual = database.get(1);
//    assertThat(actual.getIdentifier(), is(newIdentifier));
//    assertThat(actual.getIssuedDate(), is(newDate));
//  }

}
