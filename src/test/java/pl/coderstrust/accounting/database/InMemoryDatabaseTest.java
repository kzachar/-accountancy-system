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

  @Test
  public void ShouldTestIfSaveInvoiceMethodWorksWithNullId() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithNullId();
    final String expectedKeySet = "[1, 2, 3]";

    //when
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);

    //then
    Assert.assertEquals(false, database.invoices.isEmpty());
    Assert.assertEquals(expectedKeySet, database.invoices.keySet().toString());
    Assert.assertEquals(3, database.invoices.size());

  }

  @Test
  public void ShouldTestIfSaveInvoiceMethodWorks() {
    //given
    Invoice invoice = InvoiceHelper.getSampleInvoiceWithId();
    final String expectedKeySet = "[1, 2, 3, 4]";

    //when
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);
    database.saveInvoice(invoice);

    //then
    Assert.assertEquals(false, database.invoices.isEmpty());
    Assert.assertEquals(expectedKeySet, database.invoices.keySet().toString());
    Assert.assertEquals(4, database.invoices.size());
  }
}