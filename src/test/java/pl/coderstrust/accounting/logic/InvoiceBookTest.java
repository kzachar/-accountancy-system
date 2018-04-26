package pl.coderstrust.accounting.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  private InvoiceBook invoiceBookMock;
  private Database databaseMock;

  @Before
  public void setUp() {
    databaseMock = Mockito.mock(Database.class);
  }

  @Test
  public void shouldRemoveInvoice() {

    //given
    invoiceBookMock = new InvoiceBook(databaseMock);
    Mockito.doNothing().when(databaseMock).removeInvoice(Mockito.anyInt());
    Mockito.when(databaseMock.get(Mockito.anyInt())).thenReturn(
        new Invoice(1, "test", LocalDate.now(),
            new Company("Lorus", 611 - 23 - 06 - 888, "st. 1 Maja 37", 58 - 530, "Kowary"),
            new Company("Casio", 113 - 19 - 62 - 616, "st. Wirażowa 119", 02 - 145, "Warszawa")));

    //when
    invoiceBookMock.removeInvoice(1);

    //then
  }

  @Test
  public void shouldRemoveInvoiceWhenInvoiceDoesnotExist() {

    //given
    invoiceBookMock = new InvoiceBook(databaseMock);
    Mockito.doNothing().when(databaseMock).removeInvoice(Mockito.anyInt());

    //when
    try {
      invoiceBookMock.removeInvoice(1);
    } catch (IllegalArgumentException error) {
      Assert.assertTrue(error.getMessage().contains("An invoice with given ID : "));
    }
    //then

  }
}