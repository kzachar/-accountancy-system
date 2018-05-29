package pl.coderstrust.accounting.database.impl.file;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.coderstrust.accounting.helpers.FileHelper;
import pl.coderstrust.accounting.helpers.InvoiceHelper;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@RunWith(JUnitParamsRunner.class)
public class InFileDatabaseTest {

  private static final String DATABASE_FILE_PATH = "test_database_invoices.txt";
  private static final String ID_FILE_PATH = "test_id_last_invoice.txt";
  private InFileDatabase database;

  private void cleanTestFiles() {
    File fileDatabaseInvoice = new File(DATABASE_FILE_PATH);
    File fileIdInvoices = new File(ID_FILE_PATH);
    if (!fileDatabaseInvoice.delete()) {
      System.out.println("Test file was not deleted.");
    }
    if (!fileIdInvoices.delete()) {
      System.out.println("Test file was not deleted.");
    }
  }

  @Test
  public void shouldSaveCorrectInvoice() throws IOException {
    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId0();
      Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId1();

      //when
      database.saveInvoice(invoice1);
      database.saveInvoice(invoice2);

      //then
      String actual1 = FileHelper.readFromFile(DATABASE_FILE_PATH).get(0);
      String expected1 = "{\"id\":0"
          + ",\"identifier\":\"TestIdentifier0\""
          + ",\"issuedDate\":\"" + LocalDate.now() + "\""
          + ",\"buyer\":{\"name\":\"CompanyBuyerTest0\""
          + ",\"taxId\":\"000000000\""
          + ",\"streetAndNumber\":\"Test Buyer Street 0\""
          + ",\"postalCode\":\"00000\",\"location\":\"TestLocationBuyer0\"}"
          + ",\"seller\":{\"name\":\"CompanySellerTest0\",\"taxId\":\"0000000000\""
          + ",\"streetAndNumber\":\"Test Seller Street 0\",\"postalCode\":\"00000\""
          + ",\"location\":\"TestLocationSeller0\"},\"entries\":[{\"description\":\"Test Entry #1\""
          + ",\"price\":10,\"vat\":23},{\"description\":\"Test Entry #2\""
          + ",\"price\":10,\"vat\":8},{\"description\":\"Test Entry #3\""
          + ",\"price\":10,\"vat\":5},{\"description\":\"Test Entry #4\""
          + ",\"price\":10,\"vat\":0}]}";
      assertThat(actual1, is((expected1)));
      String actual2 = FileHelper.readFromFile(DATABASE_FILE_PATH).get(1);
      String expected2 = "{\"id\":1"
          + ",\"identifier\":\"TestIdentifier1\""
          + ",\"issuedDate\":\"" + LocalDate.now() + "\""
          + ",\"buyer\":{\"name\":\"CompanyBuyerTest1\""
          + ",\"taxId\":\"1111111111\""
          + ",\"streetAndNumber\":\"Test Buyer Street 1\""
          + ",\"postalCode\":\"11111\",\"location\":\"TestLocationBuyer1\"}"
          + ",\"seller\":{\"name\":\"CompanySellerTest1\",\"taxId\":\"1111111111\""
          + ",\"streetAndNumber\":\"Test Seller Street 1\",\"postalCode\":\"11111\""
          + ",\"location\":\"TestLocationSeller1\"},\"entries\":[{\"description\":\"Test Entry #1\""
          + ",\"price\":10,\"vat\":23},{\"description\":\"Test Entry #2\""
          + ",\"price\":10,\"vat\":8},{\"description\":\"Test Entry #3\""
          + ",\"price\":10,\"vat\":5},{\"description\":\"Test Entry #4\""
          + ",\"price\":10,\"vat\":0}]}";
      assertThat(actual2, is((expected2)));

    } finally {
      cleanTestFiles();
    }
  }

  @Test
  public void shouldIncrementIdWhenInvoiceWithNullIdIsPassed()
      throws IOException {
    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice = InvoiceHelper.getSampleInvoiceWithNullId();

      //when
      database.saveInvoice(invoice);
      database.saveInvoice(invoice);

      //then
      assertNotNull(FileHelper.readFromFile(DATABASE_FILE_PATH).get(0));
      assertNotNull(FileHelper.readFromFile(DATABASE_FILE_PATH).get(0));
    } finally {
      cleanTestFiles();
    }
  }

  @Test
  public void shouldThrowExceptionIfPathToIdFilePathIsNotExist() {
    try {
      // /given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      File fileIdInvoices = new File(ID_FILE_PATH);
      fileIdInvoices.setReadOnly();
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId0();
      database.saveInvoice(invoice1);
    } finally {
      cleanTestFiles();
    }
  }

  @Test(expected = IllegalArgumentException.class)

  public void shouldThrowExceptionIfDatabaseFilePathIsNull() {
    //given
    database = new InFileDatabase(null, ID_FILE_PATH);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfDatabaseFilePathIsEmpty() {
    //given
    database = new InFileDatabase("", ID_FILE_PATH);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfIdFilePathIsNull() {
    //given
    database = new InFileDatabase(DATABASE_FILE_PATH, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfIdFilePathhIsEmpty() {
    //given
    database = new InFileDatabase(DATABASE_FILE_PATH, "");
  }
}
