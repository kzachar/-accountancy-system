package pl.coderstrust.accounting.helpers;

import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;
import pl.coderstrust.accounting.model.Vat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceHelper {

  public static Invoice getSampleInvoiceWithNullId() {
    Company buyer = new Company("CompanyBuyerTest", "1234567890", "Test Buyer Street 1", "11111",
        "TestLocationBuyer");
    Company seller = new Company("CompanySellerTest", "987654321", "Test Seller Street 1", "99999",
        "TestLocationSeller");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice("TestIdentifier", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Invoice getSampleInvoiceWithId1() {
    Company buyer = new Company("CompanyBuyerTest1", "1111111111", "Test Buyer Street 1", "11111",
        "TestLocationBuyer1");
    Company seller = new Company("CompanySellerTest1", "1111111111", "Test Seller Street 1", "11111",
        "TestLocationSeller1");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice(1, "TestIdentifier1", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Invoice getSampleInvoiceWithId2() {
    Company buyer = new Company("CompanyBuyerTest2", "222222222", "Test Buyer Street 2", "22222",
        "TestLocationBuyer2");
    Company seller = new Company("CompanySellerTest2", "222222222", "Test Seller Street 2", "22222",
        "TestLocationSeller2");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #2", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice(2, "TestIdentifier2", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Invoice getSampleInvoiceWithId3() {
    Company buyer = new Company("CompanyBuyerTest3", "333333333", "Test Buyer Street 3", "33333",
        "TestLocationBuyer3");
    Company seller = new Company("CompanySellerTest3", "333333333", "Test Seller Street 3", "33333",
        "TestLocationSeller3");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #3", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice(3, "TestIdentifier3", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Invoice getSampleInvoiceWithId4() {
    Company buyer = new Company("CompanyBuyerTest4", "444444444", "Test Buyer Street 4", "44444",
        "TestLocationBuyer4");
    Company seller = new Company("CompanySellerTest4", "444444444", "Test Seller Street 4", "44444",
        "TestLocationSeller4");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice(4, "TestIdentifier4", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Company getSampleBuyer() {
    Company buyer = new Company("CompanyBuyerTest4", "444444444", "Test Buyer Street 4", "44444",
        "TestLocationBuyer4");

    return buyer;
  }

  public static Company getSampleSeller() {
    Company seller = new Company("CompanyBuyerTest4", "444444444", "Test Buyer Street 4", "44444",
        "TestLocationBuyer4");

    return seller;
  }

  public static InvoiceEntry getSampleInvoiceEntry() {
    InvoiceEntry entry = new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    return entry;
  }

  public static List getSampleListOfEntries() {
    InvoiceEntry entry = new InvoiceEntry("Test Entry #4", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    return entries;
  }
}