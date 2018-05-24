package pl.coderstrust.accounting.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.database.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceService;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.validator.CompanyValidator;
import pl.coderstrust.accounting.model.validator.InvoiceEntryValidator;
import pl.coderstrust.accounting.model.validator.InvoiceValidator;

import java.util.Collection;

@RestController
@RequestMapping
public class InvoiceController {

  private InvoiceService invoiceService = new InvoiceService(new InMemoryDatabase(),
      new InvoiceValidator(new InvoiceEntryValidator(), new CompanyValidator()));

  @GetMapping("/invoices")
  public Collection<Invoice> findInvoices() {
    return invoiceService
        .findInvoices(new Invoice(null, null, null,null, null, null), null, null);
  }

  @GetMapping("/invoices/{id}")
  public Collection<Invoice> findInvoice(@PathVariable(name="id", required = true) int id) {
    return invoiceService
        .findInvoices(new Invoice(id, null, null,null, null, null), null, null);
  }

  @PostMapping("/invoices")
  public int saveInvoice(@RequestBody Invoice invoice) {
    return invoiceService.saveInvoice(invoice);
  }

  @DeleteMapping("/invoices")
  public void removeInvoice(@RequestBody int id) {
    invoiceService.removeInvoice(id);
  }

}