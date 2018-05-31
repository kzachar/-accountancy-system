package pl.coderstrust.accounting.database.impl.file;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionIfNoInvoiceWithGivenIdToRemove() throws IOException {

    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();

      //when
      database.saveInvoice(invoice1);
      database.removeInvoice(5);
    } finally {
      cleanTestFiles();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionIfNoInvoiceToRemove() throws IOException {

    //given
    database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);

    //when
    database.removeInvoice(5);
  }

  @Test
  public void shouldRemoveInvoiceWithGivenIdWhenOneInvoiceInList() throws IOException {

    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();

      //when
      database.saveInvoice(invoice1);
      database.removeInvoice(0);

      //then
      assertTrue(FileHelper.readFromFile(DATABASE_FILE_PATH).isEmpty());
    } finally {
      cleanTestFiles();
    }
  }

  @Test
  public void shouldRemoveInvoiceWithGivenIdWhenManyInvoicesInList() throws IOException {

    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId0();
      Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId1();
      Invoice invoice3 = InvoiceHelper.getSampleInvoiceWithId2();

      //when
      database.saveInvoice(invoice1);
      database.saveInvoice(invoice2);
      database.saveInvoice(invoice3);
      database.removeInvoice(1);

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
      assertThat(actual1, is(equalTo(expected1)));
      String actual2 = FileHelper.readFromFile(DATABASE_FILE_PATH).get(1);
      String expected2 = "{\"id\":2"
          + ",\"identifier\":\"TestIdentifier2\""
          + ",\"issuedDate\":\"2018-05-31\""
          + ",\"buyer\":{\"name\":\"CompanyBuyerTest2\""
          + ",\"taxId\":\"222222222\""
          + ",\"streetAndNumber\":\"Test Buyer Street 2\""
          + ",\"postalCode\":\"22222\",\"location\":\"TestLocationBuyer2\"}"
          + ",\"seller\":{\"name\":\"CompanySellerTest2\",\"taxId\":\"222222222\""
          + ",\"streetAndNumber\":\"Test Seller Street 2\",\"postalCode\":\"22222\""
          + ",\"location\":\"TestLocationSeller2\"},\"entries\":[{\"description\":\"Test Entry #2\""
          + ",\"price\":10,\"vat\":8}]}";
      assertThat(actual2, is(equalTo(expected2)));
    } finally {
      cleanTestFiles();
    }
  }

  @Test
  public void shouldGetInvoiceById() throws IOException {

    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId0();
      Invoice invoice2 = InvoiceHelper.getSampleInvoiceWithId1();
      Invoice invoice3 = InvoiceHelper.getSampleInvoiceWithId2();

      //when
      database.saveInvoice(invoice1);
      database.saveInvoice(invoice2);
      database.saveInvoice(invoice3);

      //then
      Invoice actual1 = invoice1;
      Invoice expected1 = database.get(0);
      invoicesAreIdentical(actual1, expected1);
      Invoice actual2 = invoice3;
      Invoice expected2 = database.get(2);
      invoicesAreIdentical(actual2, expected2);
    } finally {
      cleanTestFiles();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionIfNoInvoiceWithGivenIdToGet() throws IOException {

    try {
      //given
      database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);
      Invoice invoice1 = InvoiceHelper.getSampleInvoiceWithId1();

      //when
      database.saveInvoice(invoice1);
      database.get(5);
    } finally {
      cleanTestFiles();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionIfNoInvoiceToGet() throws IOException {

    //given
    database = new InFileDatabase(DATABASE_FILE_PATH, ID_FILE_PATH);

    //when
    database.get(5);
  }

  public static void invoicesAreIdentical(Invoice invoice, Invoice invoiceToCompare) {
    MatcherAssert.assertThat(invoice.getId(), CoreMatchers.is(invoiceToCompare.getId()));
    MatcherAssert
        .assertThat(invoice.getIdentifier(), CoreMatchers.is(invoiceToCompare.getIdentifier()));
    MatcherAssert
        .assertThat(invoice.getIssuedDate(), CoreMatchers.is(invoiceToCompare.getIssuedDate()));
    MatcherAssert.assertThat(invoice.getBuyer(), CoreMatchers.is(invoiceToCompare.getBuyer()));
    MatcherAssert.assertThat(invoice.getSeller(), CoreMatchers.is(invoiceToCompare.getSeller()));
    assertTrue(invoice.getEntries().equals(invoiceToCompare.getEntries()));
  }
}