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
    Company buyer = new Company("CompanyBuyerTest", 1234567890, "Test Buyer Street 1", 11111,
        "TestLocationBuyer");
    Company seller = new Company("CompanySellerTest", 987654321, "Test Seller Street 1", 99999,
        "TestLocationSeller");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice("TestIdentifier", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }

  public static Invoice getSampleInvoiceWithId() {
    Company buyer = new Company("CompanyBuyerTest2", 222222222, "Test Buyer Street 1", 11111,
        "TestLocationBuyer");
    Company seller = new Company("CompanySellerTest2", 987654321, "Test Seller Street 1", 99999,
        "TestLocationSeller");
    InvoiceEntry entry = new InvoiceEntry("Test Entry #1", BigDecimal.TEN, Vat.REDUCED1);
    List<InvoiceEntry> entries = new ArrayList<>();
    entries.add(entry);

    Invoice invoice = new Invoice(1, "TestIdentifier", LocalDate.now(), buyer, seller, entries);
    return invoice;
  }
}


