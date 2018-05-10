package pl.coderstrust.accounting.logic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.helpers.InvoiceHelper;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.validator.InvoiceValidator;
import pl.coderstrust.accounting.model.validator.exception.InvoiceValidationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class InvoiceBookTest {

  @Mock
  private Database databaseMock;

  @Mock
  private InvoiceValidator invoiceValidatorMock;

  private InvoiceBook invoiceBook;

  @Before
  public void setUp() {
    initMocks(this);
    invoiceBook = new InvoiceBook(databaseMock, invoiceValidatorMock);
  }

  @Test
  public void shouldSaveCorrectInvoice() {

    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithId1();

    //when
    invoiceBook.saveInvoice(sampleInvoice);

    //then
    verify(invoiceValidatorMock, Mockito.times(1)).validate(sampleInvoice, false);
    verify(databaseMock).saveInvoice(sampleInvoice);
  }

  @Test
  public void shouldNotSaveIncorrectInvoice() {
    //given
    Invoice incorrectInvoice = InvoiceHelper.getSampleInvoiceWithIncorrectId();

    //when
    when(invoiceValidatorMock.validate(incorrectInvoice, false))
        .thenReturn(Collections.singletonList(new InvoiceValidationException("List Exceptions")));
    invoiceBook.saveInvoice(incorrectInvoice);

    //then
    verify(invoiceValidatorMock, Mockito.times(1)).validate(incorrectInvoice, false);
    verify(databaseMock, never()).saveInvoice(incorrectInvoice);
  }

  @Test
  public void shouldRemoveInvoice() {
    //given
    when(databaseMock.get(anyInt())).thenReturn(InvoiceHelper.getSampleInvoiceWithId1());

    //when
    invoiceBook.removeInvoice(1);

    //then
    verify(databaseMock).removeInvoice(1);
  }

  @Test
  public void shouldThrowExceptionWhenInvoiceDoesNotExistWhenRemoving() {
    //given
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

  @Test
  @Parameters(method = "updateParameters")
  public void shouldUpdateOnlyGivenParam(Invoice invoice) {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithId1();
    when(databaseMock.get(anyInt())).thenReturn(sampleInvoice);

    //when
    invoiceBook.updateInvoice(invoice);

    //then
    verify(databaseMock)
        .updateInvoice(argThat(passed -> verifyArguments(passed, invoice, sampleInvoice)));
    verify(invoiceValidatorMock).validate(any(), eq(true));
  }

  private boolean verifyArguments(Invoice passedToDatabase, Invoice passedToMethod,
      Invoice fromDatabase) {
    if (!passedToDatabase.getIdentifier().equals(
        passedToMethod.getIdentifier() == null ? fromDatabase.getIdentifier()
            : passedToMethod.getIdentifier())) {
      return false;
    }
    if (!passedToDatabase.getIssuedDate().equals(
        passedToMethod.getIssuedDate() == null ? fromDatabase.getIssuedDate()
            : passedToMethod.getIssuedDate())) {
      return false;
    }
    if (!passedToDatabase.getBuyer().equals(
        passedToMethod.getBuyer() == null ? fromDatabase.getBuyer()
            : passedToMethod.getBuyer())) {
      return false;
    }
    if (!passedToDatabase.getSeller().equals(
        passedToMethod.getSeller() == null ? fromDatabase.getSeller()
            : passedToMethod.getSeller())) {
      return false;
    }
    return passedToDatabase.getEntries().equals(
        passedToMethod.getEntries() == null ? fromDatabase.getEntries()
            : passedToMethod.getEntries());
  }

  @Test
  public void shouldThrowExceptionWhenTryingToUpdateNullInvoice() {
    //given
    boolean thrown = false;

    //when
    try {
      invoiceBook.updateInvoice(null);
    } catch (Exception ex) {
      thrown = true;
      assertTrue(ex instanceof IllegalArgumentException);
    }

    //then
    assertTrue(thrown);
    verify(databaseMock, never()).updateInvoice(any());
  }

  @Test
  public void shouldThrowExceptionWhenTryingToUpdateInvoiceWithNullId() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    boolean thrown = false;

    //when
    try {
      invoiceBook.updateInvoice(sampleInvoice);
    } catch (Exception ex) {
      thrown = true;
      assertTrue(ex instanceof IllegalArgumentException);
    }

    //then
    assertTrue(thrown);
    verify(databaseMock, never()).updateInvoice(any());
  }

  @Test
  public void shouldThrowExceptionWhenTryingToUpdateInvoiceThatDoesNotExist() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithId2();
    boolean thrown = false;

    //when
    try {
      invoiceBook.updateInvoice(sampleInvoice);
    } catch (Exception ex) {
      thrown = true;
      assertTrue(ex instanceof IllegalArgumentException);
    }

    //then
    assertTrue(thrown);
    verify(databaseMock, never()).updateInvoice(any());
  }

  @Test
  public void shouldThrowExceptionWhenTryingToUpdateInvoiceThatDoesNotValidate() {
    //given
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithId2();
    List<InvoiceValidationException> validationExceptions = new ArrayList<>();
    validationExceptions.add(new InvoiceValidationException("TEST"));
    when(invoiceValidatorMock.validate(any(), eq(true))).thenReturn(validationExceptions);
    when(databaseMock.get(anyInt())).thenReturn(InvoiceHelper.getSampleInvoiceWithId1());
    boolean thrown = false;

    //when
    try {
      invoiceBook.updateInvoice(sampleInvoice);
    } catch (Exception ex) {
      thrown = true;
      assertTrue(ex instanceof IllegalArgumentException);
      assertTrue(ex.getMessage().contains("TEST"));
    }

    //then
    assertTrue(thrown);
    verify(databaseMock, never()).updateInvoice(any());
  }

  @Test
  public void shouldDelegateFindingInvoiceToDatabase() {
    //given
    Invoice searchParams = new Invoice(null, null, null, null, null);
    LocalDate dateFrom = LocalDate.now();
    LocalDate dateTo = LocalDate.now();

    //when
    invoiceBook.findInvoices(searchParams, dateFrom, dateTo);

    //then
    verify(databaseMock).find(searchParams, dateFrom, dateTo);
  }

  @SuppressWarnings("unused")
  private Object[] updateParameters() {
    Invoice sampleInvoice = InvoiceHelper.getSampleInvoiceWithNullId();
    return new Object[]{
        new Object[]{new Invoice(1, sampleInvoice.getIdentifier(), null, null, null, null)},
        new Object[]{new Invoice(1, null, sampleInvoice.getIssuedDate(), null, null, null)},
        new Object[]{new Invoice(1, null, null, sampleInvoice.getBuyer(), null, null)},
        new Object[]{new Invoice(1, null, null, null, sampleInvoice.getSeller(), null)},
        new Object[]{new Invoice(1, null, null, null, null, sampleInvoice.getEntries())}
    };
  }
}