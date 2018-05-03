package pl.coderstrust.accounting.logic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

  private Database databaseMock;

  @Before
  public void setUp() {
    databaseMock = Mockito.mock(Database.class);
  }

  @Test
  public void shouldRemoveInvoice() {
    //given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);
    doNothing().when(databaseMock).removeInvoice(anyInt());
    when(databaseMock.get(anyInt())).thenReturn(
        new Invoice(1, "test", LocalDate.now(),
            new Company("Lorus", 611 - 23 - 06 - 888, "st. 1 Maja 37", 58 - 530, "Kowary"),
            new Company("Casio", 113 - 19 - 62 - 616, "st. Wirażowa 119", 02 - 145, "Warszawa"), null));

    //when
    invoiceBook.removeInvoice(1);

    //then
    verify(databaseMock).removeInvoice(1);
  }

  @Test
  public void shouldThrowExceptionWhenInvoiceDoesNotExistWhenRemoving() {
    //given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);
    doNothing().when(databaseMock).removeInvoice(anyInt());
    when(databaseMock.get(anyInt())).thenReturn(null);

    //when
    try {
      invoiceBook.removeInvoice(1);
      fail("should throw exception");
    } catch (IllegalArgumentException error) {

      //then
      assertTrue(error.getMessage().contains("An invoice with given ID : "));
    }
  }
}