package pl.coderstrust.accounting.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.accounting.helpers.InvoiceHelper;
import pl.coderstrust.accounting.model.Invoice;

public class InMemoryDatabaseTest {

  private InMemoryDatabase database;

  @Before
  public void init() {
    database = new InMemoryDatabase();
  }

  //Tests to check if Id increments need to be added, no method defined at the time when
  // Add invoice to InMemoryDatabase was coded

  @Test
  public void shouldIncrementIdWhenInvoiceWithNullIdIsPassed() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithNullId();
    final String expectedKeySet = "[1, 2, 3]";

    //when
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);

    //then
    Assert.assertEquals(false, database.getInvoices().isEmpty());
    Assert.assertEquals(3, database.getInvoices().size());
  }

  @Test
  public void shouldSaveSampleInvoices() {
    //given
    Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();
    Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId2();
    Invoice invoice3 = InvoiceHelper.getSampleInvoiceWithId3();
    Invoice invoice4 = InvoiceHelper.getSampleInvoiceWithId4();
    final String expectedKeySet = "[1, 2, 3, 4]";

    //when
    database.saveInvoice(invoice1);
    database.saveInvoice(invoice2);
    database.saveInvoice(invoice3);
    database.saveInvoice(invoice4);

    //then
    Assert.assertEquals(false, database.getInvoices().isEmpty());
    Assert.assertEquals(4, database.getInvoices().size());
  }
}