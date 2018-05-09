package pl.coderstrust.accounting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.database.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.validator.CompanyValidator;
import pl.coderstrust.accounting.model.validator.InvoiceEntryValidator;
import pl.coderstrust.accounting.model.validator.InvoiceValidator;

import java.util.Collection;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  private InvoiceBook invoiceBook = new InvoiceBook(new InMemoryDatabase(),
      new InvoiceValidator(new InvoiceEntryValidator(), new CompanyValidator()));

  @GetMapping
  public Collection<Invoice> getInvoices() {
    return invoiceBook.findInvoices(new Invoice(1, null, null, null, null, null), null, null);
  }

  @PostMapping
  public void saveInvoice(@RequestBody Invoice invoice) {
    invoiceBook.saveInvoice(invoice);
  }

}