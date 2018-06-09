package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.Vat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InvoiceHelper {

  public static Invoice getSampleInvoiceWithNullId() {
    Company buyer = getSampleBuyerCompany();
    Company seller = getSampleSellerCompany();
    List<InvoiceEntry> entries = getSampleFourInvoiceEntriesList();

    return new Invoice("TestIdentifier", LocalDate.now(), buyer, seller, entries);
  }

  public static Invoice getSampleInvoiceWithIncorrectId() {
    Company buyer = new Company("CompanyBuyerTest-1", "1111111111", "Test Buyer Street -1",
        "11111",
        "TestLocationBuyer-1");
    Company seller = new Company("CompanySellerTest-1", "1111111111", "Test Seller Street -1",
        "11111",
        "TestLocationSeller-1");
    List<InvoiceEntry> entries = getSampleOneInvoiceEntryList();
    return new Invoice(-1, "TestIdentifier-1", LocalDate.now(), buyer, seller, entries);
  }

  public static Company getSampleSellerCompany() {
    return new Company("CompanySellerTest", "9876543210", "Test Seller Street 1", "99-999",
        "TestLocationSeller");
  }

  public static Company getSampleBuyerCompany() {
    return new Company("CompanyBuyerTest", "1234567890", "Test Buyer Street 1", "11-111",
        "TestLocationBuyer");
  }

  public static Invoice getSampleInvoiceWithId0() {
    Company buyer = new Company("CompanyBuyerTest0", "000000000", "Test Buyer Street 0", "00000",
        "TestLocationBuyer0");
    Company seller = new Company("CompanySellerTest0", "0000000000", "Test Seller Street 0",
        "00000",
        "TestLocationSeller0");
    List<InvoiceEntry> entries = getSampleFourInvoiceEntriesList();
    return new Invoice(0, "TestIdentifier0", LocalDate.now(), buyer, seller, entries);
  }

  public static Invoice getSampleInvoiceWithId1() {
    Company buyer = new Company("CompanyBuyerTest1", "1111111111", "Test Buyer Street 1", "11-111",
        "TestLocationBuyer1");
    Company seller = new Company("CompanySellerTest1", "1111111111", "Test Seller Street 1",
        "11-111",
        "TestLocationSeller1");
    List<InvoiceEntry> entries = getSampleFourInvoiceEntriesList();
    return new Invoice(1, "TestIdentifier1", LocalDate.now(), buyer, seller, entries);
  }

  public static Invoice getSampleInvoiceWithId2() {
    Company buyer = new Company("CompanyBuyerTest2", "2222222222", "Test Buyer Street 2", "22-222",
        "TestLocationBuyer2");
    Company seller = new Company("CompanySellerTest2", "2222222222", "Test Seller Street 2", "22-222",
        "TestLocationSeller2");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #2", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);
    return new Invoice(2, "TestIdentifier2", LocalDate.now(), buyer, seller, entries);
  }

  public static Invoice getSampleInvoiceWithId3() {
    Company buyer = new Company("CompanyBuyerTest3", "3333333333", "Test Buyer Street 3", "33-333",
        "TestLocationBuyer3");
    Company seller = new Company("CompanySellerTest3", "3333333333", "Test Seller Street 3", "33-333",
        "TestLocationSeller3");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #3", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);
    return new Invoice(3, "TestIdentifier3", LocalDate.now(), buyer, seller,
        entries);
  }

  public static Invoice getSampleInvoiceWithId4() {
    Company buyer = new Company("CompanyBuyerTest4", "4444444444", "Test Buyer Street 4", "44-444",
        "TestLocationBuyer4");
    Company seller = new Company("CompanySellerTest4", "4444444444", "Test Seller Street 4", "44-444",
        "TestLocationSeller4");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);
    return new Invoice(4, "TestIdentifier4", LocalDate.now(), buyer, seller, entries);
  }

  public static Invoice getSampleInvoiceWithId5() {
    Company buyer = new Company("CompanyBuyerTest5", "5555555555", "Test Buyer Street 5", "55-555",
        "TestLocationBuyer4");
    Company seller = new Company("CompanySellerTest5", "5555555555", "Test Seller Street 5", "55-555",
        "TestLocationSeller4");
    return new Invoice(5, "TestIdentifier5", LocalDate.of(2018, Month.JANUARY, 1), buyer, seller,
        InvoiceHelper.getSampleEmptyInvoiceEntryList());
  }

  public static Invoice getSampleInvoiceWithId6() {
    Company buyer = new Company("CompanyBuyerTest6", "666666666", "Test Buyer Street 6", "66666",
        "TestLocationBuyer4");
    Company seller = new Company("CompanySellerTest6", "666666666", "Test Seller Street 4", "66666",
        "TestLocationSeller4");
    return new Invoice(6, "TestIdentifier6", LocalDate.of(2018, Month.JANUARY, 1), buyer, seller,
        InvoiceHelper.getSampleOneInvoiceEntryList());
  }

  public static Invoice getSampleInvoiceWithId7() {
    Company buyer = new Company("CompanyBuyerTest7", "777777777", "Test Buyer Street 7", "77777",
        "TestLocationBuyer4");
    Company seller = new Company("CompanySellerTest7", "777777777", "Test Seller Street 7", "77777",
        "TestLocationSeller4");
    return new Invoice(7, "TestIdentifier7", LocalDate.of(2018, Month.JANUARY, 1), buyer, seller,
        InvoiceHelper.getSampleFourInvoiceEntriesList());
  }

  public static Company getSampleBuyer() {
    return new Company("CompanyBuyerTest4", "1444444444", "Test Buyer Street 4", "44-444",
        "TestLocationBuyer4");
  }

  public static Company getSampleSeller() {
    return new Company("CompanySellerTest4", "1444444444", "Test Buyer Street 4", "44-444",
        "TestLocationBuyer4");
  }

  public static List<InvoiceEntry> getSampleEmptyInvoiceEntryList() {
    List<InvoiceEntry> entries = new ArrayList<>();
    return entries;
  }

  public static List<InvoiceEntry> getSampleOneInvoiceEntryList() {
    InvoiceEntry entry = new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);
    return entries;
  }

  public static List<InvoiceEntry> getSampleFourInvoiceEntriesList() {
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REGULAR));
    entries.add(new InvoiceEntry("Test Entry #2", BigDecimal.TEN, Vat.REDUCED1));
    entries.add(new InvoiceEntry("Test Entry #3", BigDecimal.TEN, Vat.REDUCED2));
    entries.add(new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.ZERO));
    return entries;
  }

}