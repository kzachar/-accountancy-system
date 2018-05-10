package pl.coderstrust.accounting.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInvoiceHelper {

  ObjectMapper mapper = new ObjectMapper();

  FileHelper fileHelper = new FileHelper();


  public List<Invoice> readInvoicesFromFile(String filePath) throws IOException {
    List<Invoice> invoices = new ArrayList<>();
    List<String> result = fileHelper.readFromFile(filePath);
    for (String line : result) {
      Invoice invoice = mapper.readValue(line, Invoice.class);
      invoices.add(invoice);
    }
    return invoices;
  }

}
