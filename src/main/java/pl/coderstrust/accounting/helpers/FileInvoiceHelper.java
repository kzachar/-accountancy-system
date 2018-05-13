package pl.coderstrust.accounting.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.coderstrust.accounting.database.impl.file.InFileDatabase;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileInvoiceHelper {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  public static List<Invoice> readInvoicesFromFile(String filePath) throws IOException {
    List<Invoice> invoices = new ArrayList<>();
    List<String> result = FileHelper.readFromFile(filePath);
    for (String line : result) {
      Invoice invoice = mapper.readValue(line, Invoice.class);
      invoices.add(invoice);
    }
    return invoices;
  }

  public static void writeInvoiceToFile(Invoice invoiceToWrite, String filePath)
      throws IOException {
    String convertedInvoice = mapper.writeValueAsString(invoiceToWrite);
    FileHelper.writeOneInvoiceToFile(convertedInvoice, filePath);
  }

  public static int getAndIncrementLastId(String filePath) throws IOException {
    List<String> lines = null;
    lines = FileHelper.readFromFile(filePath);
    if (lines.isEmpty()) {
      InFileDatabase inFileDatabase = new InFileDatabase(filePath, "0");
    }
    if (lines.size() > 1) {
      throw new IllegalStateException("You can't isnsert more than 1 invoice");
    }
    int id = Integer.valueOf(lines.get(0)) + 1;
    List<String> idToSave = Arrays.asList(String.valueOf(id));
    FileHelper.writeToFile(idToSave, filePath);
    return id;
  }
}
